package com.example.usecasewithmapstruct.domain.user.repository;

import com.example.usecasewithmapstruct.domain.role.entity.Role;
import com.example.usecasewithmapstruct.domain.user.entity.User;
import com.example.usecasewithmapstruct.domain.user.entity.UserRole;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {

  Optional<UserRole> findByUser(User user);

  Optional<UserRole> findByUserAndRole(User user, Role role);
}
