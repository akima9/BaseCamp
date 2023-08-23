package com.giyong.community.service;

import com.giyong.community.dto.MainCategoryDto;
import com.giyong.community.entity.MainCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MainCategoryService {
    MainCategory save(MainCategoryDto mainCategoryDto);

    MainCategory modify(MainCategoryDto mainCategoryDto);

    Page<MainCategory> findAll(Pageable pageable);

    MainCategory findById(Long mainCategoryId);

    MainCategory findByName(String mainCategoryName);

    void remove(Long mainCategoryId);
}
