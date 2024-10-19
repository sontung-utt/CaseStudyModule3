package model;

public class OrderDetail {
    private int id;
    private int idOrder;
    private int idProduct;
    private int quantity;
    private double price;
    private String nameProduct;

    public OrderDetail() {
    }

    public OrderDetail(int idOrder, int idProduct, int quantity, double price) {
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderDetail(int id, int idOrder, int idProduct, int quantity, double price) {
        this.id = id;
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderDetail(int idOrder, int idProduct, int quantity, double price, String nameProduct) {
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.price = price;
        this.nameProduct = nameProduct;
    }

    public OrderDetail(int id, int idOrder, int idProduct, int quantity, double price, String nameProduct) {
        this.id = id;
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.price = price;
        this.nameProduct = nameProduct;
    }

    public int getId() {
        return id;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
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

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }
}
