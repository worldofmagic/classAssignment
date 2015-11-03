package goodrecipebook2;

public class RecipeGrandma extends Recipe {
	private String grandma_name;

	/*
	 * Protected Variations Pattern
	 */
	public RecipeGrandma(String name, RecipeCategory cat, String g_name) {
		super(name, cat);
		grandma_name = g_name;
	}

	// SUPPORT POLYMORPHISM
	public String getSource() {
		return grandma_name;
	}
}