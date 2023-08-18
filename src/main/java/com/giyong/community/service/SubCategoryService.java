package com.giyong.community.service;

import com.giyong.community.dto.SubCategoryDto;
import com.giyong.community.entity.SubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SubCategoryService {
    SubCategory save(SubCategoryDto subCategoryDto);

    SubCategory modify(SubCategoryDto subCategoryDto);

    Page<SubCategory> findAll(Pageable pageable);

    SubCategory findById(Integer subCategoryId);

    void remove(Integer subCategoryId);
}
