
package com.breakout;

import java.awt.Color;
import java.awt.Graphics;

public class Paddle extends Sprite implements Dimensions, Observer{
	
	public int newx;
	public int newy;
	public int width;
	public int height;
	public int paddleheight;
	public int paddlewidth;
	
	public Paddle(int bheight, int bwidth)
	{
		//Setting the initial coordinates
	//	System.out.print(+bheight);
		x = (bwidth-120)/2;
		y = bheight-170;
		newx = (bwidth-120)/2;
		newy=bheight-170;
		width=bwidth;
		height=bheight;
		paddleheight=83*bheight/10000;
		paddlewidth=13*bwidth/100;
	//	System.out.print(+bheight);
		
	}
	
	
	public void set(int bheight, int bwidth)
	{

		x = (bwidth-120)/2;
		y = bheight-170;
		newx = (bwidth-120)/2;
		newy=bheight-170;
		width=bwidth;
		height=bheight;
		paddleheight=83*bheight/10000;
		paddlewidth=13*bwidth/100;
	}
	
	public void resetState(){
		x = this.newx;
		y = this.newy;
		
	}
	Sprite spriteObj = new Sprite();
	
	int dx;
	/*
	 * 	keyPressed()
	 */
	public void KeyPressed(){
		
	}
	/*
	 * 	keyReleased()
	 */
	public void KeyReleased(){
		dx = 0;
	}
	/*
	 * 	move()
	 */
	public void move(String directionToMove){
		
		if(directionToMove.equals("left")){
			dx = -2;
		}else if (directionToMove.equals("right")){
			dx = 2;
		}
	}

	/*
	 * 	paint()
	 */
	public void paint(Graphics g){
		g.setColor(Color.GREEN);
		// fil;Rect(int x, int y, int width, int height)
		g.fillRect(250, 480, 80, 5);
	}
	
	// updates the x position of the paddle
	@Override
	public void update(){
		
		x += dx;
		if(x <= 2){
			x = 2;
		}else if(x >= ((this.width+this.paddlewidth)-350)){
			x = (this.width+this.paddlewidth)-350;
		}else if(x <= Dimensions.PADDLE_LEFT){
			x = Dimensions.PADDLE_LEFT;
		}
	}
	
	@Override
	public void draw(Board com){
		com.repaint();
	}
}
