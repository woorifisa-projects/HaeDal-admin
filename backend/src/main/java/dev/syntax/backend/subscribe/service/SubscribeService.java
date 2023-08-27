package dev.syntax.backend.subscribe.service;


import dev.syntax.backend.subscribe.model.Subscribe;

public interface SubscribeService extends CrudService<Subscribe, Long> {
    public Subscribe findById(Long subscribeId);

    public Subscribe save(Subscribe subscribe);
}
