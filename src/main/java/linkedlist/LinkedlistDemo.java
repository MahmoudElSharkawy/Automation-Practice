package linkedlist;

import java.util.LinkedList;

public class LinkedlistDemo {

	public static void main(String[] args) {

		// Linked List Declaration
		LinkedList<String> lnkdlst = new LinkedList<String>();

		// Add String Elements in the list
		lnkdlst.add("item 1");
		lnkdlst.add("item 2");
		lnkdlst.add("item 3");
		lnkdlst.add("item 4");
		lnkdlst.add("item 5");
		System.out.println(lnkdlst);

		// Add first and last item in the list
		lnkdlst.addFirst("First item added");
		lnkdlst.addLast("Last item added");
		System.out.println(lnkdlst);

		// get values
		Object val = lnkdlst.get(1);
		System.out.println("get " + val);
		// set values
		lnkdlst.set(4, "set item 4");
		System.out.println(lnkdlst);

		// add to a position
		lnkdlst.add(2, "New item 2");
		System.out.println(lnkdlst);
		// remove from a position
		lnkdlst.remove(3);
		System.out.println(lnkdlst);

		// Remove first and last items
		lnkdlst.removeFirst();
		lnkdlst.removeLast();
		System.out.println(lnkdlst);

	}

}
