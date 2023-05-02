/**
 * Class Course
 * 
 * @author Lihong Zhao & Haojie Zheng
 */

package course;

import roles.Student;

import java.util.ArrayList;
//import java.util.Locale;

public class Course {
    /**
     * course id
     */
    String id;
    /**
     * course name
     */
    String courseName;
    /**
     * lecturer name
     */
    String lecturer;
    /**
     * dates of course
     */
    String day;
    /**
     * start time of course
     */
    String start;
    /**
     * end time of date
     */
    String end;
    /**
     * capacity of course
     */
    int capacity;
    /**
     * total number od students of the course
     */
    int total = 0;
    /**
     * List of students of this course
     */
    ArrayList<Student> studentsIn = new ArrayList<>();

    /**
     * days
     */
    char[] dayList;

    /**
     * start time in int type
     */
    int intStart;
    /**
     * end time in int type
     */
    int intEnd;

    /**
     * Constructs a new Course object from a string containing course details.
     *
     * The input string should be in the following format:
     * "id; courseName; lecturer; day; start; end; capacity"
     * 
     * Example: "CS101; Introduction to Computer Science; John Doe; Monday; 09:00; 10:00; 30"
     * 
     * @param str A string containing the course information. This string should be 
     *            semicolon-separated and contain exactly 7 fields:
     *            1. The course ID.
     *            2. The course name.
     *            3. The name of the lecturer.
     *            4. The day the course is held.
     *            5. The start time of the course (as a string).
     *            6. The end time of the course (as a string).
     *            7. The capacity of the course (as an integer).
     * @throws IllegalArgumentException If the input string is null, empty, or does not 
     *                                  conform to the expected format, or if the capacity 
     *                                  cannot be parsed as an integer.
     */
    public Course(String str) {
        String[] course = str.split(";");
        
        // Check that the input string is correctly formatted
        if (course.length != 7) {
            throw new IllegalArgumentException("Invalid course string: " + str);
        }
        
        this.id = course[0].trim();
        this.courseName = course[1].trim();
        this.lecturer = course[2].trim();
        this.day = course[3].trim();
        this.start = course[4].trim();
        this.end = course[5].trim();

        try {
            this.intStart = Integer.valueOf(this.start.replaceAll(":", ""));
            this.intEnd = Integer.valueOf(this.end.replaceAll(":", ""));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid start or end time: " + start + ", " + end);
        }

        this.dayList = this.day.toCharArray();

        try {
            this.capacity = Integer.valueOf(course[6].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid capacity: " + course[6]);
        }
    }



    /**
     * Prints the course information in the following format:
     * "{id}|{courseName}, {start}-{end} on {day}, with course capacity: {capacity}, students: {total}, lecturer: Professor {lecturer}"
     */
    public void printCourse() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.id).append("|").append(this.courseName)
            .append(", ").append(this.start).append("-").append(this.end)
            .append(" on ").append(this.day)
            .append(", with course capacity: ").append(this.capacity)
            .append(", students: ").append(this.total)
            .append(", lecturer: Professor ").append(this.lecturer);
        System.out.println(sb.toString());
    }


    /**
     * get ID of course
     * @return course id
     */
    public String getId() {
        return this.id;
    }

    /**
     * get capacity of course
     * @return course capacity
     */
    public int getCapacity() {
        return this.capacity;
    }

    /**
     * get total number of students in the course
     * @return total number
     */
    public int getTotal() {
        return this.total;
    }

    /**
     * get days
     * @return copy of dayList
     */
    public char[] getDayList() {
        return this.dayList.clone();
    }

    /**
     * get int start time
     * @return start time
     */
    public int getIntStart() {
        return this.intStart;
    }

    /**
     * get int end time
     * @return end time
     */
    public int getIntEnd() {
        return this.intEnd;
    }

    /**
     * get course name
     * @return course name
     */
    public String getName() {
        return this.courseName;
    }

    /**
     * get lecturer name
     * @return lecturer name
     */
    public String getLecturer() {
        return this.lecturer;
    }

    /**
     * Increment total number
     */
    public void setTotal() {
        this.total++;
    }

    /**
     * add student to this course
     * @param student object
     */
    public void setStudentsIn(Student student) {
        if(student != null){
            this.studentsIn.add(student);
        }
    }

}