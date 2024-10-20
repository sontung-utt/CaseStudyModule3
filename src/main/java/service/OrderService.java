package service;

import model.Order;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderService{
    private final Connection connection = ConnectToMySQL.getConnection();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public OrderService(){
    }

//    public void add(Order order) {
//        String sql = "insert into `order` (total, idCart, idCustomer)\n" +
//                "values (?,?,?);";
//        try {
//            assert connection != null;
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setDouble(1,order.getTotal());
//            preparedStatement.setInt(2,order.getIdCart());
//            preparedStatement.setInt(3,order.getIdCustomer());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }


    public void update(int id, Order order) {
        String sql = "update `order` \n" +
                "set status = ?\n" +
                "where id = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,order.getStatus());
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void delete(int id) {

    }


    public int findIndexById(int id) {
        return 0;
    }


    public Order findById(int id) {
        Order order = null;
        String sql = "select a.*,b.name as nameCustomer from `order` a join customer b on a.idCustomer = b.id where a.id = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                id = resultSet.getInt("id");
                LocalDateTime time = resultSet.getTimestamp("time").toLocalDateTime();
                String formattedTime = time.format(formatter);
                double total = resultSet.getDouble("total");
                int idCart = resultSet.getInt("idCart");
                int idCustomer = resultSet.getInt("idCustomer");
                String nameCustomer = resultSet.getString("nameCustomer");
                String status = resultSet.getString("status");
                order = new Order(id,formattedTime,total,idCart,idCustomer,status,nameCustomer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return order;
    }


    public List<Order> getAll() {
        List<Order> orderList = new ArrayList<>();
        String sql = "select a.*,b.name as nameCustomer from `order` a join customer b on a.idCustomer = b.id;";
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
                int idCustomer = resultSet.getInt("idCustomer");
                String nameCustomer = resultSet.getString("nameCustomer");
                String status = resultSet.getString("status");
                Order order = new Order(id,formattedTime,total,idCart,idCustomer,status,nameCustomer);
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderList;
    }

    public List<Order> getOrderListByIdCustomer(int idCustomer) {
        List<Order> orderList = new ArrayList<>();
        String sql = "select * from `order` where idCustomer = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,idCustomer);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                LocalDateTime time = resultSet.getTimestamp("time").toLocalDateTime();
                String formattedTime = time.format(formatter);
                double total = resultSet.getDouble("total");
                int idCart = resultSet.getInt("idCart");
                String status = resultSet.getString("status");
                Order order = new Order(id,formattedTime,total,idCart,idCustomer,status);
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderList;
    }

    public int getIdOrderByIdCustomer(int idCustomer) {
        String sql = "select a.id as id from `order` a\n" +
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

    public int add(Order order) {
        String sql = "INSERT INTO `order` (total, idCart, idCustomer) VALUES (?, ?, ?)";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDouble(1, order.getTotal());
            preparedStatement.setInt(2, order.getIdCart());
            preparedStatement.setInt(3, order.getIdCustomer());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Lấy id của đơn hàng mới thêm
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    public double getTotalById(int idOrder){
        String sql = "select total from `order` where id = ?;";
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,idOrder);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getDouble("total");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public List<Order> searchOrderList(String nameCustomer){
        String sql = "select a.*,b.name as nameCustomer from `order` a join customer b on a.idCustomer = b.id where b.name like ?;";
        List<Order> orderList = new ArrayList<>();
        try {
            assert connection != null;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"%"+nameCustomer+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                LocalDateTime time = resultSet.getTimestamp("time").toLocalDateTime();
                String formattedTime = time.format(formatter);
                double total = resultSet.getDouble("total");
                int idCart = resultSet.getInt("idCart");
                int idCustomer = resultSet.getInt("idCustomer");
                nameCustomer = resultSet.getString("nameCustomer");
                String status = resultSet.getString("status");
                Order order = new Order(id,formattedTime,total,idCart,idCustomer,status,nameCustomer);
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderList;
    }
}
