package model;

public class CustomerAccount {
    private int id;
    private String username;
    private String password;
    private String created_at;
    private String modified_at;
    private String name;
    private String phone;
    private String email;

    public CustomerAccount() {
    }

    public CustomerAccount(int id, String username, String password, String created_at, String modified_at, String name, String phone, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.created_at = created_at;
        this.modified_at = modified_at;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public CustomerAccount(String username, String password, String created_at, String modified_at) {
        this.username = username;
        this.password = password;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public CustomerAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public CustomerAccount(int id, String username, String password, String created_at, String modified_at) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getModified_at() {
        return modified_at;
    }

    public void setModified_at(String modified_at) {
        this.modified_at = modified_at;
    }
}
