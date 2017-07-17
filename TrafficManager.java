package com.smts.server;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

import com.smts.server.Mail;

@SuppressWarnings("unused")
public class TrafficManager {
	Places places = new Places();
	// Routes routes = new Routes();

	Integer[] rtOne = { 1, 5, 9, 12 };
	Integer[] rtTwo = { 1, 2, 3, 8, 7, 12 };
	Integer[] rtThree = { 1, 2, 4, 5, 6, 12 };
	Integer[] rtFour = { 1, 2, 4, 8 };
	Integer[] rtFive = { 1, 2, 5, 7, 8 };

	boolean[] signal = { true, false, true, true, true, true, true, false,
			false, false, true, true };

	Integer accident = 7;

	int flag;

	int p = 0;

	ArrayList<Integer> indexes = new ArrayList<Integer>();
	ArrayList<Integer> indexesAmb = new ArrayList<Integer>();
	ArrayList<Integer> indexesDoc = new ArrayList<Integer>();

	ArrayList<Integer[]> avRoutes = new ArrayList<Integer[]>();

	@SuppressWarnings({ "rawtypes" })
	public void setJourney(Integer start, Integer end) {

		// accidentValidation(accident);
		// ambulanceValidation(accident);
		// alertDoctor(accident);

		ArrayList<Integer[]> list = new ArrayList<Integer[]>();
		list.add(rtOne);
		list.add(rtTwo);
		list.add(rtThree);
		list.add(rtFour);
		list.add(rtFive);

		int rtNo = 1;

		ArrayList<Integer> tp = new ArrayList<Integer>();

		Iterator itr = list.iterator();
		while (itr.hasNext()) {
			Integer[] temp = (Integer[]) itr.next();

			//

			for (int k = 0; k < temp.length; k++) {
				for (int j = 0; j < temp.length; j++) {
					if ((temp[k] == start) && (temp[j] == end)) {
						for (int tmp = k; tmp < j; tmp++) {
							if (temp[tmp] == accident) {
								flag = tmp;
								System.out.println("Accident");
							} else {
								// System.out.println("Clean Route");
							}
						}
						System.out.print("Route " + rtNo + ": ");
						for (int x = k; x <= j; x++) {
							System.out.print(temp[x] + ",");
							tp.add(temp[x]);
						}
						// Integer[] ttp = (Integer[])tp.toArray();

						Integer[] ret = new Integer[tp.size()];
						for (int i = 0; i < ret.length; i++) {
							ret[i] = tp.get(i).intValue();
						}

						avRoutes.add(ret);
						rtNo++;
						System.out.println("\n");
					}
				}
			}

			//
		}
		LocalDate date = LocalDate.now();
		DayOfWeek dow = date.getDayOfWeek();

		// get the current day for past history
		String dayName = dow.getDisplayName(TextStyle.FULL, Locale.ENGLISH);

		if (dayName.equals("Thursday")) {
			System.out
					.println("Best Route based on previous traffic details : 1\n");
		} else if (dayName.equals("Sunday")) {
			System.out
					.println("Best Route based on previous traffic details : 2\n");
		} else if (dayName.equals("Monday")) {
			System.out
					.println("Best Route based on previous traffic details : 1\n");
		} else {
			System.out
					.println("Best Route based on previous traffic details : 4\n");
		}
	}

	void chooseRoute(Integer start, Integer end, Integer route) {
		/*
		 * System.out.println("avRoute : ");
		 * System.out.println(avRoutes.get(route-1));
		 */
		Integer[] arr = avRoutes.get((route - 1));
		/*
		 * System.out.println("\n"); for(int i : arr){ System.out.println(i); }
		 */
		System.out.print("\nSelected Route : ");
		for (Integer i : arr) {
			System.out.print(i + ",");
		}
		System.out.print("\n");
		getSignals(arr);

	}

	int a = 0;

	private void getSignals(Integer[] arr) {
		// TODO Auto-generated method stub

		// System.out.println(driverLocation);

		// code for signal validation
		while (a < (arr.length)) {
			Integer driverLocation = arr[a];
			if (signal[driverLocation] == true) {

				// code for speed validation
				System.out.print("\nMaintain Speed 40\n");
				if (a == arr.length) {
					break;
				} else {
					a++;
				}
				getSignals(arr);
			} else if (signal[driverLocation] == false) {
				System.out.print("\nMaintain Speed 30\n");
				if (a == arr.length) {
					break;
				} else {
					a++;
				}
				getSignals(arr);
			}
		}
	}

	private void accidentValidation(Integer accdnt) {
		// TODO Auto-generated method stub

		// creating instance of Mail Class
		Mail m = new Mail("tharunpractice@gmail.com", "#Tharun018#");

		// Validating Accident
		indexes.add(Math.abs(6 - accdnt));
		indexes.add(Math.abs(8 - accdnt));
		indexes.add(Math.abs(12 - accdnt));

		int min = 5;

		// getting the best possible hospital
		for (int i = 0; i < indexes.size(); i++) {
			int number = indexes.get(i);
			if (number < min)
				min = number;
		}
		// System.out.println(min);
		Integer hosp = min + accdnt;
		// System.out.println("Hospital : "+hosp+"\n");

		String[] toArr = { "tharunpractice@gmail.com" };

		m.set_from("tharunpractice@gmail.com");

		// sending a mail to hospital
		m.set_to(toArr);
		m.set_subject("Alert Hospital " + hosp + "!");
		m.set_body("There is an casuality near " + accdnt + " please be ready!");

		try {
			m.send();
			// System.out.println("Mail Sent!");
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Mail Not Sent!");
		}

	}

	private void ambulanceValidation(Integer accdnt) {
		// TODO Auto-generated method stub

		// creating instance of Mail Class
		Mail m = new Mail("tharunpractice@gmail.com", "#Tharun018#");

		// Validating Ambulance
		indexesAmb.add(Math.abs(1 - accdnt));
		indexesAmb.add(Math.abs(7 - accdnt));

		int min = 5;

		// getting the best possible ambulance
		for (int i = 0; i < indexesAmb.size(); i++) {
			int number = indexesAmb.get(i);
			if (number < min)
				min = number;
		}

		Integer amb = min + accdnt;

		String[] toArr = { "tharunpractice@gmail.com" };

		m.set_from("tharunpractice@gmail.com");

		// sending a mail to ambulance
		m.set_to(toArr);
		m.set_subject("Alert Ambulance " + amb + "!");
		m.set_body("There is an casuality near " + accdnt + " please go!");

		try {
			m.send();
			// System.out.println("Mail Sent!");
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Mail Not Sent!");
		}

	}

	private void alertDoctor(Integer accdnt) {
		// TODO Auto-generated method stub

		// creating instance of Mail Class
		Mail m = new Mail("tharunpractice@gmail.com", "#Tharun018#");
		
		// Validating Accident and Doctors
		indexesDoc.add(Math.abs(4 - accdnt));
		indexesDoc.add(Math.abs(8 - accdnt));

		int min = 5;

		// Finding the nearest doctor registered
		for (int i = 0; i < indexesDoc.size(); i++) {
			int number = indexesDoc.get(i);
			if (number < min)
				min = number;
		}

		Integer doc = min + accdnt;

		String[] toArr = { "tharunpractice@gmail.com" };

		m.set_from("tharunpractice@gmail.com");

		// Sending the email to doctor
		m.set_to(toArr);
		m.set_subject("Alert Doctor " + doc + "!");
		m.set_body("There is an casuality near " + accdnt + " please go!");

		try {
			m.send();
			// System.out.println("Mail Sent!");
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("Mail Not Sent!");
		}
	}
}