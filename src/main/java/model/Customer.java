package model;

public class Customer extends User{
    private int id;

    public Customer() {
    }

    public Customer(String name, int age, String gender, String address, String phone, String email, String image, int userId) {
        super(name, age, gender, address, phone, email, userId);
    }

    public Customer(int id, String name, int age, String gender, String address, String phone, String email, String image, int userId) {
        super(name, age, gender, address, phone, email, userId);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
