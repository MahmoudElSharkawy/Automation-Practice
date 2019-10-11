package courses;

public class EnglishCourse {

	int students,weeks,days;
	
	public static void main(String[] args) {

		EnglishCourse Eng101 = new EnglishCourse();
		
		int totalDays;
		
		Eng101.students = 10;
		Eng101.weeks = 4;
		Eng101.days = 3;
		
		totalDays = Eng101.weeks * Eng101.days;
		
		System.out.println("The English 101 Course is total of "+totalDays +" days");

	}

}
