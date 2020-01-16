/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dto.RecipeDTO;
import entities.Ingredient;
import entities.Item;
import entities.Recipe;
import entities.Storage;
import entities.WeekMenuPlan;
import errorhandling.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author fskn
 */
public class RestaurantFacade {

    private static RestaurantFacade instance;
    private static EntityManagerFactory emf;

    //private constructor to ensure Singleton
    public RestaurantFacade() {
    }

    //return instance of facade class
    public static RestaurantFacade getRestaurantFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new RestaurantFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Recipe> getAllRecipes() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query = em.createQuery("Select r from Recipe r", Recipe.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<WeekMenuPlan> getWeekMenuPlans() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery query = em.createQuery("Select wmp from WeekMenuPlan wmp", Recipe.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Recipe addRecipe(Recipe recipe, WeekMenuPlan weekMenu, Ingredient ingredient, Item item, Storage storage) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            recipe.addWeekMenu(weekMenu);
            recipe.addIngredient(ingredient);
            ingredient.setItem(item);
            item.setStorage(storage);

            if (recipe.getIngredients() == null || recipe.getDirections() == null
                    || recipe.getWeekMenu() == null || recipe.getPreparation_time() == 0) {
                throw new Exception("You must fill all the info");
            }
            em.getTransaction().begin();
            em.persist(recipe);
            em.persist(ingredient);
            em.persist(item);
            em.persist(storage);
            em.getTransaction().commit();
            return recipe;
        } finally {
            em.close();
        }
    }

    public Recipe deleteRecipe(int id) throws NotFoundException {
        EntityManager em = emf.createEntityManager();
        try {
            Recipe recipe = em.find(Recipe.class, id);
            em.getTransaction().begin();
            em.remove(recipe);
            em.getTransaction().commit();
            return recipe;
        } catch (Exception e) {
            e.printStackTrace();
            throw new NotFoundException("The given id doesnt exist - no recipe deleted");
        } finally {
            em.close();
        }
    }

