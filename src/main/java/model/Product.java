package model;

public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private String image;
    private String description;
    private int idBrandCategory;
    private String categoryName;
    private String brandName;

    public Product() {
    }

    public Product(String name, double price, int quantity, String image, String description, int idBrandCategory) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.description = description;
        this.idBrandCategory = idBrandCategory;
    }

    public Product(int id, String name, double price, int quantity, String image, String description, int idBrandCategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.description = description;
        this.idBrandCategory = idBrandCategory;
    }

    public Product(String name, double price, int quantity, String image, String description, String categoryName, String brandName) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.description = description;
        this.categoryName = categoryName;
        this.brandName = brandName;
    }

    public Product(int id, String name, double price, int quantity, String image, String description, String categoryName, String brandName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.description = description;
        this.categoryName = categoryName;
        this.brandName = brandName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdBrandCategory() {
        return idBrandCategory;
    }

    public void setIdBrandCategory(int idBrandCategory) {
        this.idBrandCategory = idBrandCategory;
    }
}
