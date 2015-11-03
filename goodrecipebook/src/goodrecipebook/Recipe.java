package goodrecipebook;

import java.util.ArrayList;

/*
 * 
 *information expert pattern
 */

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
	public void addIngredient(double u, UnitOfMeasure uom, String ingrd)
	{
		// fix – Recipe class creates Ingredients
		Ingredient ing = new Ingredient(u, uom, ingrd);
		if (ingredients == null)
			ingredients = new ArrayList<Ingredient>();
		ingredients.add(ing);
	}

	public int numIngredients()
	{
		return ingredients.size();
	}

	// instructions
	public void addInstruction(String instr)
	{
		// fix – Recipe class creates Instructions
		Instruction inst = new Instruction(instr);
		if (instructions == null)
			instructions = new ArrayList<Instruction>();
		instructions.add(inst);
	}

	public int numInstructions()
	{
		return instructions.size();
	}

	public String toString()
	{
		// fix – Recipe class prints a recipe’s details
		String s = "";
		s += s.format("%s\n", recipename);
		s += s.format("Category: %s\n", category.getCategoryName());
		s += s.format("\nINGREDIENTS\n");
		for (int i = 0; i < numIngredients(); i++)
		{
			Ingredient ing = ingredients.get(i);
			if (ing.getUOM() == null)
				s += s.format("%.2f %s\n", ing.getUnit(), ing.getIngredient());
			else
				s += s.format("%.2f %s %s\n", ing.getUnit(), ing.getUOM(),
						ing.getIngredient());
		}
		s += s.format("\nINSTRUCTIONS\n");
		for (int i = 0; i < numInstructions(); i++)
		{
			Instruction inst = instructions.get(i);
			s += s.format("%s\n", inst.getInstruction());
		}
		return s;
	}
}