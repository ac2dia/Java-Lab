package com.example.usecasewithmapstruct.domain.user.repository;

import com.example.usecasewithmapstruct.domain.role.entity.Role;
import com.example.usecasewithmapstruct.domain.user.entity.User;
import com.example.usecasewithmapstruct.domain.user.entity.UserRole;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {

  List<UserRole> findByUser(User user);

  boolean existsByUserAndRole(User user, Role role);
}
