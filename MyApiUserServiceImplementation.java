package ie.conor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.conor.dao.MyApiUserDao;
import ie.conor.entities.MyApiUser;
@Service
public class MyApiUserServiceImplementation implements MyApiUserService{

	@Autowired
	private MyApiUserDao myApiUserDao;
	
	@Override
	public MyApiUser save(MyApiUser newApiUser) {
		if (myApiUserDao.existsByUserEmail(newApiUser.getUserEmail()))
				return null;
		return myApiUserDao.save(newApiUser);
	}
	
	
}
