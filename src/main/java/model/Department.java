package model;

public class Department {
    private int id;
    private String name;
    private double salaryCoefficient;

    public Department() {
    }

    public Department(String name, double salaryCoefficient) {
        this.name = name;
        this.salaryCoefficient = salaryCoefficient;
    }

    public Department(int id, String name, double salaryCoefficient) {
        this.id = id;
        this.name = name;
        this.salaryCoefficient = salaryCoefficient;
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

    public double getSalaryCoefficient() {
        return salaryCoefficient;
    }

    public void setSalaryCoefficient(double salaryCoefficient) {
        this.salaryCoefficient = salaryCoefficient;
    }
}
