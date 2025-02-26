package DataAccess;

import Model.Bill;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {

    private final String url = "jdbc:mysql://localhost:3306/management";
    private final String user = "root";
    private final String password = "MirunaDb06!";

    public void insertBill(Bill bill) {
        String query = "INSERT INTO Log (id, idOrder, clientName, nameProduct, quantity, totalPrice, date) VALUES (?,?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, bill.id());
            statement.setInt(2, bill.idOrder());
            statement.setString(3, bill.clientName());
            statement.setString(4, bill.productName());
            statement.setInt(5, bill.quantity());
            statement.setDouble(6, bill.totalPrice());
            statement.setTimestamp(7, Timestamp.valueOf(bill.date()));

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Bill> getAllBills() {
        List<Bill> bills = new ArrayList<>();
        String query = "SELECT * FROM Log";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idOrder = resultSet.getInt("idOrder");
                String clientName = resultSet.getString("clientName");
                String productName = resultSet.getString("nameProduct");
                int quantity = resultSet.getInt("quantity");
                double totalPrice = resultSet.getDouble("totalPrice");
                LocalDateTime date = resultSet.getTimestamp("date").toLocalDateTime();

                Bill bill = new Bill(id, idOrder, clientName, productName, quantity, totalPrice, date);
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bills;
    }}