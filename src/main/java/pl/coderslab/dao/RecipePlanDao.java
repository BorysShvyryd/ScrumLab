package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.RecipePlan;
import pl.coderslab.utils.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RecipePlanDao {

    private static final String INSERT_RECIPE_PLAN_QUERY =
            "INSERT INTO scrumlab.recipe_plan (" +
                    "recipe_id, meal_name, display_order, day_name_id, plan_id) " +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE_RECIPE_IN_PLAN_QUERY =
            "DELETE FROM recipe_plan WHERE id=?";

    public static void saveRecipePlanToDB(RecipePlan recipePlan) {
        try (Connection connection = DbUtil.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(INSERT_RECIPE_PLAN_QUERY);
            ps.setInt(1, recipePlan.getRecipe_id());
            ps.setString(2, recipePlan.getMeal_name());
            ps.setInt(3, recipePlan.getDisplay_order());
            ps.setInt(4, recipePlan.getDay_name_id());
            ps.setInt(5, recipePlan.getPlan_id());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void deleteRecipePlanInDB(int recipe_plan_id) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(DELETE_RECIPE_IN_PLAN_QUERY)) {

            statement.setInt(1, recipe_plan_id);
            statement.executeUpdate();

            boolean resultDelete = statement.execute();
            if (!resultDelete) {
                throw new NotFoundException("Not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
