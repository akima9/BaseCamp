package com.giyong.community.service;

import com.giyong.community.dto.SubCategoryDto;
import com.giyong.community.entity.SubCategory;
import com.giyong.community.repository.SubCategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class SubCategoryServiceImpl implements SubCategoryService{
    @Autowired
    private SubCategoryRepository subCategoryRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    @Transactional
    public SubCategory save(SubCategoryDto subCategoryDto) {
        SubCategory subCategory = modelMapper.map(subCategoryDto, SubCategory.class);

        return subCategoryRepository.save(subCategory);
    }

    @Override
    @Transactional
    public SubCategory modify(SubCategoryDto subCategoryDto) {
        SubCategory oldSubCategory = subCategoryRepository.findById(subCategoryDto.getSubCategoryId()).orElse(null);

        SubCategoryDto dto = modelMapper.map(oldSubCategory, SubCategoryDto.class);

        if (subCategoryDto.getMainCategoryId() != null) {
            dto.setMainCategoryId(subCategoryDto.getMainCategoryId());
        }
        if (subCategoryDto.getSubCategoryName() != null) {
            dto.setSubCategoryName(subCategoryDto.getSubCategoryName());
        }

        SubCategory modifySubCategory = modelMapper.map(dto, SubCategory.class);
        System.out.println("modifySubCategory = " + modifySubCategory);

        return subCategoryRepository.save(modifySubCategory);
    }

    @Override
    public Page<SubCategory> findAll(Pageable pageable) {
        return (Page<SubCategory>) subCategoryRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public SubCategory findById(Long subCategoryId) {
        return subCategoryRepository.findById(subCategoryId).orElse(null);
    }

    @Override
    public SubCategory findByName(String subCategoryName) {
        return subCategoryRepository.findBySubCategoryName(subCategoryName).orElse(null);
    }

    @Override
    public void remove(Long subCategoryId) {
        subCategoryRepository.deleteById(subCategoryId);
    }
}
