package service;
import model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AccountService implements IService<Account>{
    private final Connection connection = ConnectToMySQL.getConnection();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    public AccountService(){

    }
    @Override
    public void add(Account account) {
        String sql = "insert into accounts(username, password)\n" +
                "values (?,?);";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int id, Account account) {
        String sql = "update accounts\n" +
                "set idRole = ?, username = ?, password = ?\n" +
                "where id = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,account.getIdRole());
            preparedStatement.setString(2, account.getUsername());
            preparedStatement.setString(3,account.getPassword());
            preparedStatement.setInt(4,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from accounts where id = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int findIndexById(int id) {
        return 0;
    }

    @Override
    public Account findById(int id) {
        Account account = null;
        String sql = "select idRole, username, password, created_at, modified_at from accounts where id = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int idRole = resultSet.getInt("idRole");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                LocalDateTime created_at = resultSet.getTimestamp("created_at").toLocalDateTime();
                LocalDateTime modified_at = resultSet.getTimestamp("modified_at").toLocalDateTime();
                String formattedCreatedAt = created_at.format(formatter);
                String formattedModifiedAt = modified_at.format(formatter);
                account = new Account(id,username,password,idRole,formattedCreatedAt,formattedModifiedAt);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return account;
    }

    @Override
    public List<Account> getAll() {
        String sql = "select a.*,r.name as roleName from accounts a\n" +
                "left join role r on a.idRole = r.id;";
        List<Account> accountList = new ArrayList<>();
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idRole = resultSet.getInt("idRole");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                LocalDateTime created_at = resultSet.getTimestamp("created_at").toLocalDateTime();
                LocalDateTime modified_at = resultSet.getTimestamp("modified_at").toLocalDateTime();
                String formattedCreatedAt = created_at.format(formatter);
                String formattedModifiedAt = modified_at.format(formatter);
                String roleName = resultSet.getString("roleName");
                Account account = new Account(id,username,password,idRole,formattedCreatedAt,formattedModifiedAt,roleName);
                accountList.add(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accountList;
    }

    public boolean existAccountName(String username){
        String sql = "select count(*) from accounts where username = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean checkPassword(String password, String rePassword){
        return password.equals(rePassword);
    }

    public boolean existAccountId(int id){
        String sql = "select count(*) from accounts where id = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt(1) <= 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public List<Account> getAccountByRole (int idRole){
        List<Account> accounts = new ArrayList<>();
        String sql = "select * from accounts a join role b on a.idRole = b. id\n" +
                "where a.idRole = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,idRole);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                LocalDateTime created_at = resultSet.getTimestamp("created_at").toLocalDateTime();
                LocalDateTime modified_at = resultSet.getTimestamp("modified_at").toLocalDateTime();
                String formattedCreatedAt = created_at.format(formatter);
                String formattedModifiedAt = modified_at.format(formatter);
                Account account = new Account(id, username, password, formattedCreatedAt, formattedModifiedAt);
                accounts.add(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accounts;
    }
}
