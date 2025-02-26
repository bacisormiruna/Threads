package BusinessLogic;

import DataAccess.BillDAO;
import Model.Bill;
import Presentation.BillView;

import java.time.LocalDateTime;
import java.util.List;

public class BillBll {
    private BillDAO billDAO;

    public BillBll() {
        billDAO = new BillDAO();
    }

    public void createBill( int id, int orderId, String clientName, String productName, int quantity, double totalPrice) {
        LocalDateTime date = LocalDateTime.now();
        Bill bill = new Bill(id, orderId, clientName, productName, quantity, totalPrice, date);
        billDAO.insertBill(bill);
        showBillInWindow(bill);
    }
    private void showBillInWindow(Bill bill) {
        BillView billView = new BillView(bill);
        billView.setVisible(true);
    }
}
