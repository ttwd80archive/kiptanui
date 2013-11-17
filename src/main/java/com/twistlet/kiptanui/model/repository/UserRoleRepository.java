package com.twistlet.kiptanui.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twistlet.kiptanui.model.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

	List<UserRole> findByUserId(String id);

}
