package service;

import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderService implements IService<Order>{
    private final Connection connection = ConnectToMySQL.getConnection();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public OrderService(){
    }
    @Override
    public void add(Order order) {
        String sql = "insert into `order` (total, idCart)\n" +
                "values (?,?,?);";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1,order.getTotal());
            preparedStatement.setInt(2,order.getIdCart());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(int id, Order order) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public int findIndexById(int id) {
        return 0;
    }

    @Override
    public Order findById(int id) {
        return null;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orderList = new ArrayList<>();
        String sql = "select * from `order`;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                LocalDateTime time = resultSet.getTimestamp("time").toLocalDateTime();
                String formattedTime = time.format(formatter);
                double total = resultSet.getDouble("total");
                int idCart = resultSet.getInt("idCart");
                Order order = new Order(id,formattedTime,total,idCart);
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderList;
    }
}
