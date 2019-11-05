import db.ConnectionFactory;
import db.ConnectionFactoryImpl;
import db.DaoFactory;
import db.UserDao;
import db.exceptions.DatabaseExeption;
import entities.User;
import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

import java.sql.Date;
import java.util.Collection;

public class HsqldbUserDaoTest extends DatabaseTestCase {
    private UserDao dao;
    private ConnectionFactory connectionFactory;

    @Override
    protected IDatabaseConnection getConnection() throws Exception {
        connectionFactory = new ConnectionFactoryImpl("org.hsqldb.jdbcDriver", "jdbc:hsqldb:file:db/usermanagement", "sa", "");
        return new DatabaseConnection(connectionFactory.createConnection());
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new XmlDataSet(getClass().getClassLoader()
                .getResourceAsStream("resources/usersDataSet.xml"));
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        dao = DaoFactory.getInstance().getUserDao();
    }


    public void testFindAll() {
        try {
            Collection collection = dao.findAll();
            assertNotNull("Collection is null", collection);
            assertEquals("Collection size", 2, collection.size());
        } catch (DatabaseExeption e) {
            e.printStackTrace();
            fail(e.toString());
        }
    }

    public void testCreate() {
        try {
            User user = new User();
            user.setFirstName("Ivan");
            user.setLastName("Ivanov");
            user.setDateOfBirth(new Date(System.currentTimeMillis()));
            assertNull(user.getId());
            user = dao.create(user);
            assertNotNull(user);
            assertNotNull(user.getId());
        } catch (DatabaseExeption e) {
            e.printStackTrace();
            fail(e.toString());
        }
    }

    public void testUpdate() {
        try {
            User user = new User();
            user.setFirstName("Ivan");
            user.setLastName("Ivanov");
            user.setId(12L);
            user.setDateOfBirth(new Date(System.currentTimeMillis()));
            dao.create(user);
            assertNotNull(user);
            user.setFirstName("Misha");
            user.setLastName("Mihalkov");
            dao.update(user);
            User userTest = dao.find(user.getId());
            assertNotNull(userTest);
            assertEquals("First name equals", user.getFirstName(), userTest.getFirstName());
            assertEquals("Last name equals", user.getLastName(), userTest.getLastName());
            assertEquals("Id equals", user.getId(), userTest.getId());
            assertEquals("Date of birth equals", user.getDateOfBirth().toString(), userTest.getDateOfBirth().toString());
        } catch (DatabaseExeption e) {
            e.printStackTrace();
            fail(e.toString());
        }
    }

    public void testDelete() {
        try {
            User user = new User();
            user.setFirstName("Ivan");
            user.setLastName("Ivanov");
            user.setId(12L);
            user.setDateOfBirth(new Date(System.currentTimeMillis()));
            dao.create(user);
            dao.detete(user);
            assertNull(dao.find(user.getId()));
        } catch (DatabaseExeption e) {
            e.printStackTrace();
            fail(e.toString());
        }

    }

    public void testFind() {
        try {
            User user = new User();
            user.setFirstName("Ivan");
            user.setLastName("Ivanov");
            user.setId(12L);
            user.setDateOfBirth(new Date(System.currentTimeMillis()));
            dao.create(user);
            User userTest = dao.find(user.getId());
            assertNotNull(userTest);
            assertEquals("First name equals", user.getFirstName(), userTest.getFirstName());
            assertEquals("Last name equals", user.getLastName(), userTest.getLastName());
            assertEquals("Id equals", user.getId(), userTest.getId());
            assertEquals("Date of birth equals", user.getDateOfBirth().toString(), userTest.getDateOfBirth().toString());

        } catch (DatabaseExeption e) {
            e.printStackTrace();
            fail(e.toString());
        }

    }
}
