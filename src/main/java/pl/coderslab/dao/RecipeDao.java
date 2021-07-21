package pl.coderslab.dao;

import pl.coderslab.exception.NotFoundException;
import pl.coderslab.model.Recipe;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeDao {

    private static final String CREATE_RECIPE_QUERY =
            "INSERT INTO recipe(name, ingredients, description, created, updated, preparation_time, preparation, admin_id) " +
                    "VALUES (?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?, ?, ?);";
    private static final String DELETE_BOOK_QUERY = "DELETE FROM recipe WHERE id = ?;";
    private static final String FIND_ALL_RECIPES_QUERY = "SELECT * FROM recipe;";
    private static final String READ_RECIPE_QUERY = "SELECT * FROM recipe WHERE id = ?;";
    private static final String UPDATE_RECIPE_QUERY = "UPDATE recipe SET name = ?, ingredients = ?, description = ?, " +
            "updated = CURRENT_TIMESTAMP, preparation_time = ?, preparation = ? WHERE id = ?;";

    public Recipe create (Recipe recipe) throws SQLException {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insert =connection.prepareStatement(CREATE_RECIPE_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insert.setString(1, recipe.getName());
            insert.setString(2, recipe.getIngredients());
            insert.setString(3, recipe.getDescription());
            insert.setInt(4, recipe.getPreparation_time());
            insert.setString(5, recipe.getPreparation());
            insert.setInt(6, recipe.getAdminId());
            int result = insert.executeUpdate();

            if (result != 1) {
                throw new RuntimeException("Execute update returned " + result);
            }

            try (ResultSet generatedKeys = insert.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    recipe.setId(generatedKeys.getInt(1));
                    return recipe;
                }else {
                    throw new RuntimeException("Generated key was not found");
                }
            }
        }catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }


    public void delete (Integer recipeID) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement delete = connection.prepareStatement(DELETE_BOOK_QUERY)) {
            delete.setInt(1, recipeID);
            delete.executeUpdate();

            boolean isDelete = delete.execute();
            if (!isDelete) {
                throw new NotFoundException("Recipe not found");
            }
        }catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public List<Recipe> findAll() {
        List<Recipe> recipeList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement findAll = connection.prepareStatement(FIND_ALL_RECIPES_QUERY);
             ResultSet resultSet = findAll.executeQuery()) {
            while (resultSet.next()) {
                Recipe recipeToAdd = new Recipe();
                recipeToAdd.setId(resultSet.getInt("id"));
                recipeToAdd.setName(resultSet.getString("name"));
                recipeToAdd.setIngredients(resultSet.getString("ingredients"));
                recipeToAdd.setDescription(resultSet.getString("description"));
                recipeToAdd.setCreated(resultSet.getString("created"));
                recipeToAdd.setUpdated(resultSet.getString("updated"));
                recipeToAdd.setPreparation_time(resultSet.getInt("preparation_time"));
                recipeToAdd.setPreparation(resultSet.getString("preparation"));
                recipeToAdd.setAdminId(resultSet.getInt("admin_id"));

                recipeList.add(recipeToAdd);
            }
        }catch (SQLException exception) {
            exception.printStackTrace();
        }
        return recipeList;
    }

    public Recipe read (Integer recipeId) {
        Recipe recipe = new Recipe();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement read = connection.prepareStatement(READ_RECIPE_QUERY)
        ) {
            read.setInt(1, recipeId);
            try (ResultSet resultSet = read.executeQuery()) {
                while (resultSet.next()) {
                    recipe.setId(resultSet.getInt("id"));
                    recipe.setName(resultSet.getString("name"));
                    recipe.setIngredients(resultSet.getString("ingredients"));
                    recipe.setDescription(resultSet.getString("description"));
                    recipe.setCreated(resultSet.getString("created"));
                    recipe.setUpdated(resultSet.getString("updated"));
                    recipe.setPreparation_time(resultSet.getInt("preparation_time"));
                    recipe.setPreparation(resultSet.getString("preparation"));
                    recipe.setAdminId(resultSet.getInt("admin_id"));
                }
            }
        }catch (Exception exception) {
            exception.printStackTrace();
        }
        return recipe;
    }

    public void update (Recipe recipe) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement updateStatment = connection.prepareStatement(UPDATE_RECIPE_QUERY)) {
            updateStatment.setString(1, recipe.getName());
            updateStatment.setString(2, recipe.getIngredients());
            updateStatment.setString(3, recipe.getDescription());
            updateStatment.setInt(4, recipe.getPreparation_time());
            updateStatment.setString(5, recipe.getPreparation());
            updateStatment.setInt(6, recipe.getId());
            updateStatment.executeUpdate();

        }catch (Exception exception){
            exception.printStackTrace();
        }
    }


}
