package com.spellbound.objects;

import com.spellbound.states.Game;
import com.spellbound.tiles.Map;
import com.spellbound.utils.Colors;
import com.spellbound.utils.SpriteHandler;

public class Passive extends GameObject {

	public Passive(float x, float y, int width, int height, int hp) {
		super(x, y, width, height, 2, Colors.green, Game.TILE_SIZE + 5, 0, 0, hp);
		// TEMP
		currAnim = SpriteHandler.serf1_idleD;
	}

	@Override
	public void tick(Map m) {
		move();
		
		currAnim.run();
		
		tileCollide(m);
	}
	
}
