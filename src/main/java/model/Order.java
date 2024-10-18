package model;

public class Order {
    private int id;
    private String time;
    private double total;
    private int idCart;

    public Order() {
    }

    public Order(String time, double total, int idCart) {
        this.time = time;
        this.total = total;
        this.idCart = idCart;
    }

    public Order(int id, String time, double total, int idCart) {
        this.id = id;
        this.time = time;
        this.total = total;
        this.idCart = idCart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
