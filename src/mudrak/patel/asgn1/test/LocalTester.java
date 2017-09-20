package mudrak.patel.asgn1.test;

import java.util.Collection;

import mudrak.patel.asgn1.data.CourseCatalog;
import mudrak.patel.asgn1.data.CourseManager;
import mudrak.patel.asgn1.dto.Course;
import mudrak.patel.asgn1.dto.Professor;

public class LocalTester {

	private static void buildCatalog() {
	//	CourseCatalog cm = new CatalogManager();
		CourseCatalog cm = CourseManager.getInstance();
		System.out.println("Building course catalog");
		try {
			// replace up to catch to add courses you are taking this term
			cm.addCourse(new Course("COMP231", "Software Development Project"));
			Professor professor = new Professor("Paula", "McMillan");
			cm.addCourse(new Course("COMP303", "Java EE Programming", professor));
			cm.addCourse(new Course("COMP304", "Wireless Programming"));
			cm.addCourse(new Course("COMP306", "Web Service Programming"));
			cm.addCourse(new Course("COMP307", "Software Security"));
			cm.addCourse(new Course("COMP309", "Data Warehousing and Data Mining"));
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}

		System.out.println();
		System.out.println("Testing get all courses");
		try {
			System.out.println("Course catalog:");
			Collection<Course> courses = ((CourseManager) cm).getAllCourses();
			for (Course course : courses) {
				System.out.println(course);
			}
			System.out.println("done");
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		buildCatalog();
		// CourseCatalog cc = new CourseManager();
		CourseCatalog cc = CourseManager.getInstance();
		System.out.println();
		System.out.println("Testing getting a course by code");
	try {
			String courseCode = "COMP303";
			System.out.println("Retrieving " + courseCode);
			System.out.println(cc.getCourse(courseCode));
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
			e.printStackTrace();
		}
		try {
			String courseCode = "TEST 1234";
			System.out.println("Retrieving " + courseCode);
			System.out.println(cc.getCourse(courseCode));
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
		
		System.out.println();
		System.out.println("Testing adding a course");
		try {
			System.out.println("Adding TEST 1234");
			System.out.println(cc.addCourse(new Course("TEST 1234", "SQA and Testing")));
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
		try {
			System.out.println("Adding TEST 1234 again");
			System.out.println(cc.addCourse(new Course("TEST 1234", "Second Testing Course")));
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
		
		System.out.println();
		System.out.println("Testing deleting a course");
		try {
			System.out.println("Deleting COMP231");
			System.out.println(cc.deleteCourse("COMP231"));
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
		try {
			System.out.println("Deleting COMP231 again");
			System.out.println(cc.deleteCourse("COMP231"));
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
		
		System.out.println();
		System.out.println("Testing updating a course");
		System.out.println("Updating Test 1234");
		try {
			Course course = cc.getCourse("TEST 1234");
			System.out.println("Updating course TEST 1234");
			// insert your details in the next line - you teach MISC101
			course.setProfessor(new Professor( "Mudrak", "B.", "Patel") );
			// Set a title of your choice
			course.setCourseTitle("What would you teach?");
			course = cc.updateCourse(course);
			System.out.println("Updated: " + course);
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
		try {
			System.out.println("Updating a course that does not exist: HIST 800");
			Course course = new Course("HIST 800", "History of the World");
			cc.updateCourse(course);
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
		
		System.out.println();
		System.out.println("tests complete");
	}
}
