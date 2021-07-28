package pl.coderslab.dao;

import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RecipePlanDao {

    private static final String INSERT_RECIPE_PLAN_QUERY =
            "INSERT INTO scrumlab.recipe_plan (" +
                    "recipe_id, meal_name, display_order, day_name_id, plan_id) " +
            "VALUES (?, ?, ?, ?, ?)";

    public static void saveRecipePlanToDB(int recipeId, String mealName, int displayOrder, int dayId, int planId) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(INSERT_RECIPE_PLAN_QUERY);
            ps.setInt(1, recipeId);
            ps.setString(2, mealName);
            ps.setInt(3, displayOrder);
            ps.setInt(4, dayId);
            ps.setInt(5, planId);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
