package com.spellbound.states;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Comparator;

import com.spellbound.main.Main;
import com.spellbound.objects.Camera;
import com.spellbound.objects.EnemyTest;
import com.spellbound.objects.GameObject;
import com.spellbound.objects.Hostile;
import com.spellbound.objects.MOBSTATES;
import com.spellbound.objects.Passive;
import com.spellbound.objects.Player;
import com.spellbound.tiles.Map;
import com.spellbound.tiles.Tile;
import com.spellbound.utils.Colors;
import com.spellbound.utils.KeyManager;
import com.spellbound.utils.SpriteHandler;

public class Game extends State {
	
	private ArrayList<GameObject> objects;
	private static ArrayList<Hostile> enemies;
	private ArrayList<Passive> passives;
	
	public static final int TILE_SIZE = 64;
	
	private static Player player;
	
	// camera to follow player
	private Camera cam;
	
	// TEMP, for testing Hostile & Passive classes
	private EnemyTest e;
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
		
		SpriteHandler.loadAssets();
		
		objects = new ArrayList<GameObject>();
		enemies = new ArrayList<Hostile>();
		passives = new ArrayList<Passive>();
		
		test = new Map();
		test.loadMapFile("res/test_map.txt");
		
		currentMap = test;
		
		// TEMP, will be handled using implemented file system
		player = new Player(64, 64, Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE + 10);
		objects.add(player);
		
		e = new EnemyTest(256, 256, Game.TILE_SIZE, Game.TILE_SIZE);
		objects.add(e);
		enemies.add(e);
		
		npc = new Passive(512, 512, Game.TILE_SIZE, Game.TILE_SIZE, 100);
		objects.add(npc);
		passives.add(npc);
		
		cam = new Camera(0, 0);
		
		// TEMP
		System.out.println("Color Key:\nblue -> player\nred -> hostile\ngreen -> passive");
	}
	
	public static void playerAttack() {
		Line2D.Double attackLine = getAttackLine((GameObject)player);
		for(GameObject o : enemies) {
			if(attackLine.intersects(o.getDamageArea())) {
				o.damage(player.getPower());
			}
		}
	}
	
	private static Line2D.Double getAttackLine(GameObject o) {
		Line2D.Double l;
		
		switch(o.getDirection()) {
		case 0: // east
			l = new Line2D.Double((double)o.getCenterX(), (double)o.getCenterY(),
					(double)(o.getCenterX() + o.getStrikeDist()), (double)o.getCenterY());
			break;
		case 1: // south east
			l = new Line2D.Double((double)o.getCenterX(), (double)o.getCenterY(),
					(double)(o.getCenterX() + o.getStrikeDist()), (double)(o.getCenterY() + o.getStrikeDist()));
			break;
		case 2: // south
			l = new Line2D.Double((double)o.getCenterX(), (double)o.getCenterY(),
					(double)(o.getCenterX()), (double)o.getCenterY() + o.getStrikeDist());
			break;
		case 3: // south west
			l = new Line2D.Double((double)o.getCenterX(), (double)o.getCenterY(),
					(double)(o.getCenterX() - o.getStrikeDist()), (double)o.getCenterY() + o.getStrikeDist());
			break;
		case 4: // west
			l = new Line2D.Double((double)o.getCenterX(), (double)o.getCenterY(),
					(double)(o.getCenterX() - o.getStrikeDist()), (double)o.getCenterY());
			break;
		case 5: // north west
			l = new Line2D.Double((double)o.getCenterX(), (double)o.getCenterY(),
					(double)(o.getCenterX() - o.getStrikeDist()), (double)o.getCenterY() - o.getStrikeDist());
			break;
		case 6: // north
			l = new Line2D.Double((double)o.getCenterX(), (double)o.getCenterY(),
					(double)(o.getCenterX()), (double)o.getCenterY() - o.getStrikeDist());
			break;
		case 7: // north east
			l = new Line2D.Double((double)o.getCenterX(), (double)o.getCenterY(),
					(double)(o.getCenterX() + o.getStrikeDist()), (double)o.getCenterY() - o.getStrikeDist());
			break;
		default:
			l = new Line2D.Double();
		}
		
		return l;
	}
	
	private void sortObjects() {
		// layer objects by ascending y position
		objects.sort(Comparator.comparing(GameObject::getY));
	}
	
	@Override
	public void tick() {
		// infinite running in debug mode
		if(debugMode) {
			player.setRunStamina(200);
		}
		
		// only tick tiles that are visible on screen
		for(Tile t : currentMap.getTiles()) {
			if(t.getBounds().x > (player.getX() - Game.SCREEN_WIDTH)
					&& t.getBounds().x < (player.getX() + Game.SCREEN_WIDTH)
					&& t.getBounds().y > (player.getY() - Game.SCREEN_HEIGHT)
					&& t.getBounds().y < (player.getY() + Game.SCREEN_HEIGHT)) {
				t.tick();
			}
		}
		
		// make enemies chase player
		for(Hostile h : enemies) {
			if(h.isPlayerSeen()) {
				h.setState(MOBSTATES.Chase);
			}
		}
		
		// tick camera
		cam.tick(player);
		
		// tick objects
		for(GameObject o : objects) {
			o.tick(test);
		}
		
		// remove objects if health reaches 0
		enemies.removeIf(ob -> ob.getID() == 1 && ob.getHP() <= 0);
		passives.removeIf(ob -> ob.getID() == 2 && ob.getHP() <= 0);
		objects.removeIf(ob -> ob.getHP() <= 0);
		
		sortObjects();
		
		// TEMP, for debugging
		if(KeyManager.getKey(KeyEvent.VK_X) && debugToggle) {
			debugMode = !debugMode;
			debugToggle = false;
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
		
		// DEBUG MODE
		
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
		
		// render player inventory
		player.getInventory().render(g);
		
		// DEBUG MODE
		if(debugMode) {
			g.setColor(Colors.red);
			g.setFont(new Font("Sans-serif", Font.BOLD, 24));
			g.drawString("DEBUG MODE", SCREEN_WIDTH - 200, 30);
		}
	}

}
