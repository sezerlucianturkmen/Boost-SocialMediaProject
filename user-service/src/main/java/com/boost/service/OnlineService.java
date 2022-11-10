package com.boost.service;
import com.boost.repository.IOnlineRepository;
import com.boost.repository.entity.Online;
import com.boost.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class OnlineService extends ServiceManager<Online, String> {


    private final IOnlineRepository onlineRepository;

    public OnlineService(IOnlineRepository onlineRepository) {
        super(onlineRepository);
        this.onlineRepository = onlineRepository;
    }
}