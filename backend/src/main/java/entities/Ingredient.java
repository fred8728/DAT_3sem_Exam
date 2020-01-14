/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 *
 * @author fskn
 */
@Entity
@NamedQuery(name = "Ingredient.deleteAllRows", query = "DELETE FROM Ingredient")
public class Ingredient implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ingredient_id;
    private int amount;
    
    
    @ManyToOne (cascade=CascadeType.PERSIST)
    private Item item_id;

    public Ingredient() {
    }

    public Ingredient(Item item_id, int amount) {
        this.item_id = item_id;
        this.amount = amount;
        
    }

    public Integer getIngredient_id() {
        return ingredient_id;
    }

    public void setIngredient_id(Integer recipe_id) {
        this.ingredient_id = recipe_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Item getItem() {
        return item_id;
    }

    public void setItem(Item item_id) {
        this.item_id = item_id;
    }

   
}
