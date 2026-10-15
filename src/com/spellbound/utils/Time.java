package com.spellbound.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Time {
	
	public static int day;
	public static int hour;
	public static int minute;
	public static int second;
	public static int timer;
	public static String time;
	
	public Time() {
		day = 0;
		hour = 0;
		minute = 0;
		second = 0;
		timer = 0;
		time = "morning";
	}
	
	public static void getTime(String filepath) {
		String timeString = "";
		try {
			timeString = Files.readString(Path.of(filepath));
		} catch(IOException e) {
			e.printStackTrace();
			return;
		}
		day = Integer.parseInt(timeString.substring(0, timeString.indexOf(" ")));
		timeString = timeString.substring(timeString.indexOf(" ") + 1);
		hour = Integer.parseInt(timeString.substring(0, timeString.indexOf(" ")));
		timeString = timeString.substring(timeString.indexOf(" ") + 1);
		minute = Integer.parseInt(timeString.substring(0, timeString.indexOf(" ")));
		timeString = timeString.substring(timeString.indexOf(" ") + 1);
		second = Integer.parseInt(timeString.substring(0, timeString.indexOf(" ")));
		timeString = timeString.substring(timeString.indexOf(" ") + 1);
		timer = Integer.parseInt(timeString);
	}
	
	public static void setTime(String filepath) {
		try {
			
			String timeString = day + " " + hour + " " + minute + " " + second + " " + timer;
			
			Files.writeString(Path.of(filepath), timeString);
			System.out.println("Time written to file successfully.");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void tick() {
		timer++;
		if(timer >= 6) { // 60 ticks/second, 10 in-game seconds/second
			second++;
			timer = 0;
		}
		if(second >= 60) { // 60 seconds per minute
			minute++;
			second = 0;
		}
		if(minute >= 60) {// 60 minutes per hour
			hour++;
			minute = 0;
		}
		if(hour >= 24) {
			day++;
			hour = 0;
			if(day % 5 == 0) // autosaves every 5 in-game days
				setTime("res/properties/time.txt");
		}
		
		switch(hour) {
		case 0,1,2,3,4,5 -> time = "early morning";
		case 6,7,8,9,10,11 -> time = "morning";
		case 12,13,14,15,16,17 -> time = "afternoon";
		case 18,19,20,21,22,23 -> time = "night";
		}
	}
	
}
