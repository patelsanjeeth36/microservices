package com.sanjeeth.rest.webservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanjeeth.rest.webservices.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
