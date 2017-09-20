package mudrak.patel.asgn1.data;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import mudrak.patel.asgn1.dto.Course;
import mudrak.patel.asgn1.exceptions.CourseNotFoundException;
import mudrak.patel.asgn1.exceptions.DuplicateCourseException;

public interface CourseCatalog extends Remote {
	
	public Course getCourse(String courseCode) throws CourseNotFoundException, RemoteException;

	public Course addCourse(Course c) throws DuplicateCourseException, RemoteException;

	public Course deleteCourse(String courseCode) throws CourseNotFoundException, RemoteException;
	
	public Course updateCourse(Course c) throws CourseNotFoundException, RemoteException;
	
	public Collection<Course> getAllCourses();
}
/*
 //* Code from ecentennial
 *public interface CourseCatalog {

     public Course getCourse(String courseCode) throws CourseNotFoundException;

     public Course addCourse(Course c) throws DuplicateCourseException;

     public Course deleteCourse(String courseCode) throws CourseNotFoundException;

     public Course updateCourse(Course c) throws CourseNotFoundException;

}
 * */
