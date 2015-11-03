package badrecipebook2;


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
		rlabel = new JLabel( "Recipes:" );
		add(rlabel);
		recipenames = new JComboBox(recipebook.getAllRecipes());
		recipenames.setSelectedIndex(-1);
		recipenames.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				JComboBox cb = (JComboBox)e.getSource();
				String recipename = (String)cb.getSelectedItem();
				// display the recipe
				displayArea.setText(recipebook.cmbselectedRecipe(recipename));
			}
		});
		add(recipenames);
		displayArea = new JTextArea(20,30);
		add(displayArea);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
/**
* @param args
*/
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		RecipeBookFrame fr = new RecipeBookFrame();
		fr.setSize(400, 500);
		fr.setVisible(true);
	}
}