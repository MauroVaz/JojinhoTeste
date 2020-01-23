package com.scarb.entities;

import java.awt.image.BufferedImage;

import com.scarb.main.Game;
import com.scarb.world.World;

public class Enemy extends Entity{
	private int speed = 1;
	
	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}
	
	public void tick() {
		
		if(x < Game.player.getX()&& World.isFree((x+speed),y)) {
			x+=speed;
		}else if(x > Game.player.getX()&& World.isFree((x-speed),y)) {
			x-=speed;
		}
		if(y < Game.player.getY()&& World.isFree(x,(y+speed))) {
			y+=speed;
		}else if(y > Game.player.getY()&& World.isFree(x,(y-speed))) {
			y-=speed;
		}
		
	}
}
