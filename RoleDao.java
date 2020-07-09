package ie.conor.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import ie.conor.entities.Role;

public interface RoleDao extends JpaRepository<Role, Integer> {
	boolean existsByUserEmail(String email);
}
