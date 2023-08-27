package dev.syntax.backend.subscribe.service;

public interface CrudService<T,ID> {
    T findById(ID Id);

}
