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

    }

    @Override
    public void update(int id, Department department) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public int findIndexById(int id) {
        return 0;
    }

    @Override
    public Department findById(int id) {
        return null;
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
}
