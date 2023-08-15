package dev.syntax.backend.admin.service;

import dev.syntax.backend.admin.dto.request.AdminRequest;
import dev.syntax.backend.admin.dto.response.AdminResponse;

import java.util.List;

public interface AdminService extends CrudService<AdminResponse, AdminRequest, Long>{

}
