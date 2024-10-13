package model;

public class BrandCategory {
    private int id;
    private Category category;
    private Brand brand;

    public BrandCategory() {
    }

    public BrandCategory(Brand brand, Category category) {
        this.brand = brand;
        this.category = category;
    }

    public BrandCategory(int id, Category category, Brand brand) {
        this.id = id;
        this.category = category;
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
