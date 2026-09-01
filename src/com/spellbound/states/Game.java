package com.spellbound.states;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.spellbound.main.Main;
import com.spellbound.objects.GameObject;
import com.spellbound.objects.Hostile;
import com.spellbound.objects.Passive;
import com.spellbound.objects.Player;
import com.spellbound.utils.Colors;

public class Game extends State {
	
	private ArrayList<GameObject> objects;
	
	public static final int TILE_SIZE = 32;
	
	private Player player;
	
	// TEMP, for testing Hostile & Passive classes
	private Hostile enemy;
	private Passive npc;
	
	
	public Game(Main main) {
		super(main);
		objects = new ArrayList<GameObject>();
		// TEMP, will be handled using implemented file system
		player = new Player(64, 64, Game.TILE_SIZE, Game.TILE_SIZE);
		objects.add(player);
		
		enemy = new Hostile(256, 256, Game.TILE_SIZE, Game.TILE_SIZE);
		objects.add(enemy);
		
		npc = new Passive(512, 512, Game.TILE_SIZE, Game.TILE_SIZE);
		objects.add(npc);
		
		// TEMP
		System.out.println("Color Key:\nblue -> player\nred -> hostile\ngreen -> passive");
	}

	@Override
	public void tick() {
		// layer objects by ascending y position
		objects.sort((o1, o2) -> Integer.compare((int)o1.getPos()[1], (int)o2.getPos()[1]));
		
		// tick objects
		for(GameObject o : objects) {
			o.tick();
		}
	}

	@Override
	public void render(Graphics2D g) {
		// background
		g.setColor(Colors.black);
		g.fillRect(0, 0, main.getWidth(), main.getHeight());
		
		// render objects
		for(GameObject o : objects) {
			o.render(g);
		}
	}

}
