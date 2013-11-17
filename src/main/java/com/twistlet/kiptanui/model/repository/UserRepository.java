package com.twistlet.kiptanui.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twistlet.kiptanui.model.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
