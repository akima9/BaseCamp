package com.giyong.community.service;

import com.giyong.community.dto.AdminDto;
import com.giyong.community.entity.Admin;
import com.giyong.community.repository.AdminRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    @Transactional
    public Admin addAdmin(AdminDto adminDto) {
        Admin admin = modelMapper.map(adminDto, Admin.class);

        return adminRepository.save(admin);
    }

    @Override
    public Admin modify(AdminDto adminDto) {
        // 아이디로 기존 admin 불러오기.
        Admin oldAdmin = adminRepository.findById(adminDto.getId()).orElse(null);
        // admin -> adminDto로 변환.
        AdminDto dto = modelMapper.map(oldAdmin, AdminDto.class);
        // 변경된 데이터 세팅.
        dto.setAdminName(adminDto.getAdminName());
        // 비밀번호는 입력한 경우에만 변경.
        if (adminDto.getAdminPw() != null) {
            dto.setAdminPw(passwordEncoder.encode(adminDto.getAdminPw()));
        }

        Admin modifyAdmin = modelMapper.map(dto, Admin.class);

        return adminRepository.save(modifyAdmin);
    }

    @Override
    public Page<Admin> findAll(Pageable pageable) {
        return (Page<Admin>) adminRepository.findAll(pageable);
    }

    @Override
    public Admin findById(Long id) {
        return adminRepository.findById(id).orElse(null);
    }

    @Override
    public Admin findByAdminId(String adminId) {
        return adminRepository.findByAdminId(adminId);
    }

    @Override
    public void remove(Long id) {
        adminRepository.deleteById(id);
    }
}
