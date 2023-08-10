package com.giyong.community.repository;

import com.giyong.community.entity.Board;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BoardRepository extends PagingAndSortingRepository<Board, Integer> {
}
