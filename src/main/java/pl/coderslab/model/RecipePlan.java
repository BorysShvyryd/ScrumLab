package pl.coderslab.model;

public class RecipePlan {

    private int id;
    private int recipe_id;
    private String meal_name;
    private int display_order;
    private int day_name_id;
    private int plan_id;

    public RecipePlan(int recipe_id, String meal_name, int display_order, int day_name_id, int plan_id) {
        this.recipe_id = recipe_id;
        this.meal_name = meal_name;
        this.display_order = display_order;
        this.day_name_id = day_name_id;
        this.plan_id = plan_id;
    }

    public int getId() {
        return id;
    }

    public int getRecipe_id() {
        return recipe_id;
    }

    public RecipePlan setRecipe_id(int recipe_id) {
        this.recipe_id = recipe_id;
        return this;
    }

    public String getMeal_name() {
        return meal_name;
    }

    public RecipePlan setMeal_name(String meal_name) {
        this.meal_name = meal_name;
        return this;
    }

    public int getDisplay_order() {
        return display_order;
    }

    public RecipePlan setDisplay_order(int display_order) {
        this.display_order = display_order;
        return this;
    }

    public int getDay_name_id() {
        return day_name_id;
    }

    public RecipePlan setDay_name_id(int day_name_id) {
        this.day_name_id = day_name_id;
        return this;
    }

    public int getPlan_id() {
        return plan_id;
    }

    public RecipePlan setPlan_id(int plan_id) {
        this.plan_id = plan_id;
        return this;
    }
}
