package com.spellbound.main;

import java.awt.Canvas;

import javax.swing.JFrame;

/*
 * Window class handles JFrame and drawing canvas, initializing settings and
 * storing JFrame and Canvas for access
 */

public class Window {
	
	private JFrame frame;
	private Canvas canvas;
	
	public Window(int width, int height, String title) {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.requestFocus();
		
		canvas = new Canvas();
		canvas.setSize(width, height);
		canvas.setFocusable(false);
		
		frame.add(canvas);
		frame.pack();
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
}
