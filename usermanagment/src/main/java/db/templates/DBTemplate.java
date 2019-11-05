package db.templates;

import db.exceptions.DatabaseExeption;

import java.sql.*;

public abstract class DBTemplate {
    protected void connectionClose(Connection connection) throws DatabaseExeption {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DatabaseExeption();
            }

        }
    }

    protected void preparedStatementClose(PreparedStatement preparedStatement) throws DatabaseExeption {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new DatabaseExeption();
            }

        }
    }

    protected void statementClose(Statement statement) throws DatabaseExeption {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new DatabaseExeption();
            }

        }
    }

    protected void resultSetClose(ResultSet resultSet) throws DatabaseExeption {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DatabaseExeption();
            }

        }
    }

    protected void callableStatementClose(CallableStatement callableStatement) throws DatabaseExeption {
        if (callableStatement != null) {
            try {
                callableStatement.close();
            } catch (SQLException e) {
                throw new DatabaseExeption();
            }

        }
    }
}
