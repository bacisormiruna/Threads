package ro.tuc;

//am comentat 3 clase cu javadoc dar mai am de generat acele fisiere

import BusinessLogic.ClientBll;
import Model.Client;
import Presentation.Menu;
import Presentation.View;
import Connection.ConnectionFactory;

import java.sql.*;
import static Connection.ConnectionFactory.getConnection;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection=getConnection();
            System.out.println("Connected to the database!");
            new Menu();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}