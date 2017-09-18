package paula.mcm.asgn1.data;

import student.name.asgn1.dto.Course;
import student.name.asgn1.exceptions.CourseNotFoundException;
import student.name.asgn1.exceptions.DuplicateCourseException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CourseCatalog extends Remote {
	
	public Course getCourse(String courseCode) throws CourseNotFoundException, RemoteException;

	public Course addCourse(Course c) throws DuplicateCourseException, RemoteException;

	public Course deleteCourse(String courseCode) throws CourseNotFoundException, RemoteException;
	
	public Course updateCourse(Course c) throws CourseNotFoundException, RemoteException;
}
