/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import errorhandling.NotFoundException;
import facades.RestaurantFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author fskn
 */
public class MakeTestData {

    public static void main(String[] args) throws NotFoundException, Exception {

        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.DROP_AND_CREATE);
        EntityManager em = emf.createEntityManager();

         //Recipe 1
        Item it1 = new Item("Gulerødder", 30);
        Item it2 = new Item("Æble", 20);
        List<Item> it = new ArrayList();
        it.add(it1);
        it.add(it2);

        Ingredient ingree = new Ingredient(it1, it1.getPrice_kg());
        Ingredient ingree1 = new Ingredient(it2, it2.getPrice_kg());
        List<Ingredient> in = new ArrayList();
        in.add(ingree);
        in.add(ingree1);

        Recipe rec1 = new Recipe("Råkost " ,in, 30, "Skræl gulerødder og æbler og riv dem på rive jern");
        List<Recipe> rec = new ArrayList();
        rec.add(rec1);

        Storage stor1 = new Storage(15000);
        Storage stor2 = new Storage(15000);
        
        rec1.addIngredient(ingree);
        rec1.addIngredient(ingree1);
        ingree.setItem(it1);
        ingree1.setItem(it2);
        it1.setStorage(stor1);
        it2.setStorage(stor2);
        
        
        // Recipe 2
        Item item = new Item("Spaghetti", 30);
        Item item2 = new Item("Oksekød", 350);
        Item item3 = new Item("Flådede Tomater", 60);
        Item item4 = new Item("Timian", 20);
        List<Item> items = new ArrayList();
        items.add(item);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        Ingredient ing1 = new Ingredient(item, item.getPrice_kg());
        Ingredient ing2 = new Ingredient(item2, item2.getPrice_kg());
        Ingredient ing3 = new Ingredient(item3, item3.getPrice_kg());
        Ingredient ing4 = new Ingredient(item4, item4.getPrice_kg());        
        List<Ingredient> ing = new ArrayList();
        ing.add(ing1);
        ing.add(ing2);
        ing.add(ing3);
        ing.add(ing4);
        Recipe r = new Recipe("Spaghetti kødsovs ", ing, 60, "Kog vand i en gryde og tilsæt spaghetti");
        rec.add(r);
        
        Storage st = new Storage(10000);
        Storage st1 = new Storage(10000);
        Storage st2 = new Storage(10000);
        Storage st3 = new Storage(10000);
        
        r.addIngredient(ing1);
        r.addIngredient(ing2);
        r.addIngredient(ing3);
        r.addIngredient(ing4);
        ing1.setItem(item);
        ing2.setItem(item2);
        ing3.setItem(item3);
        ing4.setItem(item4);
        item.setStorage(st);
        item2.setStorage(st1);
        item3.setStorage(st2);
        item4.setStorage(st3);
        
        // Recipe 3
        Item item5 = new Item("Rugbrød", 90);
        Item item6 = new Item("Kyllinge pålæg", 100);
        Item item7 = new Item("Agurk", 50);
        List<Item> items1 = new ArrayList();
        items1.add(item5);
        items1.add(item6);
        items1.add(item7);
        
        Ingredient ing5 = new Ingredient(item5, item5.getPrice_kg());
        Ingredient ing6 = new Ingredient(item6, item6.getPrice_kg());
        Ingredient ing7 = new Ingredient(item7, item7.getPrice_kg());
        List<Ingredient> ingre = new ArrayList();
        ingre.add(ing5);
        ingre.add(ing6);
        ingre.add(ing7);
        Recipe r1 = new Recipe("Smørebrød med kylling", ingre, 10, "Tilføj smør på brødet og derfor lægger du kyllingeskiver og agurk på + evt smør smør på brødet inden");
        rec.add(r1);
        Storage st4 = new Storage(10000);
        Storage st5 = new Storage(10000);
        Storage st6 = new Storage(10000);
        
        r1.addIngredient(ing5);
        r1.addIngredient(ing6);
        r1.addIngredient(ing7);
        ing5.setItem(item5);
        ing6.setItem(item6);
        ing7.setItem(item7);
        item5.setStorage(st4);
        item6.setStorage(st5);
        item7.setStorage(st6);
        
        // Recipe 4
        Item item8 = new Item("Æg", 500);
        Item item9 = new Item("Mel", 100);
        Item item10 = new Item("Mælk", 200);
        List<Item> items2 = new ArrayList();
        items2.add(item8);
        items2.add(item9);
        items2.add(item10);
        
        Ingredient ing8 = new Ingredient(item8, item8.getPrice_kg());
        Ingredient ing9 = new Ingredient(item9, item9.getPrice_kg());
        Ingredient ing10 = new Ingredient(item10, item10.getPrice_kg());
        List<Ingredient> ingre1 = new ArrayList();
        ingre1.add(ing8);
        ingre1.add(ing9);
        ingre1.add(ing10);
        Recipe r2 = new Recipe("Pandekager", ingre1, 30, "Pisk blandingen sammen og varm en pande op - tilføj smør på panden og hæld 1 dl ud når smøret er smeltet");
        rec.add(r2);
        Storage st7 = new Storage(10000);
        Storage st8 = new Storage(10000);
        Storage st9 = new Storage(10000);
        
        r2.addIngredient(ing8);
        r2.addIngredient(ing9);
        r2.addIngredient(ing10);
        ing8.setItem(item8);
        ing9.setItem(item9);
        ing10.setItem(item10);
        item8.setStorage(st7);
        item9.setStorage(st8);
        item10.setStorage(st9);
        
        
        WeekMenuPlan wmp = new WeekMenuPlan(50, 2020, rec);
        r.addWeekMenu(wmp);
        r1.addWeekMenu(wmp);
        r2.addWeekMenu(wmp);
        rec1.addWeekMenu(wmp);

        try {
            em.getTransaction().begin();
            em.persist(r);
            em.persist(r1);
            em.persist(r2);
            em.persist(rec1);
            em.persist(item);
            em.persist(wmp);

            em.getTransaction().commit();

        } finally {
            em.close();
        }

        RestaurantFacade facade = RestaurantFacade.getRestaurantFacade(emf);
        System.out.println(facade.getAllRecipes());
        System.out.println(facade.getWeekMenuPlans());
        System.out.println(facade.getCountOfRecipes());
        //facade.deleteRecipe(2);
        //System.out.println(facade.getCountOfRecipes());

        
        System.out.println(facade.getRecipeById(r.getRecipe_id()));
        System.out.println(facade.getRecipeByName("Spaghetti Kødsovs "));
    }
}

/**
 * Testing of addRecipe in facade
 * Item it1 = new Item("Gulerødder", 30);
        Item it2 = new Item("Æble", 20);
        List<Item> it = new ArrayList();
        it.add(it1);
        it.add(it2);

        Ingredient ingree = new Ingredient(it1, it1.getPrice_kg());
        Ingredient ingree1 = new Ingredient(it2, it2.getPrice_kg());
        List<Ingredient> in = new ArrayList();
        in.add(ingree);
        in.add(ingree1);

        Recipe rec1 = new Recipe("Råkost " ,in, 30, "Skræl gulerødder og æbler og riv dem på rive jern");
        List<Recipe> rec = new ArrayList();
        rec.add(rec1);

        WeekMenuPlan week = new WeekMenuPlan(51, 2020, rec);

        Storage stor1 = new Storage(15000);
        Storage stor2 = new Storage(15000);
        
        facade.addRecipe(rec1, week, ingree1, it1, stor1);
 */