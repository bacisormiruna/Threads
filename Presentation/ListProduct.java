package Presentation;

import BusinessLogic.ClientBll;
import BusinessLogic.ProductsBll;
import DataAccess.ProductsDAO;
import Model.Client;
import Model.Products;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import static DataAccess.AbstractDAO.setField;

public class ListProduct extends JFrame {//afisarea in tabel a produselor

    private JTable productTable;
    private DefaultTableModel tableModel;

    public ListProduct() {
       /* setTitle("Product List");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] columnNames = {"ID", "Name", "Price","Quantity"};
        tableModel = new DefaultTableModel(columnNames, 0);
        productTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);

        add(scrollPane, BorderLayout.CENTER);

        loadProducts();

        setVisible(true);*/
        setTitle("Product List");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        productTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(productTable);
        add(scrollPane, BorderLayout.CENTER);

        ProductsBll clientBll = new ProductsBll();
        List<Products> clientList = clientBll.selectall();

        setField(clientList, productTable);
        setVisible(true);
    }
    private void loadProducts() {
        ProductsBll productsBll = new ProductsBll();
        List<Products> productList = productsBll.selectall();

        for (Products product : productList) {
            Object[] rowData = {product.getId(), product.getNameProduct(), product.getPrice(), product.getQuantity()};
            tableModel.addRow(rowData);
        }
    }
}