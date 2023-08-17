package com.giyong.community.service;

import com.giyong.community.dto.AdminDto;
import com.giyong.community.entity.Admin;
import com.giyong.community.repository.AdminRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminRepository adminRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Admin addAdmin(AdminDto adminDto) {
        adminDto.setCreatedAt(new Date());
        adminDto.setUpdatedAt(new Date());
        Admin admin = modelMapper.map(adminDto, Admin.class);

        return adminRepository.save(admin);
    }

    @Override
    public Admin modify(AdminDto adminDto) {
        Admin oldAdmin = adminRepository.findById(adminDto.getAdminId()).orElse(null);

        AdminDto dto = modelMapper.map(oldAdmin, AdminDto.class);
        dto.setAdminName(adminDto.getAdminName());
        dto.setUpdatedAt(new Date());

        Admin modifyAdmin = modelMapper.map(dto, Admin.class);

        return adminRepository.save(modifyAdmin);
    }

    @Override
    public Page<Admin> findAll(Pageable pageable) {
        return (Page<Admin>) adminRepository.findAll(pageable);
    }

    @Override
    public Admin findById(String adminId) {
        return adminRepository.findById(adminId).orElse(null);
    }

    @Override
    public void remove(String adminId) {
        adminRepository.deleteById(adminId);
    }
}
