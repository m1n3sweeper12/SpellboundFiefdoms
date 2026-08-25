package com.spellbound.states;

import java.awt.Graphics2D;

import com.spellbound.main.Main;

public abstract class State {
	
	@SuppressWarnings("unused")
	private Main main;
	
	public State(Main main) {
		this.main = main;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics2D g);
	
}
