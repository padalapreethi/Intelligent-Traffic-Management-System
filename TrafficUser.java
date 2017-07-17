package com.smts.server;

import java.util.Scanner;

class TrafficUser {

	static Integer start;
	static Integer end;
	static Integer route;
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		// Creating an instance of TrafficManager Class.
		TrafficManager manager = new TrafficManager();

		// Taking source and destination as input from user
		System.out.print("Enter Source : ");
		start = sc.nextInt();
		System.out.print("Enter Destination : ");
		end = sc.nextInt();
		System.out.println("\n");

		// calling the functions from TrafficManager(Requesting service)
		manager.setJourney(start, end);
		System.out.print("Select Route : ");
		route = sc.nextInt();
		manager.chooseRoute(start, end, route);
	}
}