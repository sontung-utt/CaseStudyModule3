package model;

public class CartDetail {
    private int id;
    private int idCart;
    private int idProduct;
    private int quantity;
    private double price;
    private String created_at;
    private String nameProduct;

    public CartDetail() {
    }

    public CartDetail(int idCart, int idProduct, int quantity, double price) {
        this.idCart = idCart;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.price = price;
    }

    public CartDetail(int id, int idCart, int idProduct, int quantity, double price) {
        this.id = id;
        this.idCart = idCart;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.price = price;
    }

    public CartDetail(int id, int idCart, int idProduct, int quantity) {
        this.id = id;
        this.idCart = idCart;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    public CartDetail(int idCart, int idProduct, int quantity, double price, String created_at, String nameProduct) {
        this.idCart = idCart;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.price = price;
        this.created_at = created_at;
        this.nameProduct = nameProduct;
    }

    public CartDetail(int id, int idCart, int idProduct, int quantity, double price, String created_at, String nameProduct) {
        this.id = id;
        this.idCart = idCart;
        this.idProduct = idProduct;
        this.quantity = quantity;
        this.price = price;
        this.created_at = created_at;
        this.nameProduct = nameProduct;
    }

    public int getId() {
        return id;
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }
}
