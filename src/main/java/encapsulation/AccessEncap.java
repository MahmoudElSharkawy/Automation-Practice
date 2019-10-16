package encapsulation;

public class AccessEncap {

	public static void main(String[] args) {
	
		EncapTest access = new EncapTest();
		
		access.setName("Sharkawy");
		access.setAge(20);
		access.setIdNum("3699");
		
		System.out.println("Name is: " + access.getName() + " and Age is: " + access.getAge() + " and ID " + access.getIdNum());
		
	}

}
