package com.annalohvinenko.usermanagement.db;

import junit.framework.TestCase;
import com.annalohvinenko.usermanagement.db.DaoFactory;
import com.annalohvinenko.usermanagement.db.UserDao;

public class DaoFactoryTest extends TestCase {

    public void testGetUserDao() {
        try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			assertNotNull("DaoFactory is null",daoFactory);
			UserDao userDao = daoFactory.getUserDao();
			assertNotNull("UserDao is null",userDao);
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.toString());
		}
    }
}