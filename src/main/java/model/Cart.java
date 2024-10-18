package model;

public class Cart {
    private int id;
    private int idCustomer;

    public Cart() {
    }

    public Cart(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Cart(int id, int idCustomer) {
        this.id = id;
        this.idCustomer = idCustomer;
    }

    public int getId() {
        return id;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }
}
