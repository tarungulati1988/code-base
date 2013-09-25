
package com.breakout;

import java.util.Random;

public class Brick extends Sprite{
	
	public int height;
	public int width;
	public int newx;
	public int newy;
	
	public void set(int bheight, int bwidth)
	{
		x=bwidth/3;
		y=bheight/4;
	//	System.out.print(+bheight);
		height = 5*(bheight/100);
		width = 10*bwidth/100;
	}
	public Brick(int bheight, int bwidth)
	{
		//Setting the initial coordinates of the brick
		//int arrX[] = {400, 300, 150, 200, 500, 600, 700};
		
		// chooses a x co-ordinate randomly
		//Random random = new Random();
		
	//	x = arrX[random.nextInt(arrX.length)];
		x=bwidth/3;
		y=bheight/4;
		
		//Setting the height and width of the brick
		height = 5*(bheight/100);
		width = 10*bwidth/100;
	}
	public int getheight(){
		return height;
	}
	
	public int getwidth(){
		return width;
	}

}
