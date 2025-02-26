package Presentation;

import BusinessLogic.BillBll;
import BusinessLogic.ClientBll;
import BusinessLogic.OrdersBll;
import BusinessLogic.ProductsBll;
import Model.Client;
import Model.Orders;
import Model.Products;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.NoSuchElementException;

public class ViewOrder extends JFrame {
    private JPanel mainPanel;
    private JComboBox<String> productComboBox;
    private JComboBox<String> clientComboBox;
    private JTextField quantityField;
    private JButton insertButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton findButton;
    private JTextField idField;

    private OrdersBll orderBll;
    private ProductsBll productBll;
    private ClientBll clientBll;
    private BillBll billBll;

    public ViewOrder() {
        initComponents();

        orderBll = new OrdersBll();
        productBll = new ProductsBll();
        clientBll = new ClientBll();
        billBll = new BillBll();

        loadProducts();
        loadClients();

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText().trim());
                String productName = (String) productComboBox.getSelectedItem();
                String clientName = (String) clientComboBox.getSelectedItem();
                int quantity = Integer.parseInt(quantityField.getText().trim());


                if (productName.isEmpty() || quantity == 0 || clientName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields must be filled out to insert an order.");
                    return;
                }

                // Verifică stocul disponibil pentru produsul selectat
                Products selectedProduct = productBll.findProductByName(productName);
                if (selectedProduct == null) {
                    JOptionPane.showMessageDialog(null, "Product not found.");
                    return;
                }

                if (selectedProduct.getQuantity() < quantity) {
                    JOptionPane.showMessageDialog(null, "Not enough stock available.");
                    return;
                }

                int idClient = clientBll.findClientByName(clientName).getId();
                Orders order = new Orders(idClient, productName, quantity);

                orderBll.insert(order);
                selectedProduct.setQuantity(selectedProduct.getQuantity() - quantity);
                productBll.update(selectedProduct);

                double totalPrice = productBll.getProductPriceByName(productName) * quantity; // Assuming you have a method to get product price
                billBll.createBill(id, order.getIdClient(), clientName, productName, quantity, totalPrice);
                JOptionPane.showMessageDialog(null, "Order added successfully");
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText().trim());
                    String productName = (String) productComboBox.getSelectedItem();
                    String clientName = (String) clientComboBox.getSelectedItem();
                    int quantity = Integer.parseInt(quantityField.getText().trim());

                    if (id <= 0) {
                        JOptionPane.showMessageDialog(null, "A valid ID for the order must be provided.");
                        return;
                    }

                    if (productName.isEmpty() && clientName.isEmpty() && quantity == 0) {
                        JOptionPane.showMessageDialog(null, "At least one field must be filled out to update an order.");
                        return;
                    }
                    Orders existingOrder = orderBll.findById(id);
                    if (existingOrder == null) {
                        JOptionPane.showMessageDialog(null, "Order not found.");
                        return;
                    }

                    if (!productName.isEmpty()) {
                        existingOrder.setNameProduct(productName);
                    }
                    if (!clientName.isEmpty()) {
                        Client client = clientBll.findClientByName(clientName);
                        if (client == null) {
                            JOptionPane.showMessageDialog(null, "Client not found.");
                            return;
                        }
                        existingOrder.setIdClient(client.getId());
                    }
                    if (quantity >= 0) {
                        existingOrder.setQuantity(quantity);
                    }
                    orderBll.update(existingOrder);
                    JOptionPane.showMessageDialog(null, "Order updated successfully");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid ID format.");
                } catch (NoSuchElementException ex) {
                    JOptionPane.showMessageDialog(null, "The client with the specified name was not found.");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    Orders order = new Orders();
                    order.setId(id);
                    orderBll.delete(order);
                    JOptionPane.showMessageDialog(null, "Order removed successfully");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid ID format.");
                }
            }
        });
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText().trim());

                    Orders order = orderBll.findById(id);

                    if (order != null) {
                        new FindDetailsOrder(order).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Order not found");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid ID format.");
                } catch (NoSuchElementException ex) {
                    JOptionPane.showMessageDialog(null, "The order with given ID was not found!");
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
        productComboBox = new JComboBox<>();
        clientComboBox = new JComboBox<>();
        quantityField = new JTextField(20);
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
        mainPanel.add(productComboBox);
        mainPanel.add(new JLabel("Client Name:"));
        mainPanel.add(clientComboBox);
        mainPanel.add(new JLabel("Quantity:"));
        mainPanel.add(quantityField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(insertButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(findButton);

        mainPanel.add(buttonPanel);
    }
    private void loadProducts() {
        List<Products> products = productBll.selectall();
        for (Products product : products) {
            productComboBox.addItem(product.getNameProduct());
        }
    }

    private void loadClients() {
        List<Client> clients=clientBll.selectall();
        for (Client client : clients) {
            clientComboBox.addItem(client.getName());
        }
    }
}

