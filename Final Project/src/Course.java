import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Course{

	List<Student> roster = new ArrayList<Student>();
	String courseName;
	Teacher teacher;
	
	public Course(String name){
		
		courseName = name;
		
	}
	
	public void nameCourse(String n){
		
		courseName = n;
		
	}
	
	public void addStudent(Student student){
		
		roster.add(student);
		
		Collections.sort(roster);
	} 
	public void addTeacher(Teacher t){
		
		teacher = t;
		
	}
	public List<Student> listStudents(){
		
		return roster;
	}
	
	@Override
	 public String toString() {
		return courseName;
	}

}
