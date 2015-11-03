package badrecipebook2;

class RecipeOnline extends Recipe
{
	private String url;

	public RecipeOnline(String name, RecipeCategory cat, String link)
	{
		super(name, cat);
		url = link;
	}

	// Polymorphism is applied for getting the Recipe source
	public String getSource()
	{
		return url;
	}
}
