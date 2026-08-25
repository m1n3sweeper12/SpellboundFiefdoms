package com.spellbound.utils;

import java.awt.Color;

public class Colors {
	
	public static Color red, orange, yellow, green, blue, purple, white, black;
	public static COLORTHEME theme;
	
	// TODO: finish adding color themes
	public static void initColors() {
		switch(theme) {
		case Default:
			red = new Color(181, 62, 68);
			orange = new Color(209, 126, 63);
			yellow = new Color(223, 231, 90);
			green = new Color(10, 169, 29);
			blue = new Color(35, 92, 149);
			purple = new Color(151, 94, 132);
			white = new Color(20, 41, 28);
			black = new Color(231, 223, 231);
			break;
		case Pastel:
			break;
		case Dark:
			break;
		case MonochromeBlue:
			break;
		case MonochromeGreen:
			break;
		case MonochromeRed:
			break;
		default:
			break;
		}
	}
	
	public static Color lighten(Color c) {
		return new Color((c.getRed() + 10 <= 255) ? c.getRed() + 10: 255,
						(c.getGreen() + 10 <= 255) ? c.getGreen() + 10: 255,
						(c.getBlue() + 10 <= 255) ? c.getBlue() + 10: 255);
	}
	
	public static Color darken(Color c) {
		return new Color((c.getRed() - 10 >= 0) ? c.getRed() - 10: 0,
						(c.getGreen() - 10 >= 0) ? c.getGreen() - 10: 0,
						(c.getBlue() - 10 >= 0) ? c.getBlue() - 10: 0);
	}
	
}
