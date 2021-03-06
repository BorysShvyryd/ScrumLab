package pl.coderslab.dao;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Admin;
import pl.coderslab.utils.DbUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {

    private static final String CREATE_ADMIN_QUERY = "INSERT INTO admins(first_name, last_name, email, password, superadmin) VALUES (?, ?, ?, ?, ?);";
    private static final String DELETE_ADMIN_QUERY = "DELETE FROM admins WHERE id = ?;";
    private static final String FIND_ALL_ADMINS_QUERY = "SELECT * FROM admins;";
    private static final String READ_ADMIN_QUERY = "SELECT * FROM admins WHERE id = ?;";
    private static final String UPDATE_ADMIN_QUERY = "UPDATE admins SET first_name = ?, last_name = ?, email = ?, password = ?, superadmin = ?, enable = ? WHERE id = ?;";

    public Admin create(Admin admin) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement =
                     conn.prepareStatement(CREATE_ADMIN_QUERY, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, admin.getFirstName());
            statement.setString(2, admin.getLastName());
            statement.setString(3, admin.getEmail());
            statement.setString(4, hashPassword(admin.getPassword()));
            statement.setInt(5, admin.getSuperadmin());
//            statement.setInt(6, admin.getEnable());
            int result = statement.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    admin.setId(generatedKeys.getInt(1));
                    return admin;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static void delete(int id) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(DELETE_ADMIN_QUERY)) {

            statement.setInt(1, id);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("User not found");
            }
        } catch (Exception e) {
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
                    admin.setId(resultSet.getInt("id"));
                    admin.setFirstName(resultSet.getString("first_name"));
                    admin.setLastName(resultSet.getString("last_name"));
                    admin.setEmail(resultSet.getString("email"));
                    admin.setPassword(resultSet.getString("password"));
                    admin.setSuperadmin(resultSet.getInt("superadmin"));
                    admin.setEnable(resultSet.getInt("enable"));
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
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

    private static final String BLOCK_USER_QUERY = "UPDATE admins SET enable = ? WHERE id = ?";

    /**
     * Metoda blokowania u??ytkownika
     *
     * @param idUser - id u??ytkownika
     * @param blok - parametr (0 - blok user, 1 - unblok user)
     */
    public static void blokUnblokUser(int idUser, int blok) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(BLOCK_USER_QUERY)) {

            statement.setInt(1, blok);
            statement.setInt(2, idUser);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static final String SEARCH_ADMIN_FOR_EMAIL_QUERY = "SELECT id, email, password FROM admins WHERE email = ?";

    /**
     * Metoda umo??liwiaj??ce sprawdzanie danych autoryzacyjnych.
     *
     * @param email - wprowadzony email u??ytkownika
     * @param pass - wprowadzone has??o u??ytkownika
     * @return - metoda zwraca:
     *      przy autoryzacji - obiekt Admin u??ytkownika,
     *      'null' - je??li u??ytkownik z takim adresem e-mail nie istnieje,
     */
    public static Admin verificationOfAdminData(String email, String pass) {
        try (PreparedStatement ps = DbUtil.getConnection().prepareStatement(SEARCH_ADMIN_FOR_EMAIL_QUERY)) {
            ps.setString(1, email);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                if (BCrypt.checkpw(pass, resultSet.getString("password"))) {
                    return read(Integer.parseInt(resultSet.getString("id")));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Metoda przechowuje obiekt zalogowanego u??ytkownika w sesji
     *
     * @param session      - HttpSession session
     * @param loginedAdmin - obiekt Admin zalogowanego u??ytkownika
     */
    public static void storeLoginedUser(HttpSession session, Admin loginedAdmin) {
        session.setAttribute("loginedAdmin", loginedAdmin);
    }

    /**
     *  Metoda pobiera obiekt zalogowanego u??ytkownika
     * @param session - HttpSession session
     * @return - obiekt Admin zalogowanego u??ytkownika
     */
    public static Admin getLoginedAdmin(HttpSession session) {
        return (Admin) session.getAttribute("loginedAdmin");
    }
}
