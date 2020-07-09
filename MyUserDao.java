package ie.conor.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import ie.conor.entities.MyUser;

public interface MyUserDao extends JpaRepository<MyUser, Integer> {
	MyUser findByUserEmail(String email);
	boolean existsByUserEmail(String email);
}
