package com.likelion.mission06.repository;

import com.likelion.mission06.domain.Lion;

import java.util.List;
import java.util.Optional;

public interface LionRepository {
    void save(Lion lion);
    Optional<Lion> findByName(String name);
    List<Lion> findAll();
    List<Lion> findByPart(String part);
    boolean delete(String name);
}
