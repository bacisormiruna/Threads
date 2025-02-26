package Presentation;

import BusinessLogic.ClientBll;
import Model.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Clasa care reprezintă interfața grafică pentru manipularea datelor despre clienți.
 */
public class View extends JFrame {
    // Declarațiile membrilor și variabilele de instanță
    private JPanel mainPanel;
    private JTextField nameField;
    private JTextField addressField;
    private JTextField emailField;
    private JButton insertButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton findButton;
    private JTextField idField;

    private ClientBll clientBll;

    /**
     * Constructorul clasei.
     */
    public View() {
        // Inițializarea componentelor și configurarea acțiunilor butoanelor
        initComponents();

        clientBll = new ClientBll();
        /**
         * Acțiunea de inserare a unui client în baza de date.
         */
        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String address = addressField.getText();
                String email = emailField.getText();
                if (name.isEmpty() || address.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "All fields must be filled out to insert a client.");
                    return;
                }
                Client client = new Client(name, address, email);
                clientBll.insert(client);
                JOptionPane.showMessageDialog(null, "Client inserted successfully");
            }
        });

        /**
         * Acțiunea de actualizare a unui client din baza de date.
         */
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText().trim());
                    String name = nameField.getText().trim();
                    String address = addressField.getText().trim();
                    String email = emailField.getText().trim();

                    if (id <= 0) {
                        JOptionPane.showMessageDialog(null, "A valid ID must be provided.");
                        return;
                    }

                    if (name.isEmpty() && address.isEmpty() && email.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "At least one field must be filled out to update a client.");
                        return;
                    }

                    // Fetch existing client details
                    Client existingClient = clientBll.findClientById(id);
                    if (existingClient == null) {
                        JOptionPane.showMessageDialog(null, "Client not found.");
                        return;
                    }

                    // Update only the specified fields
                    if (!name.isEmpty()) {
                        existingClient.setName(name);
                    }
                    if (!address.isEmpty()) {
                        existingClient.setAddress(address);
                    }
                    if (!email.isEmpty()) {
                        existingClient.setEmail(email);
                    }

                    clientBll.update(existingClient);
                    JOptionPane.showMessageDialog(null, "Client updated successfully");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid ID format.");
                }
            }
        });

        /**
         * Acțiunea de ștergere a unui client din baza de date.
         */
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                int id = Integer.parseInt(idField.getText());
                Client client = new Client();
                client.setId(id);
                clientBll.delete(client);
                    JOptionPane.showMessageDialog(null, "Client deleted successfully");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid ID format.");
                }
            }
        });


        /**
         * Acțiunea de căutare a unui client după ID-ul său în baza de date.
         */
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText().trim());
                    Client client = clientBll.findClientById(id);
                    if (client != null) {
                        new FindDetailsClient(client).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Client not found");
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

    /**
     * Inițializează componentele interfeței grafice.
     */
    private void initComponents() {
        mainPanel = new JPanel();
        nameField = new JTextField(20);
        addressField = new JTextField(20);
        emailField = new JTextField(20);
        idField = new JTextField(20);
        insertButton = new JButton("Insert");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        findButton = new JButton("Find");

        mainPanel.setBackground(new Color(144, 238, 144)); // Light green color

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(new JLabel("ID:"));
        mainPanel.add(idField);
        mainPanel.add(new JLabel("Name:"));
        mainPanel.add(nameField);
        mainPanel.add(new JLabel("Address:"));
        mainPanel.add(addressField);
        mainPanel.add(new JLabel("Email:"));
        mainPanel.add(emailField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        buttonPanel.add(insertButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(findButton);

        mainPanel.add(buttonPanel);

    }
}
