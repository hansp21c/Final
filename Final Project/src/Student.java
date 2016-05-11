import java.util.List;
import java.util.ArrayList;
import java.util.Random;


public class Student extends AbstractPerson implements Comparable<Student> {

	List<Student> listOfStudents = new ArrayList<Student>();
	
	public Student(String fname, String lname){
		
		Random random = new Random();
		IDNum = 1000 + random.nextInt(8999);
		
		firstname = fname;
		lastname = lname;
		
	}
	
	@Override
    public String toString() {
		return firstname + " " + lastname + " " + "ID: " + IDNum;
	}

	public String firstName() {

		return firstname;

	}

	public String lastName() {

		return lastname;

	}

	public int IDNum() {

		return IDNum;

	}

	public void setFirstName(String fname) {

		firstname = fname;

	}

	public void setLastName(String lname) {

		lastname = lname;

	}

	public void setFullName(String fname, String lname) {

		firstname = fname;
		lastname = lname;

	}
	public void setIDNum(int ID){
		
		IDNum = ID;
		
	}

	@Override
	public int compareTo(Student s) {
		
		return this.lastname.compareToIgnoreCase(s.lastname);
	}

}
