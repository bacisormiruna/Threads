package Presentation;

import BusinessLogic.ClientBll;
import BusinessLogic.ProductsBll;
import Model.Client;
import DataAccess.AbstractDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import static DataAccess.AbstractDAO.setField;

public class ListClients extends JFrame {//afisarea in tabel a clientilor
    private JTable clientTable;
    private DefaultTableModel tableModel;

    public ListClients() {
        setTitle("Clients List");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        clientTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(clientTable);
        add(scrollPane, BorderLayout.CENTER);

        ClientBll clientBll = new ClientBll();
        List<Client> clientList = clientBll.selectall();

        setField(clientList, clientTable);
        setVisible(true);
    }
    private void loadClients() {
        ClientBll clientBll = new ClientBll();
        List<Client> clientList = clientBll.selectall();

        for (Client client : clientList) {
            Object[] rowData = {client.getId(), client.getName(), client.getAddress(), client.getEmail()};
            tableModel.addRow(rowData);
        }
    }
}
