package com.spellbound.objects;

import com.spellbound.states.Game;
import com.spellbound.utils.SpriteHandler;

public class EnemyTest extends Hostile {

	public EnemyTest(float x, float y, int width, int height) {
		super(x, y, width, height, 50, 1, Game.TILE_SIZE + 10, Game.TILE_SIZE + 5, 5, 50);
		// TEMP
		currAnim = SpriteHandler.player_idleU;
	}

}
