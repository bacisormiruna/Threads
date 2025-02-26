package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PerspectiveClients extends JFrame {
    private JPanel mainPanel;
    private JButton clientViewButton;
    private JButton managerViewButton;

    public PerspectiveClients() {
        initComponents();

        clientViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ListClients().setVisible(true);
            }
        });

        managerViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new View().setVisible(true);
            }
        });


        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setVisible(true);
    }

    private void initComponents() {
        mainPanel = new JPanel();
        clientViewButton = new JButton("Client List");
        managerViewButton = new JButton("Add/Delete/Update/Find clients");

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
