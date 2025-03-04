package Model;

public class Client {
    private int id;
    private String name;
    private String address;
    private String email;

    public Client( String name, String address, String email) {
        this.name = name;
        this.address = address;
        this.email = email;
    }
    public Client(){}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "Client [id = " + id + ", name = " + name + ", address = " + address  + ", email=" + email + "]";
    }
}
