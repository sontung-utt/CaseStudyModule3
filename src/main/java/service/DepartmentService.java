package service;

import model.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentService implements IService<Department>{
    private final Connection connection = ConnectToMySQL.getConnection();
    public DepartmentService(){

    }
    @Override
    public void add(Department department) {
        String sql = "insert into department (name, salaryCoefficient) \n" +
                "values (?,?);";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, department.getName());
            preparedStatement.setDouble(2,department.getSalaryCoefficient());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int id, Department department) {
        String sql = "update department \n" +
                "set name = ?, salaryCoefficient = ?\n" +
                "where id = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,department.getName());
            preparedStatement.setDouble(2,department.getSalaryCoefficient());
            preparedStatement.setInt(3,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from department where id = ?;";
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
    public Department findById(int id) {
        Department department = null;
        String sql = "select * from department where id = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                double salaryCoefficient = resultSet.getDouble("salaryCoefficient");
                department = new Department(id,name,salaryCoefficient);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return department;
    }

    @Override
    public List<Department> getAll() {
        List<Department> departmentList = new ArrayList<>();
        String sql = "select * from department;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double salaryCoefficient = resultSet.getDouble("salaryCoefficient");
                Department department = new Department(id,name,salaryCoefficient);
                departmentList.add(department);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return departmentList;
    }

    public boolean existDepartment(String name){
        String sql = "select count(*) from department where name = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}
