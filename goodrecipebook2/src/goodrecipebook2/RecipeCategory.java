package goodrecipebook2;

/*
 * Protected Variations Pattern
 */
public class RecipeCategory {
	private String categoryname;

	public RecipeCategory(String name) {
		categoryname = name;
	}

	public String getCategoryName() {
		return categoryname;
	}
}