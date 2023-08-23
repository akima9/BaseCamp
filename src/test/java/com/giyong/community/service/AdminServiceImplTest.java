package com.giyong.community.service;

import com.giyong.community.dto.AdminDto;
import com.giyong.community.entity.Admin;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class AdminServiceImplTest {
    @Autowired
    private AdminService adminService;

    @BeforeAll
    static void addTempAdmin(@Autowired AdminService adminService) {
        AdminDto adminDto = new AdminDto();
        adminDto.setAdminId("tempAdmin");
        adminDto.setAdminName("giyong");
        adminDto.setAdminPw("1234");
        adminService.addAdmin(adminDto);
    }

    @Test
    @Order(1)
    void addAdmin() {
        AdminDto adminDto = new AdminDto();
        adminDto.setAdminId("admin");
        adminDto.setAdminName("giyong");
        adminDto.setAdminPw("1234");
        Admin admin = adminService.addAdmin(adminDto);
        assertTrue(admin.getAdminId().equals(adminDto.getAdminId()));
    }

    @Test
    @Order(2)
    void modify() {
        AdminDto adminDto = new AdminDto();
        adminDto.setId(2L);
        adminDto.setAdminName("jihye");
        adminDto.setAdminPw("4321");
        Admin admin = adminService.modify(adminDto);
        assertTrue(admin.getAdminName().equals(adminDto.getAdminName()));
    }

    @Test
    void findAll() {
        Page<Admin> admins = adminService.findAll(Pageable.unpaged());
        assertTrue(admins.getTotalElements() == 1);
    }

    @Test
    @Order(3)
    void findById() {
        Admin admin = adminService.findById(2L);
        assertTrue(admin.getAdminName().equals("jihye"));
    }

    @Test
    @Order(4)
    void remove() {
        adminService.remove(1L);
        Admin admin = adminService.findById(1L);
        assertTrue(admin == null);
    }
}