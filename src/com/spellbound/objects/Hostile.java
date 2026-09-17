package com.spellbound.objects;

import com.spellbound.tiles.Map;
import com.spellbound.utils.Colors;

public class Hostile extends GameObject {

	public Hostile(float x, float y, int width, int height) {
		super(x, y, width, height, 1, Colors.red);
	}

	@Override
	public void tick(Map m) {
		this.move();
		
		this.tileCollide(m);
	}

}
