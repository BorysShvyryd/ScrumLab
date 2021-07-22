package pl.coderslab.dao;

import pl.coderslab.utils.DbUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlanDao {

    private static final String NUMBER_USER_PLAN_QUERY = "SELECT COUNT(id) AS count_id FROM plan WHERE admin_id = ?";

    /**
     * Metodę pobierającą liczbę planów dodanych przez aktualnie zalogowanego użytkownika.
     * @param adminId - id aktualnie zalogowanego użytkownika
     * @return - liczbę planów
     */
    public static int getNumberPlanByAdmin(int adminId) {
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
