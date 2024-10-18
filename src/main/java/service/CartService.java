package service;

import model.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartService implements IService<Cart>{
    private final Connection connection = ConnectToMySQL.getConnection();
    public CartService(){

    }
    @Override
    public void add(Cart cart) {
        String sql = "insert into cart (idCustomer)\n" +
                "values (?);";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,cart.getIdCustomer());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int id, Cart cart) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public int findIndexById(int id) {
        return 0;
    }

    @Override
    public Cart findById(int id) {
        String sql = "select * from cart where id = ?;";
        Cart cart = null;
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int idCustomer = resultSet.getInt("idCustomer");
                cart = new Cart(id,idCustomer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cart;
    }

    @Override
    public List<Cart> getAll() {
        String sql = "select * from cart;";
        List<Cart> cartList = new ArrayList<>();
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int idCustomer = resultSet.getInt("idCustomer");
                Cart cart = new Cart(id, idCustomer);
                cartList.add(cart);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cartList;
    }

    public int getIdCartByIdCustomer (int idCustomer) {
        String sql = "select a.id as id from cart a\n" +
                "join customer b on a.idCustomer = b.id\n" +
                "where idCustomer = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,idCustomer);
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
