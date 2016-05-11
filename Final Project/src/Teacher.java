import java.util.Random;
public class Teacher extends AbstractPerson implements Comparable<Teacher> {
	
	public Teacher(String fname, String lname) {
		Random random = new Random();
		firstname = fname;
		lastname = lname;
		IDNum = 1000 + random.nextInt(8999);
		
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

	public void setIDNum(int ID) {

		IDNum = ID;

	}

	public int compareName(String LName) {

		return lastname.compareToIgnoreCase(LName);

	}
	
	public void addToCourse(int i){
		
		
		
	}

	@Override
	public int compareTo(Teacher t) {
		return this.lastname.compareToIgnoreCase(t.lastname);
	}

}
