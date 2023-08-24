package com.giyong.community.service;

import com.giyong.community.dto.AdminDto;
import com.giyong.community.dto.MainCategoryDto;
import com.giyong.community.entity.Admin;
import com.giyong.community.entity.MainCategory;
import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class MainCategoryServiceImplTest {
    @Autowired
    private MainCategoryService mainCategoryService;
    @Autowired
    private AdminService adminService;
    private ModelMapper modelMapper;

    @BeforeAll
    static void addTempAdmin(@Autowired AdminService adminService, @Autowired MainCategoryService mainCategoryService) {
        AdminDto adminDto = new AdminDto();
        adminDto.setAdminId("admin");
        adminDto.setAdminName("giyong");
        adminDto.setAdminPw("1234");
        Admin admin = adminService.addAdmin(adminDto);

        MainCategoryDto mainCategoryDto = new MainCategoryDto();
        mainCategoryDto.setMainCategoryName("singer");
        mainCategoryDto.setAdminId(admin.getId());

        mainCategoryService.save(mainCategoryDto);
    }

    @Test
    @Order(1)
    void save() {
        Admin admin = adminService.findById(1L);

        MainCategoryDto mainCategoryDto = new MainCategoryDto();
        mainCategoryDto.setMainCategoryName("movie");
        mainCategoryDto.setAdminId(admin.getId());

        MainCategory mainCategory = mainCategoryService.save(mainCategoryDto);
        assertTrue(mainCategory.getMainCategoryName().equals(mainCategoryDto.getMainCategoryName()));
    }

    @Test
    @Order(2)
    void modify() {
        MainCategoryDto mainCategoryDto = new MainCategoryDto();
        mainCategoryDto.setMainCategoryId(1L);
        mainCategoryDto.setMainCategoryName("animation");
        MainCategory modifyMainCategory = mainCategoryService.modify(mainCategoryDto);
        assertTrue(modifyMainCategory.getMainCategoryName().equals(mainCategoryDto.getMainCategoryName()));
    }

    @Test
    @Order(6)
    void findAll() {
    }

    @Test
    @Order(3)
    void findById() {
        MainCategory mainCategory = mainCategoryService.findById(1L);
        assertTrue(mainCategory.getMainCategoryName().equals("animation"));
    }

    @Test
    @Order(4)
    void findByName() {
        MainCategory mainCategory = mainCategoryService.findByName("animation");
        assertTrue(mainCategory.getMainCategoryId() == 1L);
    }

    @Test
    @Order(5)
    void remove() {
        mainCategoryService.remove(2L);
        MainCategory mainCategory = mainCategoryService.findById(2L);
        assertTrue(mainCategory == null);
    }
}