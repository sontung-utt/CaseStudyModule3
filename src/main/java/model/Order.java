package model;

public class Order {
    private int id;
    private String time;
    private double total;
    private int idCustomer;

    public Order() {
    }

    public Order(String time, double total, int idCustomer) {
        this.time = time;
        this.total = total;
        this.idCustomer = idCustomer;
    }

    public Order(int id, String time, double total, int idCustomer) {
        this.id = id;
        this.time = time;
        this.total = total;
        this.idCustomer = idCustomer;
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

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }
}
