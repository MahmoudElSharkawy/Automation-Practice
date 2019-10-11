package polymorphism;

public class OverloadDemo {

	
	public void demo(int a) {
		System.out.println(a);
	}
	
	public void demo(int a, int b) {
		System.out.println(a +", " + b);
	}
	
	public double demo(double a) {
		System.out.println(a);
		return a*a;
	}

	
	
	
}
