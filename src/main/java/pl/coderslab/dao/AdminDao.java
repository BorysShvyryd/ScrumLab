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

    private static final String NUMBER_USER_RECIPE_QUERY = "SELECT COUNT(id) AS count_id FROM recipe WHERE admin_id = ?";

    /**
     * Metodę pobierającą liczbę przepisów dodanych przez aktualnie zalogowanego użytkownika
     *
     * @param adminId - id aktualnie zalogowanego użytkownika
     * @return - liczbę przepisów
     */

    public static int RecipeDao(int adminId) {
        try (PreparedStatement ps = DbUtil.getConnection().prepareStatement(NUMBER_USER_RECIPE_QUERY)) {
            ps.setInt(1, adminId);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next())
                return resultSet.getInt("count_id");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    private static final String NUMBER_USER_PLAN_QUERY = "SELECT COUNT(id) AS count_id FROM plan WHERE admin_id = ?";

    /**
     * Metodę pobierającą liczbę planów dodanych przez aktualnie zalogowanego użytkownika.
     * @param adminId - id aktualnie zalogowanego użytkownika
     * @return - liczbę planów
     */
    public static int PlanDao (int adminId) {
        try (PreparedStatement ps = DbUtil.getConnection().prepareStatement(NUMBER_USER_PLAN_QUERY)) {
            ps.setInt(1, adminId);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next())
                return resultSet.getInt("count_id");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
}
