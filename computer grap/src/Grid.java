import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class Grid extends Canvas 
{
	 
	int width, height;

	int rows;
	int cols;
	
	int size = 20;

	public static int[] point0 = { 25, 15 };
	public static int[] point1 = { 9, 27 };
	public static int[] point2 = { 4, 12 };
	public static int[] point3 = { 25, 24 };
	public static int[] point4 = { 30, 12 };

	Grid(int w, int h, int r, int c) 
	{
		setSize(width = w, height = h);
		rows = r;
		cols = c;
	}

	public void paint(Graphics g) 
	{
		test(g);
		int i;
		width = getSize().width;
		height = getSize().height;

		// draw the rows
		int rowHt = height / (rows);
		for (i = 0; i < rows; i++)
			g.drawLine(0, i * rowHt, width, i * rowHt);

		// draw the columns
		int rowWid = width / (cols);
		for (i = 0; i < cols; i++)
			g.drawLine(i * rowWid, 0, i * rowWid, height);
	}
	
	public void WritePixel(double x, double y, Color color, Graphics g) 
	{
		g.setColor(color);
		g.fillRect((int)x*size-20, (int)y*size-20, size, size);
		g.setColor(Color.BLACK);
		g.fillOval((int)x*size-20, (int)y*size-20, 5, 5);
	}

	public void midpointAlgo(double x1, double y1, double x2, double y2,
			Color color, Graphics g) 
	{
		/*
		 * f(x,y) = ax + by + c, check the pixels E = (x+1,y+1) and NE = (x+1,
		 * y) to determine where to draw the next pixel for the line. If the
		 * midpoint M = (x+1, y+1/2) is above the line a = dy = (y2 - y1); b =
		 * -dx = -(x2 - x1);
		 */
		if (x1 > x2 && y1 > y2)
			{
				double temp1, temp2;
				temp1 = x1;
				temp2 = y1;
				x1 = x2;
				x2 = temp1;
				y1 = y2;
				y2 = temp2;
			}
		
		double dx = 0, dy = 0;
		double x = 0, y = 0, temp = 0;
		double d = 0, E = 0, NE = 0;
		
		double m = (y2 - y1)/(x2 - x1);
		
		System.out.println(m);
		
		if (m >= 0 && m <= 1)
			{
				x = x1;
				y = y1;
				temp = x2;
				dx = x2 - x1;
				dy = y2 - y1;
				
			}
		else if (m > 1)
			{
				x = y1;
				y = x1;
				temp = y2;
				dx = y2 - y1;
				dy = x2 - x1;
			}
		else if (m < 0 && m >= -1)
			{
				if (y2 > y1)
					{
						double temp1, temp2;
						temp1 = x1;
						temp2 = y1;
						x1 = x2;
						x2 = temp1;
						y1 = y2;
						y2 = temp2;
					}
				x = x1;
				y = y1;
				temp = x2;
				dx = x2 - x1;
				dy = -(y2 - y1);
			}
		else if (m < -1)
			{
				if (y2 > y1 && x1 > x2)
					{
						double temp1, temp2;
						temp1 = x1;
						temp2 = y1;
						x1 = x2;
						x2 = temp1;
						y1 = y2;
						y2 = temp2;
					}
				x = y2;
				y = x2;
				temp = y1;
				dx = -(y2 - y1);
				dy = x2 - x1;
			}
		
		d = 2 * (dy - dx);
		E = 2 * dy;
		NE = 2 * (dy - dx);
		
		while (x <= temp) 
			{
				if (m >= 0 && m <= 1)
					WritePixel(x, y+1, color, g);
				else if (m > 1 || m < -1)
					WritePixel(y+1, x, color, g);
				else
					WritePixel(x+1, y, color, g);
		
				if (d <= 0) 
					{
						x++;
						d += E;
					} 
				else 
					{
						x++;
						if (m >= 0)
							y++;
						else
							y--;
						d += NE;
					}
			}
		g.setColor(Color.BLACK);
		g.drawLine((int)x1*size, (int)y1*size, (int)x2*size, (int)y2*size);
	}
	
	public void test(Graphics g)
	{
		midpointAlgo(point0[0], point0[1], point1[0], point1[1], Color.RED, g);
		midpointAlgo(point0[0], point0[1], point2[0], point2[1], Color.GREEN, g);
		midpointAlgo(point0[0], point0[1], point3[0], point3[1], Color.black, g);
		midpointAlgo(point0[0], point0[1], point4[0], point4[1], Color.yellow, g);
	}

}
