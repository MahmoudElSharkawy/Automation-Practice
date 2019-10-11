package constructor;

public class ConstructorDemo2 {
	
	String name;
	int id;
	
	 public ConstructorDemo2() {
		 System.out.println("First Constructor Running");
	 }
	 
	 public ConstructorDemo2(String name) {
		 System.out.println("Second Constructor Running " + name);
	 }
	 
	 public ConstructorDemo2(String name, int id) {
		 this.name = name;
		 this.id = id;
		 System.out.println("Third Constructor Running " + name + " " + id);
	 }


}
