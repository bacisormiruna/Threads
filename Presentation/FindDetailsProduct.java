package Presentation;

import Model.Orders;
import Model.Products;

import javax.swing.*;
import java.awt.*;

public class FindDetailsProduct extends JFrame{
    private JLabel productNameLabel;
    private JLabel priceLabel;
    private JLabel quantityLabel;

    public FindDetailsProduct(Products product) {
        setTitle("Product Details");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        productNameLabel = new JLabel("Product name: " + product.getNameProduct());
        priceLabel = new JLabel("Price: " + product.getPrice());
        quantityLabel = new JLabel("Quantity: " + product.getQuantity());

        add(productNameLabel);
        add(priceLabel);
        add(quantityLabel);
    }
}
