package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Account {
    private int id;
    private String username;
    private String password;
    private int idRole;
    private String created_at;
    private String modified_at;
    private String roleName;

    public Account() {
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Account(String username, String password, int idRole, String created_at, String modified_at) {
        this.username = username;
        this.password = password;
        this.idRole = idRole;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public Account(int id, String username, String password, int idRole, String created_at, String modified_at) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.idRole = idRole;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public Account(int id, String username, String password, String created_at, String modified_at) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.created_at = created_at;
        this.modified_at = modified_at;
    }

    public Account(int id, String username, String password, int idRole) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.idRole = idRole;
    }

    public Account(String username, String password, int idRole, String created_at, String modified_at, String roleName) {
        this.username = username;
        this.password = password;
        this.idRole = idRole;
        this.created_at = created_at;
        this.modified_at = modified_at;
        this.roleName = roleName;
    }

    public Account(int id, String username, String password, int idRole, String created_at, String modified_at, String roleName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.idRole = idRole;
        this.created_at = created_at;
        this.modified_at = modified_at;
        this.roleName = roleName;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
