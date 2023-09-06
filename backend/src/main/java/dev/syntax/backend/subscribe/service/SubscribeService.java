package dev.syntax.backend.subscribe.service;


import dev.syntax.backend.subscribe.model.Subscribe;
import dev.syntax.backend.user.model.User;

public interface SubscribeService extends CrudService<Subscribe, Long> {
    public Subscribe findById(Long subscribeId);

    public Subscribe save(Subscribe subscribe);

    public void deleteByUser(User user);
}
