package com.smts.server;

public class Routes {
	
	public String[] routeOne = { "1", "6" };
	public String[] routeTwo = { "1", "3", "7", "6" };
	public String[] routeThree = { "1", "6", "7" };
	public String[] routeFour = { "1", "3", "4", "7" };
	
	String[][] routeNames = {routeOne, routeTwo, routeThree, routeFour};

	public void validate(int i, int j) {
		
		int temp = routeNames.length;
		// TODO Auto-generated method stub
		for(int first = 0; first< temp-1 ; first++){
			int temp1 = routeOne.length;
			if((Integer.parseInt(routeOne[first])==i)&&(Integer.parseInt(routeOne[temp1-1])==j)){
				System.out.println("Route One");
			}
		}
	}
	
}