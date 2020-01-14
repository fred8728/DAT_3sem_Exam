package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.RecipeDTO;
import dto.WeekMenuPlanDTO;
import entities.Ingredient;
import entities.Item;
import entities.Recipe;
import entities.Storage;
import entities.WeekMenuPlan;
import errorhandling.NotFoundException;
import utils.EMF_Creator;
import facades.RestaurantFacade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("rest")
public class RestaurantResource {

    private static EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private static final RestaurantFacade facade = RestaurantFacade.getRestaurantFacade(EMF);
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @GET
    @Path("recipes/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllRecipes() {
        List<RecipeDTO> recipeList = new ArrayList();
        List<Recipe> recipe = facade.getAllRecipes();
        RecipeDTO recipeDTO = new RecipeDTO();

        for (Recipe r : recipe) {
            recipeList.add(recipeDTO.getRecipes(r));
        }
        return gson.toJson(recipeList);
    }

    @GET
    @Path("week/all")
    @Produces(MediaType.APPLICATION_JSON)
    public String getWeekMenu() {
        List<WeekMenuPlanDTO> weekMenuList = new ArrayList();
        List<WeekMenuPlan> weekMenu = facade.getWeekMenuPlans();
        WeekMenuPlanDTO WeekMenuPlanDTO = new WeekMenuPlanDTO();

        for (WeekMenuPlan wmp : weekMenu) {
            weekMenuList.add(WeekMenuPlanDTO.getWeekMenus(wmp));
        }
        return gson.toJson(weekMenuList);
    }

    @GET
    @Path("recipes/count")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCountOfRecipes() {
        return gson.toJson(facade.getCountOfRecipes());
    }

    @GET
    @Path("recipes/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRecipeByName(@PathParam("name") String name) {
        return gson.toJson(facade.getRecipeByName(name));
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("recipes/{id}")
    public void deleteRecipe(@PathParam("id") int id) throws NotFoundException {
        Recipe recipe = facade.deleteRecipe(id);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("addData")
    public String addData(){
        facade.dataToDatabase();
        return "{\"msg\":\"Success\"}";
    }
    
    /**
     * @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("recipes/add")
    public String addRecipe(Recipe recipe, WeekMenuPlan weekMenu, Ingredient ingredient, Item item, Storage storage) throws Exception {
        return gson.toJson(facade.addRecipe(recipe, weekMenu, ingredient, item, storage));
    }
     */

}
