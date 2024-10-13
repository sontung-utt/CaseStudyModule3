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

    }

    @Override
    public void update(int id, BrandCategory brandCategory) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public int findIndexById(int id) {
        return 0;
    }

    @Override
    public BrandCategory findById(int id) {
        return null;
    }

    @Override
    public List<BrandCategory> getAll() {
        List<BrandCategory> list = new ArrayList<>();
        String sql = "select a.*, b.name as nameBrand, c.name as nameCategory from brandcategory a\n" +
                "join brand b on a.idBrand = b.id\n" +
                "join category c on a.idCategory = c.id;";
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
}
