package service;

import model.Customer;
import model.CustomerAccount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CustomerAccountService implements IService<CustomerAccount>{
    private final Connection connection = ConnectToMySQL.getConnection();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    public CustomerAccountService(){

    }
    @Override
    public void add(CustomerAccount customerAccount) {
        String sql = "insert into customeraccount(username,password) values (?,?);";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customerAccount.getUsername());
            preparedStatement.setString(2,customerAccount.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int id, CustomerAccount customerAccount) {
        String sql = "update customeraccount\n" +
                "set username = ?, password = ?\n" +
                "where id = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, customerAccount.getUsername());
            preparedStatement.setString(2, customerAccount.getPassword());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public int findIndexById(int id) {
        return 0;
    }

    @Override
    public CustomerAccount findById(int id) {
        CustomerAccount account = null;
        String sql = "select * from customeraccount where id = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                LocalDateTime created_at = resultSet.getTimestamp("created_at").toLocalDateTime();
                LocalDateTime modified_at = resultSet.getTimestamp("modified_at").toLocalDateTime();
                String formattedCreatedAt = created_at.format(formatter);
                String formattedModifiedAt = modified_at.format(formatter);
                account = new CustomerAccount(id,username,password,formattedCreatedAt,formattedModifiedAt);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return account;
    }

    @Override
    public List<CustomerAccount> getAll() {
        String sql = "select * from customeraccount;";
        List<CustomerAccount> customerAccountList = new ArrayList<>();
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                LocalDateTime created_at = resultSet.getTimestamp("created_at").toLocalDateTime();
                LocalDateTime modified_at = resultSet.getTimestamp("modified_at").toLocalDateTime();
                String formattedCreatedAt = created_at.format(formatter);
                String formattedModifiedAt = modified_at.format(formatter);
                CustomerAccount customerAccount = new CustomerAccount(id,username,password,formattedCreatedAt, formattedModifiedAt);
                customerAccountList.add(customerAccount);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerAccountList;
    }

    public boolean existAccountName(String username){
        String sql = "select count(*) from customeraccount where username = ?;";
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
