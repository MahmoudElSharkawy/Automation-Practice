package polymorphism;

//Override
public class Horse extends Animal {

	public static void main(String[] args) {

		Animal h = new Horse();
		h.sound();

	}

	@Override
	public void sound() {
		System.out.println("Nighhhh");
	}

}
