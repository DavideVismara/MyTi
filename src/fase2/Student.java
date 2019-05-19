package fase2;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Student {

	//definizione oggetto Student
	
	private String firstName;
	private String lastname;
	private String birthdate; // DD/MM/YYYY, DD-MM-YYYY, DD.MM.YYYY
								// YYYY/MM/DD, YYYY-MM-DD, YYYY.MM.DD
	private int birth_day;
	private int birth_month;
	private int birth_year;
	
	//l'attributo grades l'ho interpretato come un'array list di String
	private ArrayList<String> grades;

	public Student(String firstName, String lastName, String birthdate, String... grades) {
		this.firstName = firstName;
		this.lastname = lastName;
		this.birthdate = birthdate;
		inizializeBirtDate_Day_Month_Year(birthdate);
		this.grades = new ArrayList<String>();
		inizializeGrades(grades);
	}

	private void inizializeBirtDate_Day_Month_Year(String birthdate) {
		String sep = "[./-]+";
		String[] BirthDay_element = birthdate.split(sep);

		if (BirthDay_element[0].length() == 4) {
			this.birth_year = Integer.parseInt(BirthDay_element[0]);
			this.birth_day = Integer.parseInt(BirthDay_element[2]);
		} else {
			this.birth_year = Integer.parseInt(BirthDay_element[2]);
			this.birth_day = Integer.parseInt(BirthDay_element[0]);
		}
		this.birth_month = Integer.parseInt(BirthDay_element[1]);
	}

	private void inizializeGrades(String[] grades) {
		for (int i = 0; i < grades.length; i++) {
			this.grades.add(grades[i]);
		}
	}

	public int getAge() {
		LocalDate l_birthdate = LocalDate.of(birth_year, birth_month, birth_day);
		LocalDate now = LocalDate.now();
		Period diff = Period.between(l_birthdate, now);
		return diff.getYears();
	}

	public double getAvg_Grades() {
		double avg;
		double grades_sum = 0.0;
		for (int i = 0; i < grades.size(); i++) {
			grades_sum = grades_sum + Double.valueOf(grades.get(i));
		}

		avg = grades_sum / Double.valueOf(grades.size());
		return avg;
	}

	public String getStudentPresentation() {
		String pattern = "%s %s, born on %s (%d years old), has an average of %.2f";
		return String.format(pattern, firstName, lastname, birthdate, getAge(), getAvg_Grades());
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastname() {
		return lastname;
	}

	public String getBirthdate() {
		return birthdate;
	}

}
