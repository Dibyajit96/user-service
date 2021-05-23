package com.kueblearn.user_service.repos;

import com.kueblearn.user_service.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {
}
