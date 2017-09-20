package mudrak.patel.asgn1.data;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import mudrak.patel.asgn1.dto.Course;
import mudrak.patel.asgn1.exceptions.CourseNotFoundException;
import mudrak.patel.asgn1.exceptions.DuplicateCourseException;


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
	public Course addCourse(Course course) throws DuplicateCourseException {
		if ( course == null ) {
			throw new DuplicateCourseException("Cannot add a null Course");
		}
		String code = course.getCourseCode();
		if (courses.containsKey(code) ){
			throw new DuplicateCourseException("Duplicate course code " + code );
		}
		courses.put(code, course);
		return course;
	}

	@Override
	public Course updateCourse(Course course) throws CourseNotFoundException {
		if ( course == null ) {
			throw new CourseNotFoundException("Cannot update a null Course");
		}
		Course oldC = getCourse(course.getCourseCode());
		if ( course.equals(oldC)) {
			// no change - nothing to do
			return course;
		}
		// insert changed course
		courses.put(course.getCourseCode(), course);
		// retrieve again to get derived fields, if there are any
		return getCourse(course.getCourseCode());
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


