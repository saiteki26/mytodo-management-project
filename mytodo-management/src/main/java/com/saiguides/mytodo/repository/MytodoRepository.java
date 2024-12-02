package com.saiguides.mytodo.repository;

import com.saiguides.mytodo.entity.Mytodo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MytodoRepository extends JpaRepository<Mytodo, Long> {
}
