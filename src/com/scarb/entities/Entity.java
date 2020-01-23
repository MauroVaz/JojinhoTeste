package com.scarb.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.scarb.main.Game;
import com.scarb.world.Camera;


public class Entity {
	protected int x,y,height,width;
	private BufferedImage sprite;
	public static final BufferedImage LIFE = Game.spritesheet.getSprite(6*16, 0, 16, 16);
	public static final BufferedImage GUN = Game.spritesheet.getSprite(7*16, 0, 16, 16);
	public static final BufferedImage GUN_BULLET = Game.spritesheet.getSprite(6*16, 16, 16, 16);
	public static final BufferedImage ENEMY = Game.spritesheet.getSprite(7*16, 16, 16, 16);
	public Entity(int x, int y, int width, int height, BufferedImage sprite){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite,this.getX() - Camera.x, this.getY() - Camera.y,null);
	}
}
