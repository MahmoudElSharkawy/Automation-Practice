package overriding;

//Can't Override a final method!!

class Animal {
	public void move() {
		System.out.println("Animals can move method");
	}
}

class Dog extends Animal {
	public void move() {
		System.out.println("Dogs can move too method");
	}
}

public class TestDog {

	public static void main(String[] args) {

		Animal a = new Animal();
		Animal b = new Dog();

		a.move();
		b.move();

	}

}
