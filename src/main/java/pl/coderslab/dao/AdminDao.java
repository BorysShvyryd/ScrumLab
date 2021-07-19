package pl.coderslab.dao;

import pl.coderslab.utils.DbUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {
    private static final String SEARCH_FOR_EMAIL_AND_PASS_QUERY = "SELECT email, password FROM admins WHERE email = ? AND password = ?";

    public static boolean verificationOfAdminData (String email, String hashpass) {
        try (PreparedStatement ps = DbUtil.getConnection().prepareStatement(SEARCH_FOR_EMAIL_AND_PASS_QUERY)) {
            ps.setString(1, email);
            ps.setString(2, hashpass);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
