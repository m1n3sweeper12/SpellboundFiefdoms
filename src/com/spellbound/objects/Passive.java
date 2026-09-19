package com.spellbound.objects;

import com.spellbound.states.Game;
import com.spellbound.tiles.Map;
import com.spellbound.utils.Colors;

public class Passive extends GameObject {

	public Passive(float x, float y, int width, int height, int hp) {
		super(x, y, width, height, 2, Colors.green, Game.TILE_SIZE + 5, 0, 0, hp);
	}

	@Override
	public void tick(Map m) {
		move();
		
		tileCollide(m);
	}
	
}
