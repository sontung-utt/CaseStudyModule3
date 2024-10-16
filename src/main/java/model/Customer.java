package model;

public class Customer extends User{
    private int id;
    private String username;

    public Customer() {
    }

    public Customer(String name, int age, String gender, String address, String phone, String email, int userId) {
        super(name, age, gender, address, phone, email, userId);
    }

    public Customer(int id, String name, int age, String gender, String address, String phone, String email, int userId) {
        super(name, age, gender, address, phone, email, userId);
        this.id = id;
    }

    public Customer(String name, int age, String gender, String address, String phone, String email, int userId, String username) {
        super(name, age, gender, address, phone, email, userId);
        this.username = username;
    }

    public Customer(int id, String name, int age, String gender, String address, String phone, String email, int userId, String username) {
        super(name, age, gender, address, phone, email, userId);
        this.id = id;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
