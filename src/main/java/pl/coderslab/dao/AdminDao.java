package pl.coderslab.dao;

import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.utils.DbUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao {
    private static final String SEARCH_ADMIN_FOR_EMAIL_QUERY = "SELECT id, email, password FROM admins WHERE email = ?";

    /**
     * Metoda umożliwiające sprawdzanie danych autoryzacyjnych.
     *
     * @param email - wprowadzony email użytkownika
     * @param pass - wprowadzone hasło użytkownika
     * @return - metoda zwraca:
     *      przy autoryzacji - id użytkownika,
     *      '-1' - jeśli użytkownik z takim adresem e-mail nie istnieje,
     *      '0' - jeśli zostanie wprowadzone nieprawidłowe hasło.
     */
    public static int verificationOfAdminData(String email, String pass) {
        try (PreparedStatement ps = DbUtil.getConnection().prepareStatement(SEARCH_ADMIN_FOR_EMAIL_QUERY)) {
            ps.setString(1, email);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                if (BCrypt.checkpw(pass, resultSet.getString("password")))
                    return resultSet.getInt("id");
                else
                    return 0;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }
}
