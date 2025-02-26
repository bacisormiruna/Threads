package Model;

public class Products {
    private int id;
    private String nameProduct;
    private int quantity;
    private double price;
    public Products( String nameProduct, int quantity, double price)
    {
        this.nameProduct=nameProduct;
        this.quantity=quantity;
        this.price=price;
    }
     public Products(){
     }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
