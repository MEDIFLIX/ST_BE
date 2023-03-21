package com.dashboardbe.api.repository;

import com.dashboardbe.domain.Admin;
import com.dashboardbe.domain.Todolist;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodolistRepository extends JpaRepository<Todolist, Long> {
    List findByAdminId(String adminId);
}
