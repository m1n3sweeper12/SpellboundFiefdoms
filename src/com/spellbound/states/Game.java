package com.spellbound.states;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Comparator;

import com.spellbound.main.Main;
import com.spellbound.objects.Camera;
import com.spellbound.objects.GameObject;
import com.spellbound.objects.Hostile;
import com.spellbound.objects.Passive;
import com.spellbound.objects.Player;
import com.spellbound.tiles.Map;
import com.spellbound.tiles.Tile;
import com.spellbound.utils.Colors;
import com.spellbound.utils.KeyManager;

public class Game extends State {
	
	private ArrayList<GameObject> objects;
	
	public static final int TILE_SIZE = 32;
	
	private Player player;
	
	// camera to follow player
	private Camera cam;
	
	// TEMP, for testing Hostile & Passive classes
	private Hostile enemy;
	private Passive npc;
	
	public static int SCREEN_WIDTH, SCREEN_HEIGHT;
	
	// TEMP
	public static boolean debugMode = false;
	private boolean debugToggle = true;
	
	// TEMP, for testing 
	Map test, currentMap;
	
	public Game(Main main) {
		super(main);
		
		SCREEN_WIDTH = main.getWidth();
		SCREEN_HEIGHT = main.getHeight();
		
		objects = new ArrayList<GameObject>();
		
		test = new Map();
		test.loadMapFile("res/test_map.txt");
		
		currentMap = test;
		
		// TEMP, will be handled using implemented file system
		player = new Player(64, 64, Game.TILE_SIZE, Game.TILE_SIZE);
		objects.add(player);
		
		enemy = new Hostile(256, 256, Game.TILE_SIZE, Game.TILE_SIZE);
		objects.add(enemy);
		
		npc = new Passive(512, 512, Game.TILE_SIZE, Game.TILE_SIZE);
		objects.add(npc);
		
		cam = new Camera(0, 0);
		
		// TEMP
		System.out.println("Color Key:\nblue -> player\nred -> hostile\ngreen -> passive");
	}
	
	private void sortObjects() {
		// layer objects by ascending y position
		objects.sort(Comparator.comparing(GameObject::getY));
	}
	
	@Override
	public void tick() {
		if(debugMode) {
			player.setRunStamina(200);
		}
		
		// only tick tiles that are visible on screen
		// TODO: 
		for(Tile t : currentMap.getTiles()) {
			if(t.getBounds().x > (player.getX() - Game.SCREEN_WIDTH)
					&& t.getBounds().x < (player.getX() + Game.SCREEN_WIDTH)
					&& t.getBounds().y > (player.getY() - Game.SCREEN_HEIGHT)
					&& t.getBounds().y < (player.getY() + Game.SCREEN_HEIGHT)) {
				t.tick();
			}
		}
		
		// tick camera
		cam.tick(player);
		
		// tick objects
		for(GameObject o : objects) {
			o.tick(test);
		}
		
		sortObjects();
		
		// TEMP, for debugging
		if(KeyManager.getKey(KeyEvent.VK_X) && debugToggle) {
			debugMode = !debugMode;
			debugToggle = false;
			System.out.println(debugMode);
		}
		
		if(!KeyManager.getKey(KeyEvent.VK_X)) {
			debugToggle = true;
		}
	}

	@Override
	public void render(Graphics2D g) {
		// background
		g.setColor(Colors.black);
		g.fillRect(0, 0, main.getWidth(), main.getHeight());
		
		g.translate(-cam.getX(), -cam.getY());
		
		// only render tiles that are visible on screen
		for(Tile t : currentMap.getTiles()) {
			if(t.getBounds().x > (player.getX() - Game.SCREEN_WIDTH)
					&& t.getBounds().x < (player.getX() + Game.SCREEN_WIDTH)
					&& t.getBounds().y > (player.getY() - Game.SCREEN_HEIGHT)
					&& t.getBounds().y < (player.getY() + Game.SCREEN_HEIGHT)) {
				t.render(g);
			}
		}
		
		// render objects
		for(GameObject o : objects) {
			o.render(g);
		}
		
		g.translate(cam.getX(), cam.getY());
		
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
		
		// DEBUG MODE
		if(debugMode) {
			g.setColor(Colors.red);
			g.setFont(new Font("Sans-serif", Font.BOLD, 24));
			g.drawString("DEBUG MODE", SCREEN_WIDTH - 200, 30);
		}
	}

}
