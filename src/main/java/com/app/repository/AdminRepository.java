package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByUsername(String username);

}

