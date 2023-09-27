package com.giyong.community.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.giyong.community.dto.BoardDto;
import com.giyong.community.dto.BoardImagesDto;
import com.giyong.community.dto.ImageFileDto;
import com.giyong.community.dto.ImageUploadResponseDto;
import com.giyong.community.entity.Board;
import com.giyong.community.service.BoardImagesService;
import com.giyong.community.service.BoardService;
import org.apache.tomcat.util.json.JSONParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/rest")
public class RestBoardController {
    @Autowired
    private BoardService boardService;
    @Autowired
    private BoardImagesService boardImagesService;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/boards/uploadFile")
    public String imageUpload(@RequestParam("file") MultipartFile file) throws JsonProcessingException {
        String destinationPath = boardImagesService.store(file);

//        String s = "{\"success\" : 1, \"file\": {\"url\" : \"" + destinationPath + "\"}}";
        ImageFileDto imageFileDto = new ImageFileDto();
        imageFileDto.setUrl(destinationPath);

        ImageUploadResponseDto imageUploadResponseDto = new ImageUploadResponseDto();
        imageUploadResponseDto.setSuccess(1);
        imageUploadResponseDto.setFile(imageFileDto);

        return objectMapper.writeValueAsString(imageUploadResponseDto);
//        return destinationPath;
    }

    @PutMapping("/boards")
    public String boards(@RequestBody Map<String, Object> map) throws JsonProcessingException {
        Long boardId = Long.parseLong((String) map.get("boardId"));
        Board oldBoard = boardService.findById(boardId);
        map.remove("boardId");

        map.forEach((key, value) -> {
            if (key.equals("blocks")) {
                ArrayList<Map<String, String>> blocksList = (ArrayList<Map<String, String>>) value;
                for (Map block : blocksList) {
                    if (block.get("type").equals("header")) {
                        Map<String, String> data = (Map<String, String>) block.get("data");
                        oldBoard.setTitle(data.get("text"));
                        break;
                    }

                }
            }
        });
        ObjectMapper mapper = new ObjectMapper();
        oldBoard.setContent(mapper.writeValueAsString(map));
        ModelMapper modelMapper = new ModelMapper();
        BoardDto dto = modelMapper.map(oldBoard, BoardDto.class);
        Board board = boardService.modify(dto);
        return objectMapper.writeValueAsString(board);
    }

    @PostMapping("/boards")
    public String boards(@RequestBody Map<String, Object> map, BoardDto boardDto, @SessionAttribute(name = "memberId", required = false) Long memberId) throws JsonProcessingException {
        map.forEach((key, value) -> {
            if (key.equals("blocks")) {
                ArrayList<Map<String, String>> blocksList = (ArrayList<Map<String, String>>) value;
                for (Map block : blocksList) {
                    if (block.get("type").equals("header")) {
                        Map<String, String> data = (Map<String, String>) block.get("data");
                        boardDto.setTitle(data.get("text"));
                        break;
                    }

                }
            }
        });
        ObjectMapper mapper = new ObjectMapper();
        boardDto.setContent(mapper.writeValueAsString(map));
        String title = boardDto.getTitle();
        if (title == null) {
            boardDto.setTitle("제목없음");
        }
        boardDto.setMemberId(memberId);
        Long subCategoryId = Long.parseLong((String) map.get("subCategoryId"));
        boardDto.setSubCategoryId(subCategoryId);
        Board board = boardService.write(boardDto);
        return objectMapper.writeValueAsString(board);
    }

    @GetMapping("/boards/like")
    public String like(Long memberId, Long boardId) {
        System.out.println("RestBoardController.like");
        System.out.println("memberId = " + memberId);
        System.out.println("boardId = " + boardId);
        boardService.toggleLike(boardId, memberId);
        return null;
    }
}