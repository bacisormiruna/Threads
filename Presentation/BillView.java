package Presentation;

import Model.Bill;

import javax.swing.*;
import java.awt.*;

public class BillView extends JFrame {
    private JTextArea billTextArea;

    public BillView(Bill bill) {
        setTitle("Factura cu ID: " + bill.id());
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        billTextArea = new JTextArea();
        billTextArea.setEditable(false);
        billTextArea.setFont(new Font("Serif", Font.PLAIN, 14));
        billTextArea.setText(generateBillText(bill));

        JScrollPane scrollPane = new JScrollPane(billTextArea);

        add(scrollPane);
    }

    private String generateBillText(Bill bill) {
        StringBuilder sb = new StringBuilder();
        sb.append("Bill ID: ").append(bill.id()).append("\n");
        sb.append("Order ID: ").append(bill.idOrder()).append("\n");
        sb.append("Client Name: ").append(bill.clientName()).append("\n");
        sb.append("Product Name: ").append(bill.productName()).append("\n");
        sb.append("Quantity: ").append(bill.quantity()).append("\n");
        sb.append("Total Price: $").append(bill.totalPrice()).append("\n");
        sb.append("Date: ").append(bill.date().toString()).append("\n");
        return sb.toString();
    }
}
