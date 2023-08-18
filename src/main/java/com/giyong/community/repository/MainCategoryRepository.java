package com.giyong.community.repository;

import com.giyong.community.entity.MainCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainCategoryRepository extends JpaRepository<MainCategory, Integer> {
    MainCategory findByMainCategoryName(String mainCategoryName);
}
