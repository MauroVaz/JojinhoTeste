package com.scarb.world;

public class Camera {
	
	public static int y;
	public static int x;
	
	public static int clamp(int xAtual, int xMin, int xMax) {
		if(xAtual < xMin) {
			xAtual = xMin;
		}
		if(xAtual > xMax) {
			xAtual = xMax;
		}
		return xAtual;
	}
}
