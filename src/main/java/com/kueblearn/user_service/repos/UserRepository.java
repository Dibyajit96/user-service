package com.kueblearn.user_service.repos;

import com.kueblearn.user_service.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
