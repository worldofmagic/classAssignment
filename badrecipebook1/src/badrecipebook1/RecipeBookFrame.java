package badrecipebook1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class RecipeBookFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	JLabel rlabel;
	JComboBox recipenames;
	JTextArea displayArea;
	private RecipeBook recipebook;

	public RecipeBookFrame()
	{
		super("RecipeBook Frame");
		setLayout(new FlowLayout());

		// contents of main frame
		recipebook = new RecipeBook();
		rlabel = new JLabel("Recipes:");
		add(rlabel);
		recipenames = new JComboBox(recipebook.getAllRecipes());
		recipenames.setSelectedIndex(-1);
		recipenames.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JComboBox cb = (JComboBox) e.getSource();
				String recipename = (String) cb.getSelectedItem();

				// display the recipe
				// violation � controller!!
				// violation � low coupling!!
				// violation � high cohesion!!
				ArrayList<Recipe> recipes = recipebook.getAllRecipeObjects();
				for (Recipe r : recipes)
				{
					if (r.getRecipeName().equals(recipename))
					{
						displayArea.setText(recipebook.printRecipe(r));
					}
				}
			}
		});
		add(recipenames);
		displayArea = new JTextArea(20, 30);
		add(displayArea);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args)
	{
		RecipeBookFrame fr = new RecipeBookFrame();
		fr.setSize(400, 500);
		fr.setVisible(true);
	}
}
