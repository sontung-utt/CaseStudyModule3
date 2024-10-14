package model;

public class Account {
    private int id;
    private String username;
    private String password;
    private int idRole;

    public Account() {
    }

    public Account(String username, String password, int idRole) {
        this.username = username;
        this.password = password;
        this.idRole = idRole;
    }

    public Account(int id, String username, String password, int idRole) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.idRole = idRole;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }
}
