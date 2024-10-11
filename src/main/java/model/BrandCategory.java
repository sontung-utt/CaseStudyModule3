package model;

public class BrandCategory {
    private int id;
    private int idBrand;
    private int idCategory;

    public BrandCategory() {
    }

    public BrandCategory(int idBrand, int idCategory) {
        this.idBrand = idBrand;
        this.idCategory = idCategory;
    }

    public BrandCategory(int id, int idBrand, int idCategory) {
        this.id = id;
        this.idBrand = idBrand;
        this.idCategory = idCategory;
    }

    public int getId() {
        return id;
    }

    public int getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(int idBrand) {
        this.idBrand = idBrand;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }
}
