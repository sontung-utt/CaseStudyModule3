package model;

public class Brand {
    private int id;
    private String name;
    private String image;

    public Brand() {
    }

    public Brand(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public Brand(int id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public Brand(int id, String name) {
        this.id = id;
        this.name = name;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
