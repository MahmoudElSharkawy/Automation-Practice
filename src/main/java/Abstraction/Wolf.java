package Abstraction;

public class Wolf extends Animal {

	public void sound() {
		System.out.println("WOOOooOoOOOoOOOO!!");
	}

	public static void main(String[] args) {

		Animal w = new Wolf();
		w.sound();

	}

}
