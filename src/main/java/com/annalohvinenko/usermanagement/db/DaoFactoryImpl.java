package com.annalohvinenko.usermanagement.db;

import com.annalohvinenko.usermanagement.db.DaoFactory;
import com.annalohvinenko.usermanagement.db.UserDao;

public class DaoFactoryImpl extends DaoFactory {

    @Override
    public UserDao getUserDao(){
        UserDao userDao = null;
        try {
            Class<?> daoClass = Class.forName(properties.getProperty(USER_DAO));
            userDao = (UserDao) daoClass.newInstance();
            userDao.setConnectionFactory(getConnectionFactory());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return userDao;
    }
}
