package db;

import db.exceptions.DatabaseExeption;
import entities.User;

import java.util.Collection;


public interface UserDao {
    User create(User user) throws DatabaseExeption;

    void update(User user) throws DatabaseExeption;

    void detete(User user) throws DatabaseExeption;

    User find(Long id) throws DatabaseExeption;

    Collection findAll() throws DatabaseExeption;

    void setConnectionFactory(ConnectionFactory connectionFactory);
}
