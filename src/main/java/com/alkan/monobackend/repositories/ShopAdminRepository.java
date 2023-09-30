package com.alkan.monobackend.repositories;

import com.alkan.monobackend.entities.ShopAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopAdminRepository extends JpaRepository<ShopAdmin,Integer> {
    ShopAdmin findByEmailAndPassword(String email, String password);
}
