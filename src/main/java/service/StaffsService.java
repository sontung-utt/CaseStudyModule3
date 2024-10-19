package service;
import model.Staff;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffsService implements IService<Staff>{
    private final Connection connection = ConnectToMySQL.getConnection();
    public StaffsService(){

    }
    @Override
    public void add(Staff staff) {
        String sql = "insert into staff(name, age, gender, address, phone, email, image, userId, salary, idDepartment)\n" +
                "values (?,?,?,?,?,?,?,?,?,?);";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,staff.getName());
            preparedStatement.setInt(2,staff.getAge());
            preparedStatement.setString(3, staff.getGender());
            preparedStatement.setString(4, staff.getAddress());
            preparedStatement.setString(5, staff.getPhone());
            preparedStatement.setString(6, staff.getEmail());
            preparedStatement.setString(7, staff.getImage());
            preparedStatement.setInt(8, staff.getUserId());
            preparedStatement.setDouble(9, staff.getSalary());
            preparedStatement.setInt(10, staff.getIdDepartment());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int id, Staff staff) {
        String sql = "update staff\n" +
                "set name = ?, age = ?, gender = ?, address = ?, phone = ?, email = ?, salary = ?, idDepartment = ?, staff.userId = ?, image = ?\n" +
                "where id = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,staff.getName());
            preparedStatement.setInt(2,staff.getAge());
            preparedStatement.setString(3, staff.getGender());
            preparedStatement.setString(4, staff.getAddress());
            preparedStatement.setString(5, staff.getPhone());
            preparedStatement.setString(6, staff.getEmail());
            preparedStatement.setDouble(7, staff.getSalary());
            preparedStatement.setInt(8, staff.getIdDepartment());
            preparedStatement.setInt(9, staff.getUserId());
            preparedStatement.setString(10, staff.getImage());
            preparedStatement.setInt(11,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from staff where id = ?;";
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
    public Staff findById(int id) {
        Staff staff = null;
        String sql = "SELECT a.*, b.username as username, c.name as nameDepartment \n" +
                "FROM staff a \n" +
                "JOIN accounts b ON a.userId = b.id \n" +
                "JOIN department c ON a.idDepartment = c.id \n" +
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
                String image = resultSet.getString("image");
                int userId = resultSet.getInt("userId");
                double salary = resultSet.getDouble("salary");
                int idDepartment = resultSet.getInt("idDepartment");
                String username = resultSet.getString("username");
                String nameDepartment = resultSet.getString("nameDepartment");

                staff = new Staff(id, name, age, gender, address, phone, email, userId, image, salary, idDepartment, username, nameDepartment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return staff;
    }

    @Override
    public List<Staff> getAll() {
        List<Staff> staffList = new ArrayList<>();
        String sql = "select a.*, b.username as username, c.name as nameDepartment, (a.salary * c.salaryCoefficient) as totalSalary from staff a\n" +
                "                join accounts b on a.userId = b.id\n" +
                "                join department c on a.idDepartment = c.id;";
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
                String image = resultSet.getString("image");
                int userId = resultSet.getInt("userId");
                double salary = resultSet.getDouble("totalSalary");
                int idDepartment = resultSet.getInt("idDepartment");
                String username = resultSet.getString("username");
                String nameDepartment = resultSet.getString("nameDepartment");
                Staff staff = new Staff(id,name,age,gender,address,phone,email,userId,image,salary,idDepartment,username,nameDepartment);
                staffList.add(staff);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return staffList;
    }

    public boolean checkExistUserId(int userId){
        String sql = "select count(*) from staff where userId = ?;";
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
        String sql = "select * from staff where phone = ?;";
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
        String sql = "select * from staff where email = ?;";
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

    public List<Staff> listStaffByDepartment(int idDepartment){
        List<Staff> staffs = new ArrayList<>();
        String sql = "select a.id as id, a.name as name, a.image as image, a.phone as phone, a.email as email\n" +
                "from staff a join department b on a.idDepartment = b.id\n" +
                "where a.idDepartment = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,idDepartment);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String image = resultSet.getString("image");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                Staff staff = new Staff(id,name,image,phone,email);
                staffs.add(staff);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return staffs;
    }

    public List<Staff> getStaffByName(String name) {
        List<Staff> staffList = new ArrayList<>();
        String sql = "select a.*, b.username as username, c.name as nameDepartment from staff a\n" +
                "join accounts b on a.userId = b.id\n" +
                "join department c on a.idDepartment = c.id\n" +
                "where a.name like ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String image = resultSet.getString("image");
                int userId = resultSet.getInt("userId");
                double salary = resultSet.getDouble("salary");
                int idDepartment = resultSet.getInt("idDepartment");
                String username = resultSet.getString("username");
                String nameDepartment = resultSet.getString("nameDepartment");
                Staff staff = new Staff(id,name,age,gender,address,phone,email,userId,image,salary,idDepartment,username,nameDepartment);
                staffList.add(staff);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return staffList;
    }
}
