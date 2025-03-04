package com.kairos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kairos.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
