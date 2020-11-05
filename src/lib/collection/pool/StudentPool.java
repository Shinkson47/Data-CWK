package lib.collection.pool;

import lib.collection.nodes.CollectiveNode;
import lib.collection.pool.Pool;
import lib.student.Student;

import java.util.Objects;

/**
 * <h1>A pool of students.</h1>
 * <br>
 * <p>
 * All active students, regardless of relationship, are found in {@link StudentPool#Global}, where thier order
 * bears no relevance to hierarchy or mentor/mentee relationship.
 * </p>
 *
 * @author <a href="https://www.shinkson47.in">Jordan T. Gray on 03/11/2020</a>
 * @version 1
 * @since v1
 */
public class StudentPool extends Pool<Student> {
    public static StudentPool Global = new StudentPool();

    // TODO Static wrappers
    // TODO docs
    // Rest of the methods.

    public Student findByP(String P){
        for (Student s : this){
            if (s.getpNumber() == P)
                return s;
        }
        return null;
    }

    public Student findMentorByP(String p){
        Student s = findByP(p);
        try {
            return (s.getMentees().size() > 0) ? s : null;
        } catch (NullPointerException e) {  // No student matches P
            return null;
        }
    }

    //throws nullpointer
    // returns null
    public Student findMenteeByP(String p){
        Student s = findByP(p);
        try {
            return (s.getMentor() != null) ? s : null;
        } catch (NullPointerException e) {  // No student matches P
            return null;
        }
    }

    /**
     * Instantiates many students into the global pool.
     * @param exampleStudents
     */
    public static void createManyStudents(String[] exampleStudents) {
        for (String s : exampleStudents){
            if (Global.findByP(s) != null)
                continue;
            else
                new Student(s);
        }
    }

    public void setRelationship(String _mentor, String _mentee){
        Student mentor = findByP(_mentor);
        if (mentor == null) return;

        Student mentee = findByP(_mentee);
        if (mentee == null) return;

        mentee.setMentor(mentor);
        mentor.addMentee(mentee);
    }
}
