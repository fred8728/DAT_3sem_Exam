/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import dto.WeekMenuPlanDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

/**
 *
 * @author fskn
 */
@Entity
@NamedQuery(name = "WeekMenuPlan.deleteAllRows", query = "DELETE FROM WeekMenuPlan")
public class WeekMenuPlan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer menu_id;
    private int week_number;
    private int year;
    
    @ManyToMany(mappedBy = "weekMenu")
    private List<Recipe> recipes = new ArrayList();

    public WeekMenuPlan() {
    }

    public WeekMenuPlan(int week_number, int year, List<Recipe> recipes) {
        this.week_number = week_number;
        this.year = year;
        this.recipes=recipes;
    }
    

    public Integer getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(Integer menu_id) {
        this.menu_id = menu_id;
    }

    public int getWeek_number() {
        return week_number;
    }

    public void setWeek_number(int week_number) {
        this.week_number = week_number;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
    
    public void addRecipe(Recipe recipe){
        this.recipes.add(recipe);
    }

    @Override
    public String toString() {
        return "WeekMenuPlan{" + "menu_id=" + menu_id + ", week_number=" + week_number + ", year=" + year + ", recipes=" + recipes + '}';
    }
}
