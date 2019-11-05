package db;

import db.exceptions.DatabaseExeption;
import db.templates.DBTemplate;
import entities.User;

import java.sql.*;
import java.util.Collection;
import java.util.LinkedList;

class HsqldbUserDao extends DBTemplate implements UserDao {
    private static final String INSERT_USER_SQL = "INSERT INTO users (firstname, lastname, dateofbirth) VALUES (?, ?, ?)";
    private static final String UPDATE_USER_SQL = "UPDATE users SET firstname=?, lastname=?, dateofbirth=? WHERE ID=?";
    private static final String DELETE_USER_SQL = "DELETE FROM users WHERE id=?";
    private static final String SELECT_ALL_SQL = "SELECT id, firstname, lastname, dateofbirth FROM users";
    private static final String GET_BY_ID_SQL = "SELECT id, firstname, lastname, dateofbirth FROM users WHERE id=?";
    private ConnectionFactory connectionFactory;

    public HsqldbUserDao(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public HsqldbUserDao() {

    }

    public ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }

    public void setConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public User create(User user) throws DatabaseExeption {
        Connection connection = null;
        PreparedStatement statement = null;
        CallableStatement callableStatement = null;
        ResultSet keys = null;
        try {
            connection = connectionFactory.createConnection();
            statement = connection.prepareStatement(INSERT_USER_SQL);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setDate(3, new Date(user.getDateOfBirth().getTime()));
            int n = statement.executeUpdate();
            if (n != 1) {
                throw new DatabaseExeption("Number of the inserted rows: " + n);
            }
            callableStatement = connection.prepareCall("call IDENTITY()");
            keys = callableStatement.executeQuery();
            if (keys.next()) {
                user.setId(keys.getLong(1));
            }
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseExeption();
        } finally {
            resultSetClose(keys);
            callableStatementClose(callableStatement);
            statementClose(statement);
            connectionClose(connection);
        }
    }

    @Override
    public void update(User user) throws DatabaseExeption {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionFactory.createConnection();
            statement = connection.prepareStatement(UPDATE_USER_SQL);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setDate(3, new Date(user.getDateOfBirth().getTime()));
            statement.setLong(4, user.getId());
            int n = statement.executeUpdate();
            if (n != 1) {
                throw new DatabaseExeption("Number of the inserted rows: " + n);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseExeption();
        } finally {
            statementClose(statement);
            connectionClose(connection);
        }
    }


    @Override
    public void detete(User user) throws DatabaseExeption {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = connectionFactory.createConnection();
            statement = connection.prepareStatement(DELETE_USER_SQL);
            statement.setLong(1, user.getId());
            int n = statement.executeUpdate();
            if (n != 1) {
                throw new DatabaseExeption("Number of the inserted rows: " + n);
            }

        } catch (SQLException e) {
            throw new DatabaseExeption();
        } finally {
            statementClose(statement);
            connectionClose(connection);
        }
    }

    @Override
    public User find(Long id) throws DatabaseExeption {
        User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionFactory.createConnection();
            statement = connection.prepareStatement(GET_BY_ID_SQL);
            statement.setInt(1, id.intValue());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setDateOfBirth(resultSet.getDate(4));
            }
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseExeption();
        } finally {
            resultSetClose(resultSet);
            statementClose(statement);
            connectionClose(connection);
        }
    }

    @Override
    public Collection findAll() throws DatabaseExeption {
        Collection result = new LinkedList();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionFactory.createConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_SQL);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setFirstName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setDateOfBirth(resultSet.getDate(4));
                result.add(user);
            }
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseExeption();
        } finally {
            resultSetClose(resultSet);
            statementClose(statement);
            connectionClose(connection);
        }
    }
}
