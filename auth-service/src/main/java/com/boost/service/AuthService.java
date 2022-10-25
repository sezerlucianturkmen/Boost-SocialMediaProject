package com.boost.service;

import com.boost.dto.request.ActivateRequestDto;
import com.boost.dto.request.LoginRequestDto;
import com.boost.dto.request.NewUserCreateDto;
import com.boost.dto.request.RegisterRequestDto;
import com.boost.dto.response.LoginResponseDto;
import com.boost.dto.response.RegisterResponseDto;
import com.boost.exception.AuthManagerException;
import com.boost.exception.ErrorType;
import com.boost.manager.IUserManager;
import com.boost.mapper.IAuthMapper;
import com.boost.repository.IAuthRepository;
import com.boost.repository.entity.Auth;
import com.boost.repository.enums.Roles;
import com.boost.repository.enums.Status;
import com.boost.utility.CodeGenerator;
import com.boost.utility.JwtTokenManager;
import com.boost.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository authRepository;
    private final IUserManager userManager;
    private JwtTokenManager jwtTokenManager;


    public AuthService(IAuthRepository authRepository,IUserManager userManager,JwtTokenManager jwtTokenManager) {
        super(authRepository);
        this.authRepository = authRepository;
        this.userManager=userManager;
        this.jwtTokenManager=jwtTokenManager;
    }
    public RegisterResponseDto register(RegisterRequestDto dto) {
        Auth auth= IAuthMapper.INSTANCE.toAuth(dto);

//        if (userIsExist(dto.getUsername())){
//            throw  new AuthManagerException(ErrorType.USERNAME_DUPLICATE);
//        }else {
//
//            if (dto.getAdminCode()!=null&& dto.getAdminCode().equals("admin"))  {
//                auth.setRole(Roles.ADMIN);
//            }
//            try {
//                return   save(auth);
//            }catch (Exception e){
//                throw  new AuthManagerException(ErrorType.USER_NOT_CREATED);
//            }
//        }
        if (dto.getAdminCode()!=null&& dto.getAdminCode().equals("admin"))  {
            auth.setRole(Roles.ADMIN);
        }
        try {
            auth.setActivatedCode(CodeGenerator.generateCode(UUID.randomUUID().toString()));
            save(auth);
            userManager.createUser(NewUserCreateDto.builder()
                    .authid(auth.getId())
                    .email(auth.getEmail())
                    .username(auth.getUsername())
                    .build());
            return  IAuthMapper.INSTANCE.toRegisterResponseDto(auth);
        }catch (AuthManagerException a){
            throw  new AuthManagerException(ErrorType.USER_NOT_CREATED);
        }
    }

    public  boolean userIsExist(String username){
        return authRepository.existUserName(username);
    }

    public Optional<LoginResponseDto> login(LoginRequestDto dto) {
        Optional<Auth> auth=authRepository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());
        if (auth.isPresent()){
            LoginResponseDto loginResponseDto=IAuthMapper.INSTANCE.toLoginResponseDto(auth.get());
            String token= jwtTokenManager.createToken(loginResponseDto.getId());
            loginResponseDto.setToken(token);
            return Optional.of(loginResponseDto);
        }else {
            throw  new AuthManagerException(ErrorType.LOGIN_ERROR_WRONG);
        }
    }

    public boolean activateStatus(ActivateRequestDto dto) {
        Optional<Auth> auth=authRepository.findById(dto.getId());
        if (auth.isEmpty()){
            throw  new AuthManagerException(ErrorType.USER_NOT_FOUND);
        }
        if (auth.get().getActivatedCode().equals(dto.getActivatedCode())){
            auth.get().setStatus(Status.ACTIVE);
            userManager.activateStatus(dto);
            save(auth.get());
            return true;
        }
        throw  new AuthManagerException(ErrorType.INVALID_ACTİVATE_CODE);
    }

}