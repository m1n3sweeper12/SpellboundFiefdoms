package com.spellbound.objects;

import com.spellbound.states.Game;
import com.spellbound.utils.ImageLoader;

public class WeaponTest extends Weapon {
	
	public WeaponTest(GameObject o) {
		super("Weapon Test", 10, 8, o, Game.TILE_SIZE, Game.TILE_SIZE, ImageLoader.loadImage("res/sprites/items/sword-temp.png"));
	}
	
}
