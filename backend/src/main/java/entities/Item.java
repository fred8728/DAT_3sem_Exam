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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author fskn
 */
@Entity
@NamedQuery(name = "Item.deleteAllRows", query = "DELETE FROM Item")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer item_id;
    private String name;
    private int price_kg;
    
    @OneToMany(mappedBy = "item_id", cascade=CascadeType.PERSIST)
    private List<Ingredient> ingredients = new ArrayList();
    
    @ManyToOne (cascade=CascadeType.PERSIST)
    private Storage storage;
    

    public Item() {
    }

    public Item(String name, int price_kg) {
        this.name = name;
        this.price_kg = price_kg;
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
