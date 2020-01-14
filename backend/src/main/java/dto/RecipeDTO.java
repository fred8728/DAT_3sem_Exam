/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Ingredient;
import entities.Recipe;
import entities.WeekMenuPlan;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fskn
 */
public class RecipeDTO {
    
    private Integer recipe_id;
    private String name;
    private int preparation_time;
    private String directions;
    private List <WeekMenuPlanDTO> weekMenu = new ArrayList();
    private List <IngredientDTO> ingredients = new ArrayList();
    

    public RecipeDTO() {
    }

    public RecipeDTO(Recipe recipe) {
        this.recipe_id = recipe.getRecipe_id();
        this.name=recipe.getName();
        this.preparation_time = recipe.getPreparation_time();
        this.directions = recipe.getDirections();
        for(WeekMenuPlan wmp : recipe.getWeekMenu()){
            weekMenu.add(new WeekMenuPlanDTO(wmp));
        }
        for(Ingredient ing : recipe.getIngredients()){
            ingredients.add(new IngredientDTO(ing));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public RecipeDTO getRecipes(Recipe r){
        RecipeDTO recipe = new RecipeDTO(r);
        return recipe;
    }

    public Integer getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(Integer recipe_id) {
        this.recipe_id = recipe_id;
    }

    public int getPreparation_time() {
        return preparation_time;
    }

    public void setPreparation_time(int preparation_time) {
        this.preparation_time = preparation_time;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public List<WeekMenuPlanDTO> getWeekMenu() {
        return weekMenu;
    }

    public void setWeekMenu(List<WeekMenuPlanDTO> weekMenu) {
        this.weekMenu = weekMenu;
    }
    
    public void addWeekMenu(WeekMenuPlanDTO wmp){
        this.weekMenu.add(wmp);
    }

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }
    
    public void addIngredient (IngredientDTO ing){
        this.ingredients.add(ing);
    }

    @Override
    public String toString() {
        return "RecipeDTO{" + "recipe_id=" + recipe_id + ", name=" + name + ", preparation_time=" + preparation_time + ", directions=" + directions + ", weekMenu=" + weekMenu + ", ingredients=" + ingredients + '}';
    }
}
