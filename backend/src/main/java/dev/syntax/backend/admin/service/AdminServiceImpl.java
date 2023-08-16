package dev.syntax.backend.admin.service;

import dev.syntax.backend.admin.dto.response.AdminResponse;
import dev.syntax.backend.admin.model.Admin;
import dev.syntax.backend.admin.model.User;
import dev.syntax.backend.admin.repository.AdminRepository;

import dev.syntax.backend.admin.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService{

    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public AdminResponse findById(Long id) {

        return null;
    }

    @Override
    public List<AdminResponse> findAll() {

        return null;
    }
}
