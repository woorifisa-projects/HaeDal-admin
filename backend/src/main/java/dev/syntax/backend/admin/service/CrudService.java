package dev.syntax.backend.admin.service;

import java.util.List;

public interface CrudService<Response,Request,ID>{
    Response findById(ID id);
    List<Response> findAll();
}
