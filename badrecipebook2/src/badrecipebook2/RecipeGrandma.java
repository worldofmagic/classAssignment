package badrecipebook2;

class RecipeGrandma extends Recipe
{
	private String grandma_name;

	public RecipeGrandma(String name, RecipeCategory cat, String g_name)
	{
		super(name, cat);
		grandma_name = g_name;
	}

	// Polymorphism is applied for getting the Recipe source
	public String getSource()
	{
		return grandma_name;
	}
}
