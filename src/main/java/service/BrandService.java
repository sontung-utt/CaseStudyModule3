package service;

import model.Brand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrandService implements IService<Brand>{
    private final Connection connection = ConnectToMySQL.getConnection();
    public BrandService() {

    }
    @Override
    public void add(Brand brand) {
        String sql = "insert into brand(name, image)\n" +
                "values(?,?);";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,brand.getName());
            preparedStatement.setString(2, brand.getImage());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int id, Brand brand) {
        String sql = "update brand\n" +
                "set name = ?, image = ?\n" +
                "where id = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, brand.getName());
            preparedStatement.setString(2, brand.getImage());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from brand where id = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
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
    public Brand findById(int id) {
        String sql = "select * from brand where id = ?";
        Brand brand = null;
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                String image = resultSet.getString("image");
                brand = new Brand(id, name, image);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return brand;
    }

    @Override
    public List<Brand> getAll() {
        List<Brand> brandList = new ArrayList<>();
        String sql = "select * from brand;";

        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String image = resultSet.getString("image");
                Brand brand = new Brand(id, name, image);
                brandList.add(brand);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return brandList;
    }

    public List<Brand> getBrandByCategory(int idCategory){
        List<Brand> brands = new ArrayList<>();
        String sql = "select a.* from brand a\n" +
                "join brandcategory b on a.id = b.idBrand\n" +
                "where b.idCategory = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,idCategory);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String image = resultSet.getString("image");
                Brand brand = new Brand(id,name,image);
                brands.add(brand);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return brands;
    }
}
