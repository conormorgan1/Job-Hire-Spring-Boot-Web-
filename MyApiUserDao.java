package ie.conor.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.conor.entities.MyApiUser;

public interface MyApiUserDao extends JpaRepository<MyApiUser, Integer> {
	boolean existsByUserEmail(String email);
}
