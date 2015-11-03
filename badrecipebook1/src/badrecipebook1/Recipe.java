package badrecipebook1;

import java.util.ArrayList;

public class Recipe
{
	private String recipename;
	private RecipeCategory category = null;
	private ArrayList<Ingredient> ingredients = null;
	private ArrayList<Instruction> instructions = null;

	public Recipe(String name, RecipeCategory cat)
	{
		recipename = name;
		category = cat;
	}

	public String getRecipeName()
	{
		return recipename;
	}

	public RecipeCategory getRecipeCategory()
	{
		return category;
	}

	// ingredients

	public void addIngredientObj(Ingredient ingrObj)
	{
		if (ingredients == null)
			ingredients = new ArrayList<Ingredient>();
		ingredients.add(ingrObj);
	}

	public Ingredient getIngredientObj(int idx)
	{
		return ingredients.get(idx);
	}

	public int numIngredients()
	{
		return ingredients.size();
	}

	// instructions

	public void addInstructionObj(Instruction instrObj)
	{
		if (instructions == null)
			instructions = new ArrayList<Instruction>();
		instructions.add(instrObj);
	}

	public Instruction getInstructionObj(int idx)
	{
		return instructions.get(idx);
	}

	public int numInstructions()
	{
		return instructions.size();
	}
}
