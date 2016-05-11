import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {
	static List<Course> courseList = new ArrayList<Course>();
	static List<Student> studentList = new ArrayList<Student>();
	static List<Teacher> teacherList = new ArrayList<Teacher>();

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		int userIntInput;

		System.out.println("-MAIN MENU-");
		System.out.println(" ");
		System.out.println("Do you want to...");
		System.out.println("1-Create Student         4-Create Teacher");
		System.out.println("2-Create Course          5-Add Teacher to Course");
		System.out.println("3-Add Student to Course  6-View Course Roster");
		System.out.println(" ");
		System.out.println("Type One: ");
		System.out.println(" ");

		userIntInput = input.nextInt();

		handleMainMenu(userIntInput);
		input.close();
	}

	public static void handleMainMenu(int i) {
		String[] yes = new String[0];
		Scanner input = new Scanner(System.in);
		int exit;
		switch (i) {
		case 1:
			createStudent();
			break;

		case 2:

			createCourse();
			break;

		case 3:

			addStudentToCourse();
			break;

		case 4:
			createTeacher();
			break;

		case 5:
			addTeacherToCourse();
			break;

		case 6:

			viewCourseRoster();
			break;
		default:
			System.out.println("==================================================");
			System.out.println("              ERROR: Invalid Entry");
			System.out.println("  Please type any key to return to the main menu");
			System.out.println("==================================================");
			exit = input.nextInt();

			if (exit == 0) {

				main(yes);
			} else {
				main(yes);
			}

			break;

		}
		input.close();

	}

	public static void createStudent() {
		String[] yes = new String[0];
		String firstname;
		String lastname;

		Scanner input = new Scanner(System.in);

		System.out.println("Enter student first name: ");
		firstname = input.nextLine();
		System.out.println("Enter student last name: ");
		lastname = input.nextLine();

		Student student = new Student(firstname, lastname);

		studentList.add(student);
		main(yes);
		input.close();

	}

	public static void createTeacher() {
		String[] yes = new String[0];
		String firstname;
		String lastname;

		Scanner input = new Scanner(System.in);

		System.out.println("Enter teacher first name: ");
		firstname = input.nextLine();
		System.out.println("Enter teacher last name: ");
		lastname = input.nextLine();

		Teacher teacher = new Teacher(firstname, lastname);

		teacherList.add(teacher);
		main(yes);
		input.close();

	}

	public static void createCourse() {
		String[] yes = new String[0];
		String courseName;
		Scanner input = new Scanner(System.in);

		System.out.println("Enter a name for the course: ");
		courseName = input.nextLine();

		Course course = new Course(courseName);

		courseList.add(course);
		main(yes);
		input.close();

	}

	public static void addTeacherToCourse() {
		String[] yes = new String[0];
		int Teachnum;
		int CourseNum;
		int exit;
		Scanner input = new Scanner(System.in);

		if (teacherList.size() == 0 || courseList.size() == 0) {

			System.out.println("==================================================");
			System.out.println("ERROR: Either No Teachers, or No Courses to Choose");
			System.out.println("  Please type any key to return to the main menu");
			System.out.println("==================================================");
			exit = input.nextInt();

			if (exit == 0) {

				main(yes);
			} else {
				main(yes);
			}

		} else {

			for (int i = 0; i < courseList.size(); i++) {

				System.out.print("[" +i+ "]" + " " + courseList.get(i).toString() + " ");

			}
			System.out.println(" ");
			System.out.println("Choose a Course: ");

			CourseNum = input.nextInt();

			for (int i = 0; i < teacherList.size(); i++) {

				System.out.print("[" +i+ "]" + " " + teacherList.get(i).toString() + " ");

			}

			System.out.println(" ");
			System.out.println("Choose a teacher: ");

			Teachnum = input.nextInt();

			if (Teachnum > studentList.size() - 1 || CourseNum > courseList.size() - 1) {

				System.out.println("==================================================");
				System.out.println("       ERROR: Invalid Index Inputted (lol)");
				System.out.println("  Please type any key to return to the main menu");
				System.out.println("==================================================");
				exit = input.nextInt();

				if (exit == 0) {

					main(yes);
				} else {
					main(yes);
				}
				input.close();

			} else {

				courseList.get(CourseNum).addTeacher(teacherList.get(Teachnum));
				System.out.println(" ");
				System.out.println("Done.");

				main(yes);
				input.close();
			}
		}

	}

	public static void addStudentToCourse() {

		String[] yes = new String[0];
		int Studentnum;
		int CourseNum;
		int exit;
		Scanner input = new Scanner(System.in);

		if (studentList.size() == 0 || courseList.size() == 0) {

			System.out.println("==================================================");
			System.out.println("ERROR: Either No Students, or No Courses to Choose");
			System.out.println("  Please type any key to return to the main menu");
			System.out.println("==================================================");
			exit = input.nextInt();

			if (exit == 0) {

				main(yes);
			} else {
				main(yes);
			}
			input.close();
		} else {

			for (int i = 0; i < courseList.size(); i++) {

				System.out.print("[" +i+ "]" + " " + courseList.get(i).toString() + " ");

			}
			System.out.println(" ");
			System.out.println("Choose a Course: ");

			CourseNum = input.nextInt();

			for (int i = 0; i < studentList.size(); i++) {

				System.out.print("[" +i+ "]" + " " + studentList.get(i).toString() + " ");

			}

			System.out.println(" ");
			System.out.println("Choose a teacher: ");

			Studentnum = input.nextInt();

			if (Studentnum > studentList.size() - 1 || CourseNum > courseList.size() - 1) {

				System.out.println("==================================================");
				System.out.println("       ERROR: Invalid Index Inputted (lol)");
				System.out.println("  Please type any key to return to the main menu");
				System.out.println("==================================================");
				exit = input.nextInt();

				if (exit == 0) {

					main(yes);
				} else {
					main(yes);
				}
				input.close();

			} else {

				courseList.get(CourseNum).addStudent(studentList.get(Studentnum));
				System.out.println(" ");
				System.out.println("Done.");

				main(yes);
				input.close();
			}
		}

	}

	public static void viewCourseRoster() {
		int CourseNum;
		int exit;
		String[] yes = new String[0];
		Scanner input = new Scanner(System.in);

		if (courseList.size() == 0) {

			System.out.println("==================================================");
			System.out.println("           ERROR: No Courses to Choose");
			System.out.println("  Please type any key to return to the main menu");
			System.out.println("==================================================");
			exit = input.nextInt();
			if (exit == 0) {

				main(yes);

			} else {

				main(yes);
			}

			input.close();

		}

		for (int i = 0; i < courseList.size(); i++) {

			System.out.print(i + " " + courseList.get(i).toString() + " ");

		}
		System.out.println(" ");
		System.out.println("Choose a Course: ");

		CourseNum = input.nextInt();

		if (courseList.get(CourseNum).roster.size() == 0) {

			System.out.println("==================================================");
			System.out.println("           ERROR: No Students in Roster");
			System.out.println("  Please type any key to return to the main menu");
			System.out.println("==================================================");
			exit = input.nextInt();
			if (exit == 0) {

				main(yes);

			} else {

				main(yes);
			}

			input.close();

		} else {

			if (courseList.get(CourseNum).teacher == null) {

				System.out.println("Students: " + courseList.get(CourseNum).listStudents());
				System.out.println("-Input any number to Return to Main Menu-");

			} else {

				System.out.println("Teacher: " + courseList.get(CourseNum).teacher.toString());
				System.out.println("Students: " + courseList.get(CourseNum).listStudents());
				System.out.println("-Input any number to Return to Main Menu-");
			}

			exit = input.nextInt();

			if (exit == 0) {

				main(yes);

			} else {

				main(yes);
			}

		}
		input.close();
	}

}
