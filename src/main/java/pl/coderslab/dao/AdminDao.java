package pl.coderslab.dao;

import pl.coderslab.model.Admin;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {

    private static final String CREATE_ADMIN_QUERY = "INSERT INTO admins(id, first_name, last_name, email, password, superadmin, enable) VALUES (0, ?, ?, ?, ?, 0, 0);";
    private static final String DELETE_ADMIN_QUERY = "DELETE FROM admins WHERE id = ?;";
    private static final String FIND_ALL_ADMINS_QUERY = "SELECT * FROM admins;";
    private static final String READ_ADMIN_QUERY = "SELECT * FROM admins WHERE id = ?;";
    private static final String UPDATE_ADMIN_QUERY = "UPDATE admins SET first_name = ?, last_name = ?, email = ?, password = ?, superadmin = ?, enable = ? WHERE id = ?;";

    public static Admin create(Admin admin) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement =
                     conn.prepareStatement(CREATE_ADMIN_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, admin.getFirstName());
            statement.setString(2, admin.getLastName());
            statement.setString(3, admin.getEmail());
            statement.setString(4, admin.getPassword());
//            statement.setInt(5, admin.getSuperadmin());
//            statement.setInt(6, admin.getEnable());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                admin.setId(resultSet.getInt(1));
            }
            return admin;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void delete(int id) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(DELETE_ADMIN_QUERY)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Admin> findAll() {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(FIND_ALL_ADMINS_QUERY)) {

            List<Admin> list = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.setId(resultSet.getInt("id"));
                admin.setFirstName(resultSet.getString("first_name"));
                admin.setLastName(resultSet.getString("last_name"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPassword(resultSet.getString("password"));
                admin.setSuperadmin(resultSet.getInt("superadmin"));
                admin.setEnable(resultSet.getInt("enable"));
                list.add(admin);
            }

            if (list.isEmpty()) {
                return null;
            }

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Admin read(int adminId) {
        Admin admin = new Admin();
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(READ_ADMIN_QUERY)) {

            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    admin.setId(resultSet.getInt("adminId"));
                    admin.setFirstName(resultSet.getString("first_name"));
                    admin.setLastName(resultSet.getString("last_name"));
                    admin.setEmail(resultSet.getString("email"));
                    admin.setPassword(resultSet.getString("password"));
                    admin.setSuperadmin(resultSet.getInt("superadmin"));
                    admin.setEnable(resultSet.getInt("enable"));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return admin;
    }
    public static void update(Admin admin) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(UPDATE_ADMIN_QUERY)) {

            statement.setString(1, admin.getFirstName());
            statement.setString(2, admin.getLastName());
            statement.setString(3, admin.getEmail());
            statement.setString(4, admin.getPassword());
            statement.setInt(5, admin.getSuperadmin());
            statement.setInt(6, admin.getEnable());
            statement.setInt(7, admin.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
