package com.giyong.community.service;

import com.giyong.community.dto.SubCategoryDto;
import com.giyong.community.entity.SubCategory;
import com.giyong.community.repository.SubCategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SubCategoryServiceImpl implements SubCategoryService{
    @Autowired
    private SubCategoryRepository subCategoryRepository;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public SubCategory save(SubCategoryDto subCategoryDto) {
        subCategoryDto.setCreatedAt(new Date());
        subCategoryDto.setUpdatedAt(new Date());

        SubCategory subCategory = modelMapper.map(subCategoryDto, SubCategory.class);

        return subCategoryRepository.save(subCategory);
    }

    @Override
    public SubCategory modify(SubCategoryDto subCategoryDto) {
        SubCategory oldSubCategory = subCategoryRepository.findById(subCategoryDto.getSubCategoryId()).orElse(null);

        SubCategoryDto dto = modelMapper.map(oldSubCategory, SubCategoryDto.class);
        dto.setMainCategoryId(subCategoryDto.getMainCategoryId());
        dto.setSubCategoryName(subCategoryDto.getSubCategoryName());
        dto.setUpdatedAt(new Date());

        SubCategory modifySubCategory = modelMapper.map(dto, SubCategory.class);

        return subCategoryRepository.save(modifySubCategory);
    }

    @Override
    public Page<SubCategory> findAll(Pageable pageable) {
        return (Page<SubCategory>) subCategoryRepository.findAll(pageable);
    }

    @Override
    public SubCategory findById(Integer subCategoryId) {
        return subCategoryRepository.findById(subCategoryId).orElse(null);
    }

    @Override
    public SubCategory findByName(String subCategoryName) {
        return subCategoryRepository.findBySubCategoryName(subCategoryName).orElse(null);
    }

    @Override
    public void remove(Integer subCategoryId) {
        subCategoryRepository.deleteById(subCategoryId);
    }
}
