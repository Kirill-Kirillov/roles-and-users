package com.rolesandusers.test.model.repository;

import com.rolesandusers.test.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {
   Optional<Role> findByName(String name);

   void deleteByName(String name);

   Set<Role> getRolesByUsersNotNull();
}
