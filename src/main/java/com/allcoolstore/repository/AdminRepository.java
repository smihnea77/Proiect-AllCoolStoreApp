package com.allcoolstore.repository;

import com.allcoolstore.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepository extends JpaRepository<Admin, Long> {
}
