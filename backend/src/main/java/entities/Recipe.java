/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author fskn
 */
@Entity
@NamedQuery(name = "Recipe.deleteAllRows", query = "DELETE FROM Recipe")
public class Recipe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recipe_id;
    private String name;
    private int preparation_time;
    private String directions;
    
    @ManyToMany (cascade=CascadeType.PERSIST)
    private List <WeekMenuPlan> weekMenu = new ArrayList();
    
    
    @OneToMany (cascade=CascadeType.PERSIST)
    private List <Ingredient> ingredients = new ArrayList();

    public Recipe() {
    }

    public Recipe(String name, List<Ingredient> ing, int preparation_time, String directions) {
        this.name=name;
        this.ingredients=ingredients;
        this.preparation_time = preparation_time;
        this.directions = directions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<WeekMenuPlan> getWeekMenu() {
        return weekMenu;
    }

    public void setWeekMenu(List<WeekMenuPlan> weekMenu) {
        this.weekMenu = weekMenu;
    }
    
    public void addWeekMenu(WeekMenuPlan wmp){
        weekMenu.add(wmp);
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
    
    public void addIngredient(Ingredient ing){
        ingredients.add(ing);
    }

    @Override
    public String toString() {
        return "Recipe{" + "recipe_id=" + recipe_id + ", name=" + name + ", preparation_time=" + preparation_time + ", directions=" + directions + ", weekMenu=" + weekMenu + ", ingredients=" + ingredients + '}';
    }

}
