package service;

import model.Brand;
import model.BrandCategory;
import model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrandCategoryService implements IService<BrandCategory>{
    private final Connection connection = ConnectToMySQL.getConnection();
    public BrandCategoryService(){

    }
    @Override
    public void add(BrandCategory brandCategory) {
        String sql = "insert into brandcategory(idBrand, idCategory)\n" +
                "values (?,?);";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,brandCategory.getIdBrand());
            preparedStatement.setInt(2,brandCategory.getIdCategory());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int id, BrandCategory brandCategory) {
        String sql = "update brandcategory set idBrand = ?, idCategory = ? where id = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,brandCategory.getIdBrand());
            preparedStatement.setInt(2,brandCategory.getIdCategory());
            preparedStatement.setInt(3,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from brandcategory where id = ?;";
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
    public BrandCategory findById(int id) {
        String sql = "select * from brandcategory where id = ?;";
        BrandCategory brandCategory = null;
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int idBrand = resultSet.getInt("idBrand");
                int idCategory = resultSet.getInt("idCategory");
                brandCategory = new BrandCategory(id,idCategory,idBrand);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return brandCategory;
    }

    @Override
    public List<BrandCategory> getAll() {
        List<BrandCategory> list = new ArrayList<>();
        String sql = "select a.*, b.name as nameBrand, c.name as nameCategory from brandcategory a\n" +
                "join brand b on a.idBrand = b.id\n" +
                "join category c on a.idCategory = c.id\n" +
                "order by c.name asc";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idBrand = resultSet.getInt("idBrand");
                int idCategory = resultSet.getInt("idCategory");
                String nameBrand = resultSet.getString("nameBrand");
                String nameCategory = resultSet.getString("nameCategory");
                Brand brand = new Brand(idBrand, nameBrand);
                Category category = new Category(idCategory, nameCategory);
                BrandCategory brandCategory = new BrandCategory(id,category,brand);
                list.add(brandCategory);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public boolean existsBrandAndCategory(int idBrand, int idCategory) {
        String sql = "SELECT COUNT(*) FROM brandcategory WHERE idBrand = ? AND idCategory = ?";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idBrand);
            preparedStatement.setInt(2, idCategory);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; // Trả về true nếu tồn tại
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
