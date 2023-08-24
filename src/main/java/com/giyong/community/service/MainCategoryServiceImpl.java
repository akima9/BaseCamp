package com.giyong.community.service;

import com.giyong.community.dto.MainCategoryDto;
import com.giyong.community.entity.MainCategory;
import com.giyong.community.repository.MainCategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MainCategoryServiceImpl implements MainCategoryService{
    @Autowired
    private MainCategoryRepository mainCategoryRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public MainCategory save(MainCategoryDto mainCategoryDto) {
        MainCategory mainCategory = modelMapper.map(mainCategoryDto, MainCategory.class);

        return mainCategoryRepository.save(mainCategory);
    }

    @Override
    public MainCategory modify(MainCategoryDto mainCategoryDto) {
        MainCategory oldMainCategory = mainCategoryRepository.findById(mainCategoryDto.getMainCategoryId()).orElse(null);

        MainCategoryDto dto = modelMapper.map(oldMainCategory, MainCategoryDto.class);
        dto.setMainCategoryName(mainCategoryDto.getMainCategoryName());

        MainCategory modifyMainCategory = modelMapper.map(dto, MainCategory.class);

        return mainCategoryRepository.save(modifyMainCategory);
    }

    @Override
    public Page<MainCategory> findAll(Pageable pageable) {
        return (Page<MainCategory>) mainCategoryRepository.findAll(pageable);
    }

    @Override
    public MainCategory findById(Long mainCategoryId) {
        return mainCategoryRepository.findById(mainCategoryId).orElse(null);
    }

    @Override
    public MainCategory findByName(String mainCategoryName) {
        return mainCategoryRepository.findByMainCategoryName(mainCategoryName);
    }

    @Override
    public void remove(Long mainCategoryId) {
        mainCategoryRepository.deleteById(mainCategoryId);
    }
}
