package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PerspectiveProduct extends JFrame {
    private JPanel mainPanel;
    private JButton clientViewButton;
    private JButton managerViewButton;

    public PerspectiveProduct() {
        initComponents();

        clientViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListProduct().setVisible(true);
            }
        });

        managerViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginManager().setVisible(true);
            }
        });


        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400); // Set the size of the window to be larger
        setVisible(true);
    }

    private void initComponents() {
        mainPanel = new JPanel();
        clientViewButton = new JButton("Client View - Product List");
        managerViewButton = new JButton("Manager View");

        mainPanel.setBackground(new Color(144, 238, 144));

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(Box.createVerticalStrut(20));
        clientViewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        managerViewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(clientViewButton);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(managerViewButton);
    }
}
