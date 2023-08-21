package com.giyong.community.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.giyong.community.dto.MainCategoryDto;
import com.giyong.community.dto.SubCategoryDto;
import com.giyong.community.entity.MainCategory;
import com.giyong.community.entity.SubCategory;
import com.giyong.community.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/sub")
public class RestSubCategoryController {
    @Autowired
    private SubCategoryService subCategoryService;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/categorys/dupCheck")
    public String dupCheck(@RequestBody SubCategoryDto subCategoryDto) throws JsonProcessingException {
        SubCategory subCategory = subCategoryService.findByName(subCategoryDto.getSubCategoryName());
        return objectMapper.writeValueAsString(subCategory);
    }
}
