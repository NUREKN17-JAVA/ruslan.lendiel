package db;

import db.exceptions.DatabaseExeption;

import java.sql.Connection;

public interface ConnectionFactory {
    Connection createConnection() throws DatabaseExeption;
}
