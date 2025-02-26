package Model;
import java.time.LocalDateTime;

public record Bill(int id, int idOrder, String clientName, String productName, int quantity, double totalPrice, LocalDateTime date) {
}
