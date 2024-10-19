package service;

import model.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailService implements IService<OrderDetail>{
    private final Connection connection = ConnectToMySQL.getConnection();
    public OrderDetailService(){

    }
    @Override
    public void add(OrderDetail orderDetail) {
        String sql = "insert into orderdetail(idOrder, idProduct, quantity, price)\n" +
                "values (?,?,?,?);";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,orderDetail.getIdOrder());
            preparedStatement.setInt(2,orderDetail.getIdProduct());
            preparedStatement.setInt(3,orderDetail.getQuantity());
            preparedStatement.setDouble(4,orderDetail.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int id, OrderDetail orderDetail) {

    }

    @Override
    public void delete(int id) {
        String sql = "delete from orderdetail where id = ?;";
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
    public OrderDetail findById(int id) {
        return null;
    }

    @Override
    public List<OrderDetail> getAll() {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        String sql = "select a.*, b.name as nameProduct from orderdetail a \n" +
                "join product b on a.idProduct = b.id;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderDetailList;
    }

    public List<OrderDetail> listOrderDetailById(int idOrder){
        List<OrderDetail> orderDetailList = new ArrayList<>();
        String sql = "select a.*, b.name as nameProduct from orderdetail a\n" +
                "join product b on a.idProduct = b.id\n" +
                "join `order` c on a.idOrder = c.id\n" +
                "where c.id = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,idOrder);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int idProduct = resultSet.getInt("idProduct");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                String nameProduct = resultSet.getString("nameProduct");
                OrderDetail orderDetail = new OrderDetail(id,idOrder,idProduct,quantity,price,nameProduct);
                orderDetailList.add(orderDetail);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderDetailList;
    }
}
