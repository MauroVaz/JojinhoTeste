package com.scarb.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.scarb.main.Game;
import com.scarb.world.Camera;
import com.scarb.world.World;

public class Player extends Entity {

	public boolean right, left, up, down;
	private int speed = 2;
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private BufferedImage[] standyPlayer;
	private BufferedImage[] upPlayer;
	private int frames = 0, maxFrames = 8, index = 0, maxIndex = 3;
	private boolean moved = false;

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);

		rightPlayer = new BufferedImage[4];
		leftPlayer = new BufferedImage[4];
		standyPlayer = new BufferedImage[4];
		upPlayer = new BufferedImage[4];
		standyPlayer[0] = Game.spritesheet.getSprite(32, 32, 15, 15);
		
		for (int i = 0; i < 4; i++) {
			rightPlayer[i] = Game.spritesheet.getSprite(32 + (i * 16), 0, 16, 16);
		}
		for (int i = 0; i < 4; i++) {
			leftPlayer[i] = Game.spritesheet.getSprite(32 + (i * 16), 16, 16, 16);
		}
		for (int i = 0; i < 4; i++) {
			upPlayer[i] = Game.spritesheet.getSprite(32 + (i * 16), 48, 15, 15);
		}
		for (int i = 0; i < 4; i++) {
			standyPlayer[i] = Game.spritesheet.getSprite(32 + (i * 16), 32, 15, 15);
		}

	}

	public void tick() {
		moved = false;
		if (right && World.isFree((x+speed),y)) {
			moved = true;
			x += speed;
		} else if (left && World.isFree((x-speed),y)) {
			moved = true;
			x -= speed;
		}
		if (up && World.isFree(x,(y-speed))) {
			moved = true;
			y -= speed;
		} else if (down && World.isFree(x,(y+speed))) {
			moved = true;
			y += speed;
		}

		if (moved ) {
			frames++;
			if (frames == maxFrames) {
				frames = 0;
				index++;
				if (index > maxIndex) {
					index = 0;
				}
			}
		}
		
		
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2), 0, World.WIDTH*16 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT/2), 0, World.HEIGHT*16 - Game.HEIGHT);
	}

	public void render(Graphics g) {
		if (right) {
			g.drawImage(rightPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		} else if (left) {
			g.drawImage(leftPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		} else if (up) {
			g.drawImage(upPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		} else if (down){
			g.drawImage(standyPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}else {
			g.drawImage(standyPlayer[0], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
	}

}
