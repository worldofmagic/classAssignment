package goodrecipebook;

import java.util.ArrayList;

/*
 * This class uses creator pattern, controller and high cohesion pattern
 * 
 */
public class RecipeBook
{
	private ArrayList<RecipeCategory> recipecategories = new ArrayList<RecipeCategory>();
	private ArrayList<Recipe> recipes = new ArrayList<Recipe>();

	public RecipeBook()
	{
		CreateTempCategories();
		CreateTempRecipes();
	}

	private void CreateTempCategories()
	{
		recipecategories.add(new RecipeCategory("dessert"));
		recipecategories.add(new RecipeCategory("appetizer"));
	}

	private void CreateTempRecipes()
	{
		String rname1 = "Puff Puff";
		String cat1 = "dessert";
		createRecipe(rname1, cat1);
		addIngredient(rname1, 3, UnitOfMeasure.cup, "white flour");
		addIngredient(rname1, 1, UnitOfMeasure.tsp, "salt");
		addIngredient(rname1, 1, UnitOfMeasure.cup, "water");
		addIngredient(rname1, 8, UnitOfMeasure.grms, "yeast");
		addInstruction(rname1, "Combine warm water with salt in bowl");
		addInstruction(rname1, "Add yeast and mix well");
		String rname2 = "Plantain chips";
		String cat2 = "appetizer";
		createRecipe(rname2, cat2);
		addIngredient(rname2, 1.5, UnitOfMeasure.cup, "yogurt");
		addIngredient(rname2, 3, null, "plantains");
		addIngredient(rname2, 1, UnitOfMeasure.cup, "vegetable oil");
		addIngredient(rname2, 0.25, UnitOfMeasure.tsp, "salt");
		addInstruction(rname2, "Heat oil in frying pan");
		addInstruction(rname2, "Cut plantain into thin slices");
		addInstruction(rname2, "Mix in salt");
		addInstruction(rname2, "Fry plantain until crispy brown");
	}

	// categories
	public void addRecipeCategory(String categoryname)
	{
		recipecategories.add(new RecipeCategory(categoryname));
	}

	private RecipeCategory getRecipeCategory(String name)
	{
		for (RecipeCategory rcat : recipecategories)
		{
			if (rcat.getCategoryName().equals(name))
				return rcat;
		}
		return null;
	}

	// recipes
	public String[] getAllRecipes()
	{
		String[] recipenames = new String[recipes.size()];
		int idx = 0;
		for (Recipe r : recipes)
		{
			recipenames[idx] = r.getRecipeName();
			idx++;
		}
		return recipenames;
	}

	private Recipe getRecipe(String name)
	{
		for (Recipe r : recipes)
		{
			if (r.getRecipeName().equals(name))
				return r;
		}
		return null;
	}

	public void createRecipe(String recipename, String categoryname)
	{
		RecipeCategory category = getRecipeCategory(categoryname);
		recipes.add(new Recipe(recipename, category));
	}

	public void addIngredient(String recipename, double unit, UnitOfMeasure uom,
			String ingredient)
	{
		Recipe recipe = getRecipe(recipename);
		if (recipe != null)
			// fix – RecipeBook class delegates creating
			// ingredients – No longer coupled with Ingredient
			// object
			recipe.addIngredient(unit, uom, ingredient);
	}

	public void addInstruction(String recipename, String instruction)
	{
		Recipe recipe = getRecipe(recipename);
		if (recipe != null)
			// fix – RecipeBook class delegates creating
			// instructions – No longer coupled with
			// Instruction object
			recipe.addInstruction(instruction);
	}

	public String displayRecipe(String recipename)
	{
		// fix – RecipeBook class delegates printing recipe
		Recipe recipe = getRecipe(recipename);
		return (recipe != null) ? recipe.toString() : "";
	}

	// fix – RecipeBook class acts as controller or handler of
	// system event (item selected for display)
	public String cmbselectedRecipe(String recipename)
	{
		return displayRecipe(recipename);
	}
}