package com.spellbound.main;

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

public class Main implements Runnable {
	
	// basic window info
	private int width, height;
	private String title;
	
	// game loop
	private boolean running = false;
	private Thread thread;
	
	// graphics
	private Window window;
	private BufferStrategy bs;
	private Graphics2D g;
	
	// game state
	
	
	public Main(int width, int height, String title) {
		this.width = width;
		this.height = height;
		this.title = title;
	}
	
	private void init() {
		window = new Window(width, height, title);
	}
	
	private void update() {
		
	}
	
	private void render() {
		bs = window.getCanvas().getBufferStrategy();
		if(bs == null) {
			window.getCanvas().createBufferStrategy(3);
			return;
		}
		g = (Graphics2D) bs.getDrawGraphics();
		///////////////////////////
		
		g.clearRect(0, 0, width, height);
		
		///////////////////////////
		g.dispose();
		bs.show();
	}
	
	public synchronized void start() {
		if(running) return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running) return;
		running = false;
		try {
			thread.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		init();
		
		int fps = 60;
		double timePerUpdate = 1000000000/fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int updates = 0;
		int renders = 0;
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime)/timePerUpdate;
			timer += now - lastTime;
			lastTime = now;
			
			while(delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			renders++;
			
			if(timer >= 1000000000) {
				window.getFrame().setTitle("Spellbound Fiefdoms | Greyson Morgan\t\tTICKS: "
						+ updates + " | FPS: " + renders);
				updates = 0;
				renders = 0;
				timer = 0;
			}
		}
		stop();
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
}
