package com.annalohvinenko.usermanagement.gui;

import com.annalohvinenko.usermanagement.User;
import com.annalohvinenko.usermanagement.db.DaoFactory;


import com.mockobjects.dynamic.Mock;
import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.TestHelper;
import junit.extensions.jfcunit.finder.NamedComponentFinder;

import java.awt.Component;
import java.util.ArrayList;

import java.util.Properties;

public class MainFrameTest extends JFCTestCase {
 

    private MainFrame mainFrame;
    private Mock mockUserDao;
    private ArrayList<User> users;

    @Override
    protected void setUp() throws Exception {
        // TODO Auto-generated method stub
        super.setUp();
        try {
            Properties properties = new Properties();
            DaoFactory.init(properties);
            users = new ArrayList<User>();
            setHelper(new JFCTestHelper());
            mainFrame = new MainFrame();
        }catch(Exception e) {
            e.printStackTrace();
        }
        mainFrame.setVisible(true);
    }

    @Override
    protected void tearDown() throws Exception {
        try {
            super.tearDown();
            mockUserDao.verify();
//				mainFrame.setVisible(false);
            getHelper();
            TestHelper.cleanUp(this);
        } catch (Exception e){
            e.printStackTrace();
        }
    }//done

    private Component find(Class<?> componentClass, String name) {
        NamedComponentFinder finder;
        finder = new NamedComponentFinder(componentClass, name);
        finder.setWait(0);
        Component component = finder.find(mainFrame, 0);
        assertNotNull("Could not find component '" + name + "'", component);
        return component;
    }//done
}
