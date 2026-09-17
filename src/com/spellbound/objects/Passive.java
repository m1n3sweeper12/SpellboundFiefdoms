package com.spellbound.objects;

import com.spellbound.tiles.Map;
import com.spellbound.utils.Colors;

public class Passive extends GameObject {

	public Passive(float x, float y, int width, int height) {
		super(x, y, width, height, 2, Colors.green);
	}

	@Override
	public void tick(Map m) {
		move();
		
		tileCollide(m);
	}
	
}
