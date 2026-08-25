package com.spellbound.utils;

import java.awt.Color;

public class Colors {
	
	public static Color red, orange, yellow, green, blue, purple, white, black;
	public static COLORTHEME theme;
	
	// TODO: finish adding color themes
	public static void initColors() {
		switch(theme) {
		case Default:
			red = new Color();
			orange = new Color();
			yellow = new Color();
			green = new Color();
			blue = new Color();
			purple = new Color();
			white = new Color();
			black = new Color();
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
