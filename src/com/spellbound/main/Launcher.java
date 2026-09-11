package com.spellbound.main;

/*
 * Launcher class creates a new instance of Main class and calls start() method,
 * starting run() method (main game loop)
 */

public class Launcher {

	public static void main(String[] args) {
		new Main(960, 720, "Spellbound Fiefdoms | Greyson Morgan").start();
	}

}
