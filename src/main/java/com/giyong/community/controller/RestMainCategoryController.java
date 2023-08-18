package com.giyong.community.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.giyong.community.dto.MainCategoryDto;
import com.giyong.community.entity.MainCategory;
import com.giyong.community.service.MainCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/main")
public class RestMainCategoryController {
    @Autowired
    private MainCategoryService mainCategoryService;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/categorys/dupCheck")
    public String dupCheck(@RequestBody MainCategoryDto mainCategoryDto) throws JsonProcessingException {
        MainCategory mainCategory = mainCategoryService.findByName(mainCategoryDto.getMainCategoryName());
        return objectMapper.writeValueAsString(mainCategory);
    }
}
