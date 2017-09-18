package paula.mcm.asgn1.data;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import student.name.asgn1.dto.Course;
import student.name.asgn1.exceptions.CourseNotFoundException;
import student.name.asgn1.exceptions.DuplicateCourseException;


public class CourseManager implements CourseCatalog {
	private static CourseManager instance = null;
	private Map<String, Course> courses = null;
	
	private CourseManager() { 
		courses = new ConcurrentHashMap<String, Course>();
	}

	public static synchronized CourseManager getInstance() {
		if (instance == null) {
			instance = new CourseManager();
		}
		return instance;
	}
	
	public Collection<Course> getAllCourses() {
		return courses.values();
	}

	@Override
	public Course getCourse(String courseCode) throws CourseNotFoundException {
		if ( courseCode == null ) {
			throw new CourseNotFoundException("No course with null courseCode");
		}
		if (courses.containsKey(courseCode)) {
			return courses.get(courseCode);
		} else {
			throw new CourseNotFoundException("No course with code " + courseCode);
		}
	}

	@Override
	public Course addCourse(Course c) throws DuplicateCourseException {
		if ( c == null ) {
			throw new DuplicateCourseException("Cannot add a null Course");
		}
		String code = c.getCourseCode();
		if (courses.containsKey(code) ){
			throw new DuplicateCourseException("Duplicate course code " + code );
		}
		courses.put(code, c);
		return c;
	}

	@Override
	public Course updateCourse(Course c) throws CourseNotFoundException {
		if ( c == null ) {
			throw new CourseNotFoundException("Cannot update a null Course");
		}
		Course oldC = getCourse(c.getCourseCode());
		if ( c.equals(oldC)) {
			// no change - nothing to do
			return c;
		}
		// insert changed course
		courses.put(c.getCourseCode(), c);
		// retrieve again to get derived fields, if there are any
		return getCourse(c.getCourseCode());
	}

	@Override
	public Course deleteCourse(String courseCode) throws CourseNotFoundException {
		if ( courseCode == null ) {
			throw new CourseNotFoundException("No course with null courseCode");
		}
		if (courses.containsKey(courseCode)) {
			Course c = courses.get(courseCode);
			courses.remove(courseCode);
			return c;
		} else {
			throw new CourseNotFoundException("No course with code " + courseCode);
		}
	}
}