    public int getCountOfRecipes() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("Select COUNT(r) from Recipe r");
            return Integer.parseInt(query.getSingleResult().toString());
        } finally {
            em.close();
        }
    }

    public Recipe getRecipeById(int id) {
        EntityManager em = getEntityManager();
        try {
            Recipe recipe = em.find(Recipe.class, id);
            return recipe;

        } finally {
            em.close();
        }
    }

    public Recipe getRecipeByName(String name) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery query
                    = em.createQuery("SELECT r from Recipe r WHERE r.name=:name", Recipe.class);
            return (Recipe) query.setParameter("name", name).getSingleResult();

        } finally {
            em.close();
        }
    }

    public void dataToDatabase() {
        EntityManager em = emf.createEntityManager();
        List<Recipe> rec = new ArrayList();
        List<Recipe> rec2 = new ArrayList();
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

        Recipe rec1 = new Recipe("Råkost ", in, 30, "Skræl gulerødder og æbler og riv dem på rive jern");
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

        //Recipe 5
        Item item11 = new Item("Salat", 30);
        Item item12 = new Item("Kylling", 350);
        Item item13 = new Item("Brød kutoner", 60);
        Item item14 = new Item("Parmazan", 500);
        List<Item> items3 = new ArrayList();
        items3.add(item11);
        items3.add(item12);
        items3.add(item13);
        items3.add(item14);
        Ingredient ing11 = new Ingredient(item11, item11.getPrice_kg());
        Ingredient ing12 = new Ingredient(item12, item12.getPrice_kg());
        Ingredient ing13 = new Ingredient(item13, item13.getPrice_kg());
        Ingredient ing14 = new Ingredient(item14, item14.getPrice_kg());
        List<Ingredient> ingre2 = new ArrayList();
        ingre2.add(ing11);
        ingre2.add(ing12);
        ingre2.add(ing13);
        ingre2.add(ing14);
        Recipe r3 = new Recipe("Cæcar Salat", ingre2, 20, "Bland det hele sammen og drys parmazan på toppen");
        rec.add(r3);

        Storage st11 = new Storage(10000);
        Storage st12 = new Storage(10000);
        Storage st13 = new Storage(10000);
        Storage st14 = new Storage(10000);

        r3.addIngredient(ing11);
        r3.addIngredient(ing12);
        r3.addIngredient(ing13);
        r3.addIngredient(ing14);
        ing11.setItem(item11);
        ing12.setItem(item12);
        ing13.setItem(item13);
        ing14.setItem(item14);
        item11.setStorage(st11);
        item12.setStorage(st12);
        item13.setStorage(st13);
        item14.setStorage(st14);

        //Recipe 6
        Item item15 = new Item("Oksekød", 400);
        Item item16 = new Item("Pandekager", 350);
        Item item17 = new Item("Dressing", 300);
        List<Item> items4 = new ArrayList();
        items4.add(item15);
        items4.add(item16);
        items4.add(item17);
        Ingredient ing15 = new Ingredient(item15, item15.getPrice_kg());
        Ingredient ing16 = new Ingredient(item16, item16.getPrice_kg());
        Ingredient ing17 = new Ingredient(item17, item17.getPrice_kg());
        List<Ingredient> ingre3 = new ArrayList();
        ingre3.add(ing15);
        ingre3.add(ing16);
        ingre3.add(ing17);
        Recipe r4 = new Recipe("Burrito", ingre3, 55, "Fyld pandekagen med kød og luk den - dernæst salat og dressing ved siden af");
        rec.add(r4);

        Storage st15 = new Storage(10000);
        Storage st16 = new Storage(10000);
        Storage st17 = new Storage(10000);

        r.addIngredient(ing15);
        r.addIngredient(ing16);
        r.addIngredient(ing17);
        ing15.setItem(item15);
        ing16.setItem(item16);
        ing17.setItem(item17);
        item15.setStorage(st15);
        item16.setStorage(st16);
        item17.setStorage(st17);

        //Recipe 7
        Item item19 = new Item("Kagecreme", 400);
        Item item20 = new Item("Tærtebund", 350);
        Item item21 = new Item("Marcipan", 800);
        Item item22 = new Item("Jordbær", 500);
        List<Item> items5 = new ArrayList();
        items5.add(item19);
        items5.add(item20);
        items5.add(item21);
        items5.add(item22);
        Ingredient ing19 = new Ingredient(item19, item19.getPrice_kg());
        Ingredient ing20 = new Ingredient(item20, item20.getPrice_kg());
        Ingredient ing21 = new Ingredient(item21, item21.getPrice_kg());
        Ingredient ing22 = new Ingredient(item22, item22.getPrice_kg());
        List<Ingredient> ingre4 = new ArrayList();
        ingre4.add(ing19);
        ingre4.add(ing20);
        ingre4.add(ing21);
        ingre4.add(ing22);
        Recipe r5 = new Recipe("Jordbær tærte", ingre4, 90, "Start med at lave tærte bunden, derefter ligger du kagecreme ovenpå og jordbær");
        rec.add(r5);

        Storage st19 = new Storage(10000);
        Storage st20 = new Storage(10000);
        Storage st21 = new Storage(10000);
        Storage st22 = new Storage(10000);

        r5.addIngredient(ing19);
        r5.addIngredient(ing20);
        r5.addIngredient(ing21);
        r5.addIngredient(ing22);
        ing19.setItem(item19);
        ing20.setItem(item20);
        ing21.setItem(item21);
        ing22.setItem(item22);
        item19.setStorage(st19);
        item20.setStorage(st20);
        item21.setStorage(st21);
        item22.setStorage(st22);

        //Recipe 8
        Item item23 = new Item("Pita", 600);
        Item item24 = new Item("Shawarma", 800);
        Item item25 = new Item("Dressing", 250);
        Item item26 = new Item("Salat", 50);
        List<Item> items6 = new ArrayList();
        items6.add(item23);
        items6.add(item24);
        items6.add(item25);
        items6.add(item26);
        Ingredient ing23 = new Ingredient(item23, item23.getPrice_kg());
        Ingredient ing24 = new Ingredient(item24, item24.getPrice_kg());
        Ingredient ing25 = new Ingredient(item25, item25.getPrice_kg());
        Ingredient ing26 = new Ingredient(item26, item26.getPrice_kg());
        List<Ingredient> ingre5 = new ArrayList();
        ingre5.add(ing23);
        ingre5.add(ing24);
        ingre5.add(ing25);
        ingre5.add(ing26);
        Recipe r6 = new Recipe("Pita shawarma", ingre5, 30, "Fyld pita op med salat og tilføj lidt dressing. Derefter tilsæt kød og dressing på toppen");
        rec2.add(r6);

        Storage st23 = new Storage(10000);
        Storage st24 = new Storage(10000);
        Storage st25 = new Storage(10000);
        Storage st26 = new Storage(10000);

        r6.addIngredient(ing23);
        r6.addIngredient(ing24);
        r6.addIngredient(ing25);
        r6.addIngredient(ing26);
        ing23.setItem(item13);
        ing24.setItem(item24);
        ing25.setItem(item25);
        ing26.setItem(item26);
        item23.setStorage(st23);
        item24.setStorage(st24);
        item25.setStorage(st25);
        item26.setStorage(st26);

        //Recipe 9
        Item item27 = new Item("Kaffe", 900);
        Item item28 = new Item("Mælk", 200);
        List<Item> items7 = new ArrayList();
        items7.add(item27);
        items7.add(item28);
        Ingredient ing27 = new Ingredient(item27, item23.getPrice_kg());
        Ingredient ing28 = new Ingredient(item28, item24.getPrice_kg());
        List<Ingredient> ingre6 = new ArrayList();
        ingre6.add(ing23);
        ingre6.add(ing24);
        Recipe r7 = new Recipe("Caffe Latte", ingre6, 5, "Kog vand til nescafe og tilsæt pisket mælk");
        rec2.add(r7);

        Storage st27 = new Storage(10000);
        Storage st28 = new Storage(10000);

        r7.addIngredient(ing27);
        r7.addIngredient(ing28);
        ing27.setItem(item27);
        ing28.setItem(item28);
        item27.setStorage(st27);
        item28.setStorage(st28);

        //Recipe 10
        Item item29 = new Item("Brød", 100);
        Item item30 = new Item("Pølse", 200);
        List<Item> items8 = new ArrayList();
        items8.add(item27);
        items8.add(item28);
        Ingredient ing29 = new Ingredient(item29, item29.getPrice_kg());
        Ingredient ing30 = new Ingredient(item30, item30.getPrice_kg());
        List<Ingredient> ingre7 = new ArrayList();
        ingre7.add(ing23);
        ingre7.add(ing24);
        Recipe r8 = new Recipe("Hotdog", ingre7, 20, "Put pølse ned i brødet - evt tilføj kethup");
        rec2.add(r8);

        Storage st29 = new Storage(10000);
        Storage st30 = new Storage(10000);

        r8.addIngredient(ing29);
        r8.addIngredient(ing30);
        ing29.setItem(item29);
        ing30.setItem(item30);
        item29.setStorage(st29);
        item30.setStorage(st30);

        WeekMenuPlan wmp = new WeekMenuPlan(50, 2020, rec);
        WeekMenuPlan wmp1 = new WeekMenuPlan(51, 2020, rec);
        r.addWeekMenu(wmp);
        r1.addWeekMenu(wmp);
        r2.addWeekMenu(wmp);
        rec1.addWeekMenu(wmp);
        r3.addWeekMenu(wmp);
        r4.addWeekMenu(wmp);
        r5.addWeekMenu(wmp);
        r6.addWeekMenu(wmp1);
        r7.addWeekMenu(wmp1);
        r8.addWeekMenu(wmp1);

        try {
            em.getTransaction().begin();
            em.persist(r);
            em.persist(r1);
            em.persist(r2);
            em.persist(r3);
            em.persist(r4);
            em.persist(r5);
            em.persist(r6);
            em.persist(r7);
            em.persist(r8);
            em.persist(rec1);
            em.persist(item);
            em.persist(wmp);
            em.persist(wmp1);

            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

}
