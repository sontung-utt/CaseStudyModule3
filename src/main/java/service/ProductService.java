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

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public int findIndexById(int id) {
        return 0;
    }

    @Override
    public Product findById(int id) {
        return null;
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
}
