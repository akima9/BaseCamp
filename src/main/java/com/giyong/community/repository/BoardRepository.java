package com.giyong.community.repository;

import com.giyong.community.entity.Board;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Board, Integer> {
}
