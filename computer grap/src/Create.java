import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Create extends Frame  
{
	/*
	 * Construct a GfxDemo2 given its title, width and height. Uses a
	 * CreateBagLayout to make the Canvas resize properly.
	 */
	Create(String title, int w, int h, int rows, int cols) 
	{
		setTitle(title);
		setResizable(false);

		// Now create a Canvas and add it to the Frame.
		Grid xyz = new Grid(w, h, rows, cols);
		add(xyz);

		addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e) 
			{
				setVisible(false);
				dispose();
				System.exit(0);
			}
		});

		// Normal end ... pack it up!
		pack();
	}

	public static void main(String[] a) 
	{
		new Create("Graphics Assignment 1", 800, 600, 30, 40).setVisible(true);
	}

}
