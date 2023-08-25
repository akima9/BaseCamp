package com.giyong.community.service;

import com.giyong.community.dto.AdminDto;
import com.giyong.community.entity.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminService {
    Admin addAdmin(AdminDto adminDto);

    Admin modify(AdminDto adminDto);

    Page<Admin> findAll(Pageable pageable);

    Admin findById(Long id);

    Admin findByAdminId(String adminId);

    void remove(Long id);
}
