package Presentation;

import Model.Client;
import Model.Orders;

import javax.swing.*;
import java.awt.*;

public class FindDetailsOrder extends JFrame {
    private JLabel productNameLabel;
    private JLabel idClientLabel;
    private JLabel quantityLabel;

    public FindDetailsOrder(Orders order) {
        setTitle("Order Details");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        productNameLabel = new JLabel("Product name: " + order.getNameProduct());
        idClientLabel = new JLabel("ID Client: " + order.getIdClient());
        quantityLabel = new JLabel("Quantity: " + order.getQuantity());

        add(productNameLabel);
        add(idClientLabel);
        add(quantityLabel);
    }
}