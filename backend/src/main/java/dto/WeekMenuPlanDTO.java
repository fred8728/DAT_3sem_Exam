/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Recipe;
import entities.WeekMenuPlan;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fskn
 */
public class WeekMenuPlanDTO {
    
    private Integer menu_id;
    private int week_number;
    private int year;
    private List<RecipeDTO> recipes = new ArrayList();

    public WeekMenuPlanDTO() {
    }

    public WeekMenuPlanDTO(WeekMenuPlan wmp) {
        this.menu_id = wmp.getMenu_id();
        this.week_number = wmp.getWeek_number();
        this.year = year = wmp.getYear();
    }
    
    public WeekMenuPlanDTO getWeekMenus(WeekMenuPlan wmp){
        WeekMenuPlanDTO weekMenu = new WeekMenuPlanDTO(wmp);
        return weekMenu;
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

    public List<RecipeDTO> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeDTO> recipes) {
        this.recipes = recipes;
    }
    

}
