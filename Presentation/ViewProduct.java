package Presentation;

import BusinessLogic.LoginA;
import BusinessLogic.OrdersBll;
import BusinessLogic.ProductsBll;
import Model.Orders;
import Model.Products;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewProduct extends JFrame {
    private JPanel mainPanel;
    private JTextField productNameField;
    private JTextField quantityField;
    private JTextField priceField;
    private JButton insertButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton findButton;
    private JTextField idField;

    private ProductsBll productBll;

    public ViewProduct() {
        initComponents();

        productBll = new ProductsBll();

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!LoginA.isAuthenticated()) {
                    JOptionPane.showMessageDialog(null, "You must be logged in as a manager to insert a product.");
                    return;
                }

                String productName = productNameField.getText();
                int id = Integer.parseInt(idField.getText().trim());
                double price = Double.parseDouble(priceField.getText());
                int quantity= Integer.parseInt(quantityField.getText().trim());
                if (productName.isEmpty() || quantity==0 || id==0 || (price<0.0001)) {
                    JOptionPane.showMessageDialog(null, "All fields must be filled out to insert a product.");
                    return;
                }
                Products product = new Products(productName, quantity, price);
                productBll.insert(product);
                JOptionPane.showMessageDialog(null, "Product added successfully");
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!LoginA.isAuthenticated()) {
                        JOptionPane.showMessageDialog(null, "You must be logged in as a manager to insert a product.");
                        return;
                    }
                    String productName = productNameField.getText();
                    int id = Integer.parseInt(idField.getText().trim());
                    double price = Double.parseDouble(priceField.getText());
                    int quantity= Integer.parseInt(quantityField.getText().trim());
                    if (id <= 0) {
                        JOptionPane.showMessageDialog(null, "A valid ID for product must be provided.");
                        return;
                    }

                    if (productName.isEmpty() && id==0 && quantity==0&& price<0.0001) {
                        JOptionPane.showMessageDialog(null, "At least one field must be filled out to update a product.");
                        return;
                    }

                    Products existingProduct = productBll.findClientById(id);
                    if (existingProduct == null) {
                        JOptionPane.showMessageDialog(null, "Product not found.");
                        return;
                    }

                    if (!productName.isEmpty()) {
                        existingProduct.setNameProduct(productName);
                    }
                    if (id>=0) {
                        existingProduct.setId(id);
                    }
                    if (quantity>=0) {
                        existingProduct.setQuantity(quantity);
                    }
                    productBll.update(existingProduct);
                    JOptionPane.showMessageDialog(null, "Product updated successfully");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid ID format.");
                }
            }
        });


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!LoginA.isAuthenticated()) {
                    JOptionPane.showMessageDialog(null, "You must be logged in as a manager to insert a product.");
                    return;
                }
                try{
                    int idClient = Integer.parseInt(idField.getText());
                    Products product = new Products();
                    product.setId(idClient);
                    productBll.delete(product);
                    JOptionPane.showMessageDialog(null, "Product removed successfully");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid ID format.");
                }
            }
        });

        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idClient = Integer.parseInt(idField.getText().trim());
                    Products product = productBll.findClientById(idClient);
                    if (product != null) {
                        new FindDetailsProduct(product).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Order not found");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid ID format.");
                }
            }
        });

        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setVisible(true);
    }

    private void initComponents() {
        mainPanel = new JPanel();
        productNameField = new JTextField(20);
        quantityField = new JTextField(20);
        priceField = new JTextField(20);
        idField = new JTextField(20);
        insertButton = new JButton("Insert");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        findButton = new JButton("Find");

        mainPanel.setBackground(new Color(144, 238, 144)); // Light green color

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(new JLabel("ID:"));
        mainPanel.add(idField);
        mainPanel.add(new JLabel("Product Name:"));
        mainPanel.add(productNameField);
        mainPanel.add(new JLabel("Price:"));
        mainPanel.add(priceField);
        mainPanel.add(new JLabel("Quantity:"));
        mainPanel.add(quantityField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        buttonPanel.add(insertButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(findButton);

        mainPanel.add(buttonPanel);

    }
}
