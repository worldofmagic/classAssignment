package badrecipebook2;

import java.io.Serializable;

class RecipeCategory implements Serializable
{
	private String categoryname;
	public RecipeCategory(String name)
	{
		categoryname = name;
	}
	public String getCategoryName()
	{
		return categoryname;
	}
}