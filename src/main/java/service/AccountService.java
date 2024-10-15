package service;
import model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AccountService implements IService<Account>{
    private final Connection connection = ConnectToMySQL.getConnection();
    public AccountService(){

    }
    @Override
    public void add(Account account) {
        String sql = "insert into accounts(idRole, username, password, created_at, modified_at)\n" +
                "values (?,?,?,?,?);";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,account.getIdRole());
            preparedStatement.setString(2,account.getUsername());
            preparedStatement.setString(3, account.getPassword());
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String formattedDateTime = now.format(formatter);
            preparedStatement.setString(4,formattedDateTime);
            preparedStatement.setString(5,formattedDateTime);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int id, Account account) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public int findIndexById(int id) {
        return 0;
    }

    @Override
    public Account findById(int id) {
        return null;
    }

    @Override
    public List<Account> getAll() {
        String sql = "select * from accounts;";
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
                Account account = new Account(id,username,password,idRole,created_at,modified_at);
                accountList.add(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accountList;
    }

    public void register(Account account){
        String sql = "insert into accounts(username, password)\n" +
                "values (?,?);";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existAccount(String username){
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
}
