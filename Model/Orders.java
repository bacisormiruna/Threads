package Model;

public class Orders {
    private int id;
    private String nameProduct;
    private int idClient;
    private int quantity;
    public Orders(int idClient, String productName, int quantity)
    {
        this.idClient=idClient;
        this.nameProduct=productName;
        this.quantity=quantity;
    }
    public Orders(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int id_client) {
        this.idClient = id_client;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String productName) {
        this.nameProduct = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity_order) {
        this.quantity = quantity_order;
    }
}
