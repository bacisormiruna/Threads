package Presentation;
import Model.Client;

import javax.swing.*;
import java.awt.*;

public class FindDetailsClient extends JFrame{
    private JLabel nameLabel;
    private JLabel addressLabel;
    private JLabel emailLabel;

    public FindDetailsClient(Client client) {
        setTitle("Client Details");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        nameLabel = new JLabel("Name: " + client.getName());
        addressLabel = new JLabel("Address: " + client.getAddress());
        emailLabel = new JLabel("Email: " + client.getEmail());

        add(nameLabel);
        add(addressLabel);
        add(emailLabel);
    }
}
