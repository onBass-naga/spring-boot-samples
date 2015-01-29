package com.example.app.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by naga on 2015/01/29.
 */
interface UserRepository extends JpaRepository<User, Long> {
}