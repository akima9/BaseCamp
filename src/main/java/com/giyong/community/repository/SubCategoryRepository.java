package com.giyong.community.repository;

import com.giyong.community.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    Optional<SubCategory> findBySubCategoryName(String subCategoryName);
}
