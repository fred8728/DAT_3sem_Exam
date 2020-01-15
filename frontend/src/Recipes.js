import React, { useState, useEffect } from "react";
import apiFacade from "./apiFacade";
import Search from "./Search";

// Spørgsmål: <td>{data.weekMenu.menu_id}</td>

function GetRecipes() {
  const [recipe, setRecipe] = useState([]);
  const [search, setSearch] = useState("");

  const handleChange = e => {
    setSearch(e.target.value);
  };

  useEffect(() => {
    apiFacade.getRecipes().then(data => {
      setRecipe(data)});
  }, []);

  return (
    <div>
      <h1>Recipes</h1>
      <table class="customers">
      <Search value={search} onChange={handleChange} />
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Time</th>
            <th>Directions</th>
          </tr>
        </thead>
        <tbody>
          {recipe.filter(recipe => {
              return(recipe.name.toLowerCase().includes(search.toLowerCase()) 
              || recipe.directions.includes(search))
          })
            .map(data => (
              <tr key={data.recipe_id}>
                  <td>{data.recipe_id}</td>
                  <td>{data.name}</td>
                <td>{data.preparation_time}</td>
                <td>{data.directions}</td>
              </tr>
            ))}
        </tbody>
      </table>
    </div>
  );
}

export default GetRecipes;