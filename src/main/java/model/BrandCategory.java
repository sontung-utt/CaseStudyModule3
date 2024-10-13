package model;

public class BrandCategory {
    private int id;
    private int idCategory;
    private int idBrand;
    private Category category;
    private Brand brand;

    public BrandCategory() {
    }

    public BrandCategory(int idCategory, int idBrand) {
        this.idCategory = idCategory;
        this.idBrand = idBrand;
    }

    public BrandCategory(int id, int idCategory, int idBrand) {
        this.id = id;
        this.idCategory = idCategory;
        this.idBrand = idBrand;
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

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public int getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(int idBrand) {
        this.idBrand = idBrand;
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
