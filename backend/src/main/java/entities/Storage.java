/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author fskn
 */
@Entity
@NamedQuery(name = "Storage.deleteAllRows", query = "DELETE FROM Storage")
public class Storage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer storage_id;
    private int amount;
    
    @OneToMany(mappedBy = "storage")
    private List<Item> item_id = new ArrayList();

    public Storage() {
    }
    
    public Storage(int amount) {
        this.amount = amount;
    }

    public Integer getId() {
        return storage_id;
    }

    public void setId(Integer id) {
        this.storage_id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<Item> getItem_id() {
        return item_id;
    }

    public void setItem_id(List<Item> item_id) {
        this.item_id = item_id;
    }
   
}
