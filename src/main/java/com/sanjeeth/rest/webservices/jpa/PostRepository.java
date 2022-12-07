package com.sanjeeth.rest.webservices.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanjeeth.rest.webservices.user.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
