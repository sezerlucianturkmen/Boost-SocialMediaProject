package com.boost.repository;
import com.boost.repository.entity.Auth;
import com.boost.repository.enums.Roles;
import com.boost.repository.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface IAuthRepository extends JpaRepository<Auth,Long>{

    @Query("select count(a.username)>0  from Auth as a  where a.username=?1")
    Boolean existUserName(String username);
    Optional<Auth> findOptionalByUsernameAndPassword(String username,String password);
    ///
    List<Auth> findAllByRole(Roles roles);

    @Query("select a from Auth a where a.status='ACTIVE' or a.status='PENDING'")
    Optional<List<Auth>> findAllActiveAndPendingAuth();

    List<Auth> findAllByStatusIn(List<Status> statusList);

}
