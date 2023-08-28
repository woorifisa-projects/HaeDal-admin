package dev.syntax.backend.admin.service;

import dev.syntax.backend.admin.model.Admin;
import dev.syntax.backend.admin.repository.AdminRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

    private final AdminRepository adminRepository;

    public AdminServiceImpl(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public Admin findById(Long id) {

        return adminRepository.findById(id).orElse(null);
    }

    @Override
    public List<Admin> findAll() {
        return (List<Admin>) adminRepository.findAll();
    }
}
