package arraylist;

import java.util.ArrayList;

public class ArraylistIntDemo {

	public static void main(String[] args) {

		ArrayList<Integer> ali = new ArrayList<Integer>();

		ali.add(10);
		ali.add(20);
		ali.add(30);
		ali.add(40);
		ali.add(50);
		System.out.println(ali);
		System.out.println(ali.get(1));

		for (int i = 0; i < ali.size(); i++) {
			System.out.println(ali.get(i));
		}

		ali.remove(ali.size() - 1);
		ali.remove(3);
		System.out.println(ali);

	}

}
