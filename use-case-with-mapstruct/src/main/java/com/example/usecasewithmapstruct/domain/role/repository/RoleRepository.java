package com.example.usecasewithmapstruct.domain.role.repository;

import com.example.usecasewithmapstruct.domain.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

}
