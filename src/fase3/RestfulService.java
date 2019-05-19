package fase3;

import java.util.ArrayList;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import fase1.Fibonacci_v2;
import fase2.Student;

@Path("/")
public class RestfulService {
	private static ArrayList<Student> Students = new ArrayList<Student>();

	// chiamata per visualizzare tutti gli studenti
	@GET
	@Path("/retrieveStudent")
	@Produces(MediaType.TEXT_HTML)
	public String getAllStudents() {
		String retrieve = "<h1>Students retrieve</h1>";
		for (int i = 0; i < Students.size(); i++) {
			retrieve = retrieve + "<h2>" + Students.get(i).getStudentPresentation() + "</h2>";
		}

		return retrieve;
	}

	@POST
	@Path("/createStudent")
	@Produces(MediaType.TEXT_HTML)
	public String createStudent(@QueryParam("firstname") String firstName, @QueryParam("lastname") String lastName,
			@QueryParam("birthdate") String birthdate, @QueryParam("grades") String grades) {
		// inserimento studente
		String[] a = grades.split(",");
		Student s = new Student(firstName, lastName, birthdate, a);
		addStudent(s);
		return "<h1>" + s.getStudentPresentation() + "</h1>";
	}

	@POST
	@Path("/askFibonacci")
	@Produces(MediaType.TEXT_HTML)
	public String askFibonacci(@QueryParam("numerocifre") String numeroCifre) {
		// richiesta fibonacci
		Fibonacci_v2 f = new Fibonacci_v2(Integer.valueOf(numeroCifre));
		return "<h1>" + f.getFibonacciAnswer() + "</h1>";
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHello() {
		// Presentazione RES
		String resource = "<h1> Hello I'm MyTI RES written by Davide Vismara</h1>";
		return resource;
	}

	public static void addStudent(Student s) {
		getStudents().add(s);
	}

	public static ArrayList<Student> getStudents() {
		return Students;
	}

}
