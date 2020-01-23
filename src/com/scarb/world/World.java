package com.scarb.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.scarb.entities.Entity;
import com.scarb.entities.Gun;
import com.scarb.entities.Life;
import com.scarb.entities.Bullet;
import com.scarb.entities.Enemy;
import com.scarb.main.Game;

public class World {
	
	public static Tile[] tiles;
	public static int WIDTH, HEIGHT;
	public static final int TILE_SIZE = 16;
	
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[map.getWidth() * map.getHeight()];
			map.getRGB(0,0,map.getWidth() , map.getHeight(), pixels,0,map.getWidth());
			for(int xx = 0; xx < map.getWidth(); xx++) {
				for(int yy = 0; yy < map.getHeight(); yy++) {
				int pixelAtual = pixels[xx + (yy*map.getWidth())];
					
					if( pixelAtual == 0xFF000000){
						//CHAO
						tiles[xx + (yy*WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR);
					}else if(pixelAtual == 0xFFFFFFFF){
						//pareide
						tiles[xx + (yy*WIDTH)] = new WallTile(xx * 16, yy * 16, Tile.TILE_WALL);
					}else if(pixelAtual == 0xFF0400FF){
						//preyer
						Game.player.setX(xx *16);
						Game.player.setY(yy *16);
						tiles[xx + (yy*WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR);
					}else if(pixelAtual == 0xFFFF0C00){
						//ENEMY
						Game.entities.add(new Enemy(xx * 16, yy * 16,16,16,Entity.ENEMY));
						tiles[xx + (yy*WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR);
					}else if(pixelAtual == 0xFFFF2873){
						//LIFE
						Game.entities.add(new Life(xx * 16, yy * 16,16,16,Entity.LIFE));
						tiles[xx + (yy*WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR);
					}else if(pixelAtual == 0xFFFFAA00){
						//GUN
						Game.entities.add(new Gun(xx * 16, yy * 16,16,16,Entity.GUN));
						tiles[xx + (yy*WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR);
					}else if(pixelAtual == 0xFF00FFDD){
						//BULLET
						Game.entities.add(new Bullet(xx * 16, yy * 16,16,16,Entity.GUN_BULLET));
						tiles[xx + (yy*WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR);
					}else {
						tiles[xx + (yy*WIDTH)] = new FloorTile(xx * 16, yy * 16, Tile.TILE_FLOOR);
					}
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isFree(int xNext, int yNext) {
		int x1 = xNext / TILE_SIZE;
		int y1 = yNext / TILE_SIZE;
		
		int x2 = (xNext+TILE_SIZE-1) / TILE_SIZE;
		int y2 = yNext / TILE_SIZE;
		
		int x3 = xNext / TILE_SIZE;
		int y3 = (yNext+TILE_SIZE-1) / TILE_SIZE;
		
		int x4 = (xNext+TILE_SIZE-1) / TILE_SIZE;
		int y4 = (yNext+TILE_SIZE-1) / TILE_SIZE;
		
		return !((tiles[x1 + (y1*World.WIDTH)] instanceof WallTile) || 
				(tiles[x2 + (y2*World.WIDTH)] instanceof WallTile) ||
				(tiles[x3 + (y3*World.WIDTH)] instanceof WallTile) ||
				(tiles[x4 + (y4*World.WIDTH)] instanceof WallTile));

	}
	
	public void render(Graphics g) {
		int xstart = Camera.x>> 4;
		int ystart = Camera.y>> 4;
		
		int xfinal = xstart + (Game.WIDTH >> 4);
		int yfinal = ystart + (Game.HEIGHT >> 4);
		
		
		for(int xx = xstart; xx <= xfinal; xx++) {
			for(int yy = ystart; yy <= yfinal; yy++) {
				if(xx < 0 || yy < 0 || xx >=WIDTH || yy >= HEIGHT) {
					continue;
				}
				Tile tile = tiles[xx + (yy * WIDTH)];
				tile.render(g);
			}
		}
	}
}
