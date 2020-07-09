package ie.conor.services;

import ie.conor.entities.MyUser;

public interface MyUserService {
	MyUser save(MyUser newApiUser);
	MyUser findByEmail(String email);
}
