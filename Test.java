package com.smts.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Test {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		
		Integer[] test = {1, 2, 3, 4, 5, 6};
		
		ArrayList<Integer[]> list = new ArrayList<Integer[]>();
		list.add(test);
		
		Iterator itr = list.iterator();
		
		while(itr.hasNext()){
			List tmp = Arrays.asList(itr.next());
				System.out.println(tmp.get(1));
		}
	}
}
