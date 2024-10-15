package model;

public class Staff extends User{
    private int id;
    private String image;
    private double salary;
    private int idDepartment;
    private String username;
    private String nameDepartment;

    public Staff() {
    }

    public Staff(String name, int age, String gender, String address, String phone, String email, int userId, String image, double salary, int idDepartment) {
        super(name, age, gender, address, phone, email, userId);
        this.image = image;
        this.salary = salary;
        this.idDepartment = idDepartment;
    }

    public Staff(int id, String name, int age, String gender, String address, String phone, String email, int userId, String image, double salary, int idDepartment) {
        super(name, age, gender, address, phone, email, userId);
        this.id = id;
        this.image = image;
        this.salary = salary;
        this.idDepartment = idDepartment;
    }

    public Staff(String name, int age, String gender, String address, String phone, String email, int userId, String image, double salary, int idDepartment, String username, String nameDepartment) {
        super(name, age, gender, address, phone, email, userId);
        this.image = image;
        this.salary = salary;
        this.idDepartment = idDepartment;
        this.username = username;
        this.nameDepartment = nameDepartment;
    }

    public Staff(int id, String name, int age, String gender, String address, String phone, String email, int userId, String image, double salary, int idDepartment, String username, String nameDepartment) {
        super(name, age, gender, address, phone, email, userId);
        this.id = id;
        this.image = image;
        this.salary = salary;
        this.idDepartment = idDepartment;
        this.username = username;
        this.nameDepartment = nameDepartment;
    }

    public int getId() {
        return id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }
}
