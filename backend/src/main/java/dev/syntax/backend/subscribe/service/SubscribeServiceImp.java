package dev.syntax.backend.subscribe.service;

import dev.syntax.backend.subscribe.model.Subscribe;
import dev.syntax.backend.subscribe.repository.SubscribeRepository;
import dev.syntax.backend.user.model.User;
import org.springframework.stereotype.Service;

@Service
public class SubscribeServiceImp implements SubscribeService{
    private SubscribeRepository subscribeRepository;

    public SubscribeServiceImp(SubscribeRepository subscribeRepository) {
        this.subscribeRepository = subscribeRepository;
    }


    @Override
    public Subscribe findById(Long subscribeId) {
        return subscribeRepository.findById(subscribeId).orElse(null);
    }

    public Subscribe save(Subscribe subscribe){
        return subscribeRepository.save(subscribe);
    }

    @Override
    public void deleteByUser(User user) {
            subscribeRepository.deleteByUser(user);
    }


}
