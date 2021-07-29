package pl.coderslab.dao;

import pl.coderslab.model.PlanList;
import pl.coderslab.utils.DbUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;


import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Plan;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class PlanDao {
    private static final String LAST_ADDED_PLAN_QUERY = "SELECT day_name.name as day_name, meal_name,  recipe.name as recipe_name, recipe.description as recipe_description\n" +
            "FROM `recipe_plan`\n" +
            "         JOIN day_name on day_name.id=day_name_id\n" +
            "         JOIN recipe on recipe.id=recipe_id WHERE\n" +
            "        recipe_plan.plan_id =  (SELECT MAX(id) from plan WHERE admin_id = ?)\n" +
            "ORDER by day_name.display_order, recipe_plan.display_order;";
    private static final String ALL_RECIPE_BY_PLAN_FROM_ADMIN = "SELECT day_name.name as day_name, meal_name, recipe.name as recipe_name, recipe.description as recipe_description " +
            "FROM `recipe_plan`" +
            "         JOIN day_name on day_name.id=day_name_id" +
            "         JOIN recipe on recipe.id=recipe_id WHERE plan_id = ? " +
            "ORDER by day_name.display_order, recipe_plan.display_order";
    private static final String NUMBER_USER_PLAN_QUERY = "SELECT COUNT(id) AS count_id FROM plan WHERE admin_id = ?";
    private static final String CREATE_PLAN_QUERY = "INSERT INTO plan (name, description, created, admin_id) VALUES (?,?, CURRENT_TIMESTAMP, ?);";
    private static final String DELETE_PLAN_QUERY = "DELETE FROM plan where id = ?;";
    private static final String FIND_ALL_PLANS_QUERY = "SELECT * FROM plan;";
    private static final String READ_PLAN_QUERY = "SELECT * from plan where id = ?;";
    private static final String UPDATE_PLAN_QUERY = "UPDATE	plan SET name = ? , description = ?, created = CURRENT_TIMESTAMP, admin_id = ? WHERE id = ?;";
    private static final String FIND_NEW_PLAN = "SELECT MAX(id) from plan WHERE admin_id = ?";

    public static Plan read(Integer planId) {
        Plan plan = new Plan();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_PLAN_QUERY)
        ) {
            statement.setInt(1, planId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    plan.setId(resultSet.getInt("id"));
                    plan.setName(resultSet.getString("name"));
                    plan.setDescription(resultSet.getString("description"));
                    plan.setCreated(resultSet.getString("created"));
                    plan.setAdminId(resultSet.getInt("admin_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plan;

    }

    public static List<Plan> findAll() {
        List<Plan> planList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_PLANS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Plan planToAdd = new Plan();
                planToAdd.setId(resultSet.getInt("id"));
                planToAdd.setName(resultSet.getString("name"));
                planToAdd.setDescription(resultSet.getString("description"));
                planToAdd.setCreated(resultSet.getString("created"));
                planToAdd.setAdminId(resultSet.getInt("admin_id"));
                planList.add(planToAdd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planList;

    }

    public static Plan create(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_PLAN_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, plan.getName());
            insertStm.setString(2, plan.getDescription());
            insertStm.setInt(3, plan.getAdminId());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    plan.setId(generatedKeys.getInt(1));
                    return plan;
                } else {
                    throw new RuntimeException("Generated key was not found");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void delete(Integer planId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PLAN_QUERY)) {
            statement.setInt(1, planId);
            statement.executeUpdate();

            boolean deleted = statement.execute();
            if (!deleted) {
                throw new NotFoundException("Product not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Plan plan) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PLAN_QUERY)) {
            statement.setString(1, plan.getName());
            statement.setString(2, plan.getDescription());
            statement.setString(3, plan.getCreated());
            statement.setInt(4, plan.getAdminId());
            statement.executeUpdate();

            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodę pobierającą liczbę planów dodanych przez aktualnie zalogowanego użytkownika.
     *
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
    public static List<List <String>> getDetailsFromPlan(int plan_id) {
        List<List <String>> result = new ArrayList<List<String>>();
        try (PreparedStatement ps = DbUtil.getConnection().prepareStatement(ALL_RECIPE_BY_PLAN_FROM_ADMIN)) {
            ps.setInt(1, plan_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                List<String> rowResult = new ArrayList<String>();
                rowResult.add(rs.getString(1));
                rowResult.add(rs.getString(2));
                rowResult.add(rs.getString(3));
                rowResult.add(rs.getString(4));
                result.add(rowResult);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }


    public static List<PlanList> lastPlan(int adminId) throws SQLException {
        List<PlanList> planList= new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(LAST_ADDED_PLAN_QUERY)
        ) {
            statement.setInt(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                PlanList planToAdd = new PlanList();
                planToAdd.setDayName(resultSet.getString("day_name"));
                planToAdd.setMealName(resultSet.getString("meal_name"));
                planToAdd.setRecipeName(resultSet.getString("recipe_name"));
                planToAdd.setRecipeDescription(resultSet.getString("recipe_description"));
                planList.add(planToAdd);
            }
            catch (SQLException e){
                e.printStackTrace();
            }
            return planList;
        }
    }
}