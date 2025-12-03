import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private BasicDataSource dataSource;

    public DataManager() {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/northwind");
        dataSource.setUsername("root");
        dataSource.setPassword("yearup"); // <- change this
    }

    // 1. INSERT new shipper, return new id
    public int insertShipper(String name, String phone) {
        String sql = "INSERT INTO Shippers (CompanyName, Phone) VALUES (?, ?)";
        int newId = -1;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, name);
            ps.setString(2, phone);
            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                newId = keys.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return newId;
    }

    // 2. SELECT all shippers
    public List<Shipper> getAllShippers() {
        List<Shipper> shippers = new ArrayList<>();

        String sql = "SELECT ShipperID, CompanyName, Phone FROM Shippers ORDER BY ShipperID";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("ShipperID");
                String name = rs.getString("CompanyName");
                String phone = rs.getString("Phone");
                shippers.add(new Shipper(id, name, phone));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return shippers;
    }

    // 3. UPDATE shipper phone
    public void updateShipperPhone(int shipperId, String newPhone) {
        String sql = "UPDATE Shippers SET Phone = ? WHERE ShipperID = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, newPhone);
            ps.setInt(2, shipperId);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // 4. DELETE shipper
    public void deleteShipper(int shipperId) {
        String sql = "DELETE FROM Shippers WHERE ShipperID = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, shipperId);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}