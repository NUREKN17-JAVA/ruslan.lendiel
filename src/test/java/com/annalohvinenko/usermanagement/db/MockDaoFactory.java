package com.annalohvinenko.usermanagement.db;

import com.annalohvinenko.usermanagement.db.DaoFactory;
import com.annalohvinenko.usermanagement.db.UserDao;
import com.mockobjects.dynamic.Mock;

public class MockDaoFactory extends DaoFactory {

	private Mock mockUserDao;
	
	public MockDaoFactory() {
		mockUserDao = new Mock(UserDao.class);
	}

	public Mock getMockUserDao() {
	       return mockUserDao;
	}
	    
	public UserDao getUserDao() {
	       return (UserDao) mockUserDao.proxy();
	}

}