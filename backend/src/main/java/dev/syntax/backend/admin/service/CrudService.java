package dev.syntax.backend.admin.service;

import java.util.List;

public interface CrudService<Admin,ID>{
    Admin findById(ID id);
    List<Admin> findAll();
}
