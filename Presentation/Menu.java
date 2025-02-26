package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private JPanel mainPanel;
    private JButton clientButton;
    private JButton productButton;
    private JButton orderButton;

    public Menu() {
        initComponents();

        clientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PerspectiveClients().setVisible(true);
            }
        });

        productButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PerspectiveProduct().setVisible(true);
            }
        });

        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewOrder().setVisible(true);
            }
        });

        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400); // Set the size of the window to be larger
        setVisible(true);
    }

    private void initComponents() {
        mainPanel = new JPanel();
        clientButton = new JButton("Client Operations");
        productButton = new JButton("Product Operations");
        orderButton = new JButton("Order Operations");

        mainPanel.setBackground(new Color(144, 238, 144)); // Light green color

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(Box.createVerticalStrut(20)); // Add vertical spacing
        clientButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        productButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        orderButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(clientButton);
        mainPanel.add(Box.createVerticalStrut(20)); // Add vertical spacing
        mainPanel.add(productButton);
        mainPanel.add(Box.createVerticalStrut(20)); // Add vertical spacing
        mainPanel.add(orderButton);
    }
}
