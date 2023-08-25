package com.giyong.community.service;

import com.giyong.community.dto.AdminDto;
import com.giyong.community.dto.MainCategoryDto;
import com.giyong.community.dto.SubCategoryDto;
import com.giyong.community.entity.Admin;
import com.giyong.community.entity.MainCategory;
import com.giyong.community.entity.SubCategory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class SubCategoryServiceImplTest {
    @Autowired
    private SubCategoryService subCategoryService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private MainCategoryService mainCategoryService;

    @Test
    @DisplayName("서브 카테고리 생성")
    @Order(1)
    void save() {
        AdminDto adminDto = new AdminDto();
        adminDto.setAdminId("admin");
        adminDto.setAdminName("giyong");
        adminDto.setAdminPw("1234");
        Admin admin = adminService.addAdmin(adminDto);

        MainCategoryDto mainCategoryDto = new MainCategoryDto();
        mainCategoryDto.setMainCategoryName("singer");
        mainCategoryDto.setAdminId(admin.getId());

        MainCategory mainCategory = mainCategoryService.save(mainCategoryDto);

        SubCategoryDto subCategoryDto = new SubCategoryDto();
        subCategoryDto.setSubCategoryName("아이유");
        subCategoryDto.setMainCategoryId(1L);
        subCategoryDto.setAdminId(1L);
        SubCategory subCategory = subCategoryService.save(subCategoryDto);
        assertTrue(subCategory.getSubCategoryName().equals(subCategoryDto.getSubCategoryName()));
    }

    @Test
    @DisplayName("서브 카테고리 수정")
    @Order(3)
    void modify() {
        SubCategoryDto subCategoryDto = new SubCategoryDto();
        subCategoryDto.setSubCategoryId(1L);
        subCategoryDto.setSubCategoryName("소녀시대");
        SubCategory subCategory = subCategoryService.modify(subCategoryDto);
        assertTrue(subCategory.getSubCategoryId() == 1);
        assertTrue(subCategory.getSubCategoryName().equals(subCategoryDto.getSubCategoryName()));
        assertTrue(subCategory.getMainCategory().getMainCategoryName().equals("singer"));
        assertTrue(subCategory.getAdmin().getAdminName().equals("giyong"));
    }

    @Test
    @DisplayName("서브 카테고리 목록 가져오기")
    @Order(4)
    void findAll() {
        Page<SubCategory> subCategories = subCategoryService.findAll(Pageable.unpaged());
        assertTrue(subCategories.getTotalElements() == 1);
    }

    @Test
    @DisplayName("아이디로 서브 카테고리 가져오기")
    @Order(2)
    void findById() {
        SubCategory subCategory = subCategoryService.findById(1L);
        assertTrue(subCategory.getSubCategoryName().equals("아이유"));
        assertTrue(subCategory.getAdmin().getAdminName().equals("giyong"));
        assertTrue(subCategory.getMainCategory().getMainCategoryName().equals("singer"));
    }

    @Test
    @DisplayName("서브 카테고리명으로 서브 카테고리 가져오기")
    @Order(3)
    void findByName() {
        SubCategory subCategory = subCategoryService.findByName("소녀시대");
        assertTrue(subCategory.getSubCategoryId() == 1);
        assertTrue(subCategory.getAdmin().getAdminName().equals("giyong"));
        assertTrue(subCategory.getMainCategory().getMainCategoryName().equals("singer"));
    }

    @Test
    @DisplayName("서브 카테고리 삭제")
    @Order(5)
    void remove() {
        subCategoryService.remove(1L);
        SubCategory subCategory = subCategoryService.findById(1L);
        assertTrue(subCategory == null);
    }
}