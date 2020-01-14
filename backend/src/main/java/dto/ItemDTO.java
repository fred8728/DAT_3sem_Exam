/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Ingredient;
import entities.Item;
import entities.Storage;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author fskn
 */
public class ItemDTO {
    private Integer item_id;
    private String name;
    private int price_kg;
    private List<Ingredient> ingredients = new ArrayList();
    private Storage storage;

    public ItemDTO() {
    }

    public ItemDTO(Item item) {
        this.item_id = item.getItem_id();
        this.name = item.getName();
        this.price_kg = item.getPrice_kg();
        this.storage = item.getStorage();
    }

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice_kg() {
        return price_kg;
    }

    public void setPrice_kg(int price_kg) {
        this.price_kg = price_kg;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
    
    
    
}
