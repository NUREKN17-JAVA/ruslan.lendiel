import db.DaoFactory;
import db.UserDao;
import junit.framework.TestCase;

public class DaoFactoryTest extends TestCase {


    public void testGetUserDao(){
        try {
            DaoFactory daoFactory=DaoFactory.getInstance();
            assertNotNull("DaoFactory instance is null", daoFactory);
            UserDao userDao=daoFactory.getUserDao();
            assertNotNull("UserDao instance is not null", userDao);
        } catch (RuntimeException e){
            e.printStackTrace();
            fail(e.toString());
        }

    }
}
