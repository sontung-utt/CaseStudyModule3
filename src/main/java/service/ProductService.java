package service;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IService<Product>{
    private final Connection connection = ConnectToMySQL.getConnection();
    public ProductService(){

    }

    @Override
    public void add(Product product) {
        String sql = "insert into product(name, price, quantity, image, description, idBrandCategory)\n" +
                "values (?,?,?,?,?,?);";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,product.getName());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setInt(3,product.getQuantity());
            preparedStatement.setString(4,product.getImage());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6,product.getIdBrandCategory());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int id, Product product) {
        String sql = "update product a\n" +
                "set a.name = ?, a.price = ?, a.quantity = ?, a.image = ?, a.description = ?, a.idBrandCategory = ?\n" +
                "where a.id = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setInt(3,product.getQuantity());
            preparedStatement.setString(4,product.getImage());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6,product.getIdBrandCategory());
            preparedStatement.setInt(7,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from product where id = ?;";
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
    public Product findById(int id) {
        Product product = null;
        String sql = "select * from product where id = ?";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String image = resultSet.getString("image");
                String description = resultSet.getString("description");
                int idBrandCategory = resultSet.getInt("idBrandCategory");
                product = new Product(id,name,price,quantity,image,description,idBrandCategory);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();
        String sql = "select a.id as id,a.name as name, a.price as price, a.quantity as quantity,\n" +
                "       a.image as image, a.description as description, d.name as categoryName, c.name as brandName\n" +
                "from product a\n" +
                "join brandCategory b on a.idBrandCategory = b.id\n" +
                "join brand c on b.idBrand = c.id\n" +
                "join category d on b.idCategory = d.id;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String image = resultSet.getString("image");
                String description = resultSet.getString("description");
                String categoryName = resultSet.getString("categoryName");
                String brandName = resultSet.getString("brandName");
                Product product = new Product(id,name,price,quantity,image,description,categoryName,brandName);
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    public boolean existProduct(String name){
        String sql = "select count(*) from product where name = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"name");
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
