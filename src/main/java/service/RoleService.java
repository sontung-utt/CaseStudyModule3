package service;

import model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleService implements IService<Role>{
    private final Connection connection = ConnectToMySQL.getConnection();
    @Override
    public void add(Role role) {

    }

    @Override
    public void update(int id, Role role) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public int findIndexById(int id) {
        return 0;
    }

    @Override
    public Role findById(int id) {
        return null;
    }

    @Override
    public List<Role> getAll() {
        List<Role> roleList = new ArrayList<>();
        String sql = "select * from role;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Role role = new Role(id, name);
                roleList.add(role);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roleList;
    }
}
