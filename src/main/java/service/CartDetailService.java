package service;

import model.CartDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CartDetailService implements IService<CartDetail>{
    private final Connection connection = ConnectToMySQL.getConnection();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    public CartDetailService(){

    }
    @Override
    public void add(CartDetail cartDetail) {
        String sql = "insert into cartDetail (idCart, idProduct, quantity, price)\n" +
                "values (?,?,?,?);";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,cartDetail.getIdCart());
            preparedStatement.setInt(2,cartDetail.getIdProduct());
            preparedStatement.setInt(3,cartDetail.getQuantity());
            preparedStatement.setDouble(4,cartDetail.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int id, CartDetail cartDetail) {
        String sql = "update cartDetail \n" +
                "set quantity = ?\n" +
                "where idProduct = ? and idCart = ? and id = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,cartDetail.getQuantity());
            preparedStatement.setInt(2,cartDetail.getIdProduct());
            preparedStatement.setInt(3,cartDetail.getIdCart());
            preparedStatement.setInt(4,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from cartDetail where id = ?;";
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
    public CartDetail findById(int id) {
        return null;
    }

    @Override
    public List<CartDetail> getAll() {
        List<CartDetail> cartDetailList = new ArrayList<>();
        String sql = "select a.*, b.name as nameProduct from cartdetail a \n" +
                "join product b on a.idProduct = b.id;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idCart = resultSet.getInt("idCart");
                int idProduct = resultSet.getInt("idProduct");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                LocalDateTime created_at = resultSet.getTimestamp("created_at").toLocalDateTime();
                String formattedCreatedAt = created_at.format(formatter);
                String nameProduct = resultSet.getString("nameProduct");
                CartDetail cartDetail = new CartDetail(id, idCart, idProduct, quantity, price, formattedCreatedAt, nameProduct);
                cartDetailList.add(cartDetail);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cartDetailList;
    }

    public boolean checkExistProductInCart(int idCart, int idProduct){
        String sql = "select count(*) from cartDetail where idCart = ? and idProduct = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,idCart);
            preparedStatement.setInt(2,idProduct);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public CartDetail findByIdProduct(int idCart, int idProduct) {
        String sql = "select * from cartDetail where idCart = ? and idProduct = ?;";
        CartDetail cartDetail = null;
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,idCart);
            preparedStatement.setInt(2,idProduct);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int quantity = resultSet.getInt("quantity");
                cartDetail = new CartDetail(id,idCart,idProduct,quantity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cartDetail;
    }

    public int numProduct(int idCart, int idCustomer){
        String sql = "select count(idProduct) as numProduct from cartdetail a\n" +
                "join cart b on a.idCart = b.id\n" +
                "where b.id = ? and b.idCustomer = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,idCart);
            preparedStatement.setInt(2,idCustomer);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt("numProduct");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public List<CartDetail> getListCartByIdCart(int idCart) {
        List<CartDetail> cartDetailList = new ArrayList<>();
        String sql = "SELECT a.*, b.name as nameProduct FROM cartdetail a " +
                "JOIN product b ON a.idProduct = b.id " +
                "WHERE a.idCart = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idCart);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idProduct = resultSet.getInt("idProduct");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                LocalDateTime created_at = resultSet.getTimestamp("created_at").toLocalDateTime();
                String formattedCreatedAt = created_at.format(formatter);
                String nameProduct = resultSet.getString("nameProduct");
                CartDetail cartDetail = new CartDetail(id, idCart, idProduct, quantity, price, formattedCreatedAt, nameProduct);
                cartDetailList.add(cartDetail);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cartDetailList;
    }

    public double totalPerCartId(int idCart){
        String sql = "select sum(a.quantity * a.price) as totalPerCartId from cartdetail a where idCart = ?;";
        double total = 0;
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,idCart);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                total = resultSet.getDouble("totalPerCartId");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return total;
    }
}
