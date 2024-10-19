package model;

public class Order {
    private int id;
    private String time;
    private double total;
    private int idCart;
    private int idCustomer;
    private String status;
    private String nameCustomer;

    public Order() {
    }

    public Order(int id, String time, double total, int idCart, int idCustomer, String status) {
        this.id = id;
        this.time = time;
        this.total = total;
        this.idCart = idCart;
        this.idCustomer = idCustomer;
        this.status = status;
    }

    public Order(int id, String time, double total, int idCart, int idCustomer, String status, String nameCustomer) {
        this.id = id;
        this.time = time;
        this.total = total;
        this.idCart = idCart;
        this.idCustomer = idCustomer;
        this.status = status;
        this.nameCustomer = nameCustomer;
    }

    public Order(double total, int idCart, int idCustomer) {
        this.total = total;
        this.idCart = idCart;
        this.idCustomer = idCustomer;
    }

    public Order(String time, double total, int idCart, int idCustomer) {
        this.time = time;
        this.total = total;
        this.idCart = idCart;
        this.idCustomer = idCustomer;
    }

    public Order(int id, String time, double total, int idCart, int idCustomer) {
        this.id = id;
        this.time = time;
        this.total = total;
        this.idCart = idCart;
        this.idCustomer = idCustomer;
    }

    public int getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }
}
