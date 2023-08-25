package com.giyong.community.service;

import com.giyong.community.dto.*;
import com.giyong.community.entity.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class BoardServiceImplTest {
    @Autowired
    private BoardService boardService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private MainCategoryService mainCategoryService;
    @Autowired
    private SubCategoryService subCategoryService;
    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("게시글 작성")
    @Order(1)
    void write() {
        AdminDto adminDto = new AdminDto();
        adminDto.setAdminId("admin");
        adminDto.setAdminName("giyong");
        adminDto.setAdminPw("1111");
        Admin admin = adminService.addAdmin(adminDto);

        MainCategoryDto mainCategoryDto = new MainCategoryDto();
        mainCategoryDto.setMainCategoryName("드라마");
        mainCategoryDto.setAdminId(admin.getId());
        MainCategory mainCategory = mainCategoryService.save(mainCategoryDto);

        SubCategoryDto subCategoryDto = new SubCategoryDto();
        subCategoryDto.setSubCategoryName("마스크걸");
        subCategoryDto.setMainCategoryId(mainCategory.getMainCategoryId());
        subCategoryDto.setAdminId(admin.getId());
        SubCategory subCategory = subCategoryService.save(subCategoryDto);

        SubCategoryDto subCategoryDto1 = new SubCategoryDto();
        subCategoryDto1.setSubCategoryName("더글로리");
        subCategoryDto1.setMainCategoryId(mainCategory.getMainCategoryId());
        subCategoryDto1.setAdminId(admin.getId());
        subCategoryService.save(subCategoryDto1);

        MemberDto memberDto = new MemberDto();
        memberDto.setMemberId("member1");
        memberDto.setMemberPw("1234");
        Member member = memberService.addMember(memberDto);

        BoardDto boardDto = new BoardDto();
        boardDto.setTitle("마스크걸 봤냐?");
        boardDto.setContent("안봤냐?");
        boardDto.setMemberId(member.getId());
        boardDto.setSubCategoryId(subCategory.getSubCategoryId());
        Board board = boardService.write(boardDto);

        assertTrue(board.getTitle().equals(boardDto.getTitle()));
    }

    @Test
    @DisplayName("게시글 수정")
    @Order(2)
    void modify() {
        BoardDto boardDto = new BoardDto();
        boardDto.setBoardId(1L);
        boardDto.setTitle("더글로리 봤냐?");
        boardDto.setSubCategoryId(2L);
        boardDto.setMemberId(1L);
        Board board = boardService.modify(boardDto);

        assertTrue(board.getTitle().equals(boardDto.getTitle()));
        assertTrue(board.getSubCategory().getSubCategoryName().equals("더글로리"));
    }

    @Test
    @DisplayName("게시글 목록")
    @Order(4)
    void findAll() {
        Page<Board> boards = boardService.findAll(Pageable.unpaged());
        assertTrue(boards.getTotalElements() == 1);
    }

    @Test
    @DisplayName("아이디로 게시글 가져오기")
    @Order(3)
    void findById() {
        Board board = boardService.findById(1L);
        assertTrue(board.getTitle().equals("더글로리 봤냐?"));
        assertTrue(board.getMember().getMemberId().equals("member1"));
        assertTrue(board.getSubCategory().getSubCategoryName().equals("더글로리"));
    }

    @Test
    void remove() {
    }

    @Test
    void upViewCount() {
    }

    @Test
    void hasUserViewedPost() {
    }

    @Test
    void findCommentCount() {
    }
}