package com.spellbound.states;

import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Comparator;

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
	
	// TEMP, for debugging
	int timer = 0;
	
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
	
	private void sortObjects() {
		// layer objects by ascending y position
		objects.sort(Comparator.comparing(GameObject::getY));
	}
	
	@Override
	public void tick() {
		// tick objects
		for(GameObject o : objects) {
			o.tick();
		}
	}

	@Override
	public void render(Graphics2D g) {
		// background
		g.setColor(Colors.green.darker().darker());
		g.fillRect(0, 0, main.getWidth(), main.getHeight());
		
		// render objects
		sortObjects();
		for(GameObject o : objects) {
			o.render(g);
		}
		
		// *** GUI ***
		
		// render player stamina bar
		g.setColor(Colors.white);
		g.setFont(new Font("Monospaced", Font.PLAIN, 18));
		g.drawString("Run Stamina:", 10, 25);
		g.setColor(Colors.white.darker().darker());
		g.fillRect(150, 10, 200, 20);
		if(player.getRunStamina() < 50) {
			g.setColor(Colors.red);
		} else if(player.getRunStamina() < 100) {
			g.setColor(Colors.orange);
		} else if(player.getRunStamina() < 150) {
			g.setColor(Colors.yellow);
		} else {
			g.setColor(Colors.blue);
		}
		g.fillRect(150, 10, (int)player.getRunStamina(), 20);
		g.setColor(Colors.black);
		g.drawRect(150, 10, 200, 20);
	}

}
