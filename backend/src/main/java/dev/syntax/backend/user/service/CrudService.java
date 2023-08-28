package dev.syntax.backend.user.service;

import java.util.List;

public interface CrudService<User, ID>{
    User findById(ID id);
    List<User> findAll();
}
