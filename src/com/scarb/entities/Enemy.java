package com.scarb.entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.scarb.main.Game;
import com.scarb.world.World;

public class Enemy extends Entity{
	private int speed = 1;
	
	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}
	
	public void tick() {
		
		if(x < Game.player.getX()&& World.isFree((x+speed),y) && !isColiding((x+speed),y)) {
			x+=speed;
		}else if(x > Game.player.getX()&& World.isFree((x-speed),y) && !isColiding((x-speed),y)) {
			x-=speed;
		}
		if(y < Game.player.getY()&& World.isFree(x,(y+speed)) && !isColiding(x,(y+speed))) {
			y+=speed;
		}else if(y > Game.player.getY()&& World.isFree(x,(y-speed))&& !isColiding(x,(y-speed))) {
			y-=speed;
		}
		
	}
	
	public boolean isColiding(int xNext, int yNext) {
		Rectangle enemyCurrent = new Rectangle(xNext, yNext, World.TILE_SIZE, World.TILE_SIZE);
		
		for(int  i = 0; i < Game.enemies.size(); i++){
			Enemy e = Game.enemies.get(i);
			if(e == this) {
				continue;
			}
			Rectangle targetEnemy = new Rectangle(e.getX(), e.getY(), World.TILE_SIZE, World.TILE_SIZE);
			if(enemyCurrent.intersects(targetEnemy)){
				return true;
			}
		
		}
		return false;
	}
}
