package service;

import model.Customer;
import model.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerService implements IService<Customer>{
    private final Connection connection = ConnectToMySQL.getConnection();
    public CustomerService(){

    }

    public void add(Customer customer, int idLogin) {
        String sql = "insert into customer(name, age, gender, address, phone, email, userId)\n" +
                "values (?,?,?,?,?,?,?);";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,customer.getName());
            preparedStatement.setInt(2,customer.getAge());
            preparedStatement.setString(3, customer.getGender());
            preparedStatement.setString(4, customer.getAddress());
            preparedStatement.setString(5, customer.getPhone());
            preparedStatement.setString(6,customer.getEmail());
            preparedStatement.setInt(7,idLogin);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int id, Customer customer, int idLogin){
        String sql = "update customer\n" +
                "set name = ?, age = ?, gender = ?, address = ?, phone = ?, email = ?, userId = ?\n" +
                "where id = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,customer.getName());
            preparedStatement.setInt(2,customer.getAge());
            preparedStatement.setString(3, customer.getGender());
            preparedStatement.setString(4, customer.getAddress());
            preparedStatement.setString(5, customer.getPhone());
            preparedStatement.setString(6,customer.getEmail());
            preparedStatement.setInt(7, idLogin);
            preparedStatement.setInt(8, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(Customer customer) {

    }

    @Override
    public void update(int id, Customer customer) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public int findIndexById(int id) {
        return 0;
    }

    @Override
    public Customer findById(int id) {
        Customer customer = null;
        String sql = "SELECT a.*, b.username as username \n" +
                "FROM customer a \n" +
                "JOIN customeraccount b ON a.userId = b.id \n" +
                "WHERE a.id = ?";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                int userId = resultSet.getInt("userId");
                String username = resultSet.getString("username");
                customer = new Customer(id, name, age, gender, address, phone, email, userId, username);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customerList = new ArrayList<>();
        String sql = "select a.*, b.username as username from customer a join customeraccount b on a.userId = b.id;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                int userId = resultSet.getInt("userId");
                String username = resultSet.getString("username");
                Customer customer = new Customer(id,name,age,gender,address,phone,email,userId, username);
                customerList.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customerList;
    }

    public boolean checkExistUserId(int userId){
        String sql = "select count(*) from customer where userId = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean checkExistPhone(String phone){
        String sql = "select * from customer where phone = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,phone);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean checkExistEmail(String email){
        String sql = "select * from customer where email = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean checkValidatePhone (String phone){
        String phoneRegex = "^(0[3|5|7|8|9])+([0-9]{8})$";
        return !phone.matches(phoneRegex);
    }

    public boolean checkValidateEmail (String email) {
        String emailRegex = "^[\\w-\\.]+@[\\w-\\.]+\\.[a-zA-Z]{2,6}$";
        return !email.matches(emailRegex);
    }

    public int getIdByUserId (int userId) {
        String sql = "select id from customer where userId = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt("id");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
