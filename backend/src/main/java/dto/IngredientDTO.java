/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Ingredient;
import entities.Item;
import entities.Recipe;

/**
 *
 * @author fskn
 */
public class IngredientDTO {
 
    private Integer ingredient_id;
    private int amount;

    public IngredientDTO() {
    }

    public IngredientDTO(Ingredient ing) {
        this.ingredient_id=ing.getIngredient_id();
        this.amount = ing.getAmount();
    }
    
    public IngredientDTO getIngredients(Ingredient ingredient){
        IngredientDTO ing = new IngredientDTO(ingredient);
        return ing;
    }

    public Integer getIngredient_id() {
        return ingredient_id;
    }

    public void setIngredient_id(Integer ingredient_id) {
        this.ingredient_id = ingredient_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
