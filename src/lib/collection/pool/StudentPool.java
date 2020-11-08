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

    /**
     * <h2>Finds a student within the pool, using thier p number.</h2>
     * @apiNote Rejects call <b>P</b> is not a valid P Number according to {@link Student#isValidPNumber(String)}
     * @param P The students p Number.
     * @return The matching student, null if no student matched or <b>P</b> is not a valid P Number.
     */
    public Student findByP(String P){
        return findByP(P, true);
    }

    /**
     * <h2>Finds a student within the pool, using thier p number.</h2>
     * Rejects call if assertP is true AND <b>P</b> is not a valid P Number according to {@link Student#isValidPNumber(String)}
     * @param P The students p Number.
     * @param assertP If true, will validate that the string is a valid P Number.
     * @return The matching student, null if no student matched or <b>P</b> was tested and found to be an invalid P Number
     * @deprecated {@link StudentPool#findByP(String)} should almost always be used. Only use this signature to disable p number validation if it's absolutely required, and handled elsewhere.
     */
    @Deprecated
    public Student findByP(String P, boolean assertP){
        if (assertP && !Student.isValidPNumber(P)) return null; // Double & for efficiency. P number will only be evaluated if assertP is true.
        for (Student s : this)
            if (s.getpNumber().equals(P))
                return s;

        return null;
    }

    /**
     * <h2>Finds a mentor by their P</h2>
     * Uses {@link StudentPool#findByP(String)} to locate a student,<br>
     * and {@link Student#isMentor()} to determine if they're a mentor.
     *
     * @see StudentPool#findByP(String)
     * @see Student#isMentor()
     * @see StudentPool#findMenteeByP(String)
     * @param p The p number of the student to search for.
     * @return <blockquote>
     *          A matching student, ONLY IF they're considered to be a mentor. <br>
     *          If no matching student is found, returns null.<br>
     *          If a matching student is found, but is not a mentor, returns null.
     * </blockquote>
     */
    public Student findMentorByP(String p){
        Student s = findByP(p);
        try {
            return (s.isMentor()) ? s : null;
        } catch (NullPointerException e) {  // No student matches P
            return null;
        }
    }

    /**
     * <h2>Finds a mentee by their P</h2>
     * Uses {@link StudentPool#findByP(String)} to locate a student,<br>
     * and {@link Student#isMentee()} to determine if they're a mentee.
     *
     * @see StudentPool#findByP(String)
     * @see Student#isMentee()
     * @see StudentPool#findMentorByP(String)
     * @param p The p number of the student to search for.
     * @return <blockquote>
     *          A matching student, ONLY IF they're considered to be a mentee. <br>
     *          If no matching student is found, returns null.<br>
     *          If a matching student is found, but is not a mentee, returns null.
     * </blockquote>
     */
    public Student findMenteeByP(String p){
        Student s = findByP(p);
        try {
            return (s.isMentee()) ? s : null;
        } catch (NullPointerException e) {  // No student matches P
            return null;
        }
    }


    /**
     * <h2>Creates a mentee/mentor relationship between two students in the pool</h2>
     * By adding setting <b>_mentee</b>'s mentor to <b>_mentor</b>, and adding <b>_mentee</b> to <b>_mentor</b>'s mentees.
     * <br>
     * @apiNote Rejects call if <b>_mentor</b> equals <b>_mentee</b>, if either of the p's are not valid, or if either student does not exist.
     * @param _mentor The P Number of the student to be considered the <u><b>mentor</b></u> in this relationship.
     * @param _mentee The P Number of the student to be considered the <u><b>mentee</b></u> in the relationship.
     */
    public void setRelationship(String _mentor, String _mentee){
        if  (_mentee.equals(_mentor)) return;           // Cannot create a two entity relationship within one entity.

        Student mentor = findByP(_mentor);              // This call inertly tests that the p number is valid, and that the student exists.
        if (mentor == null) return;

        Student mentee = findByP(_mentee);
        if (mentee == null) return;

        mentee.setMentor(mentor);
        mentor.addMentee(mentee);
    }

    //#region static
    /**
     * <h2>Instantiates many students into {@link StudentPool#Global}</h2>
     * Creates many students from a list of P Numbers.
     * <br>Skips P Numbers if they're not valid p numbers, or if a student with that number already exists.
     * @param exampleStudents
     */
    public static void createManyStudents(String[] exampleStudents) {
        for (String s : exampleStudents){
            /*
                Find by P also validates P, however if a p number is not valid this would assume
                that the student doesn't exist and will attempt to create an invalid PNumbered student.

                assertP: false prevents validation of s's P, so we can inversely test for it here, and skip directly
                if the p number is not valid.

                Double pipeline for efficiency. This will only search for a student if the p number is valid.
             */
            if (Student.isValidPNumber(s) || Global.findByP(s, false) == null)
                new Student(s);
        }
    }
    //#endregion static
}
