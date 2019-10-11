package arraylist;

import java.util.ArrayList;

public class ArraylistDemo {

	public static void main(String[] args) {


		ArrayList<String> al = new ArrayList<String>();
		
		al.add("Mahmoud");
		al.add("Atef");
		al.add("Mohamed");
		al.add("ElSharkawy");
		al.add("Ibrahim");
		System.out.println(al);
		
		al.add(0, "7mbozo");
		System.out.println(al);
		
		al.remove(1);
		al.remove("Mohamed");
		System.out.println(al);

	}

}
