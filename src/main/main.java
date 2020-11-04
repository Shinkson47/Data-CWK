package main;

import lib.collection.pool.StudentPool;
import lib.student.Student;

import java.util.ArrayList;

/**
 * <h1></h1>
 * <br>
 * <p>
 *
 * </p>
 *
 * @author <a href="https://www.shinkson47.in">Jordan T. Gray on 03/11/2020</a>
 * @version 1
 * @since v1
 */
public class main {
    public static void main(String[] args) {

        //#region Populate student pool.
        // Level 6
        Student mentor =
        new Student("P2531111");
        new Student("P2530001");

        // Level 5
        Student mentee =
        new Student("P2531120");
        new Student("P2532211");
        new Student("P2534141");

        // Unassigned level 5
        new Student("P2530230");

        // Level 4
        new Student("P2530201");
        new Student("P2530150");
        new Student("P2530190");
        new Student("P2530210");
        new Student("P2530250");
        //#endregion

        //TODO

        //STOPSHIP

        //• Assign student mentors to mentees.
        //• Reassign mentors
        mentee.setMentor(mentor);

        //• determine if the mentor has been assigned
        boolean hasMentor = mentee.hasMentor();

        //• Reassign mentees
        Student newMentor = StudentPool.Global.findByP("P2531120");
        mentor.transferMenteesTo(newMentor);

        //• Find a mentor for a given mentee
        mentor = mentee.getMentor();

        //• Remove mentors
        mentor.delete();

        //• Remove mentees at any level of study
        mentee.delete();

        //• Find all the mentees for the given mentor
        ArrayList<Student> mentees = mentor.getMentees();

        //• Let the user search for a mentor
        Student locatedMentor = StudentPool.Global.findMentorByP("P2531111");

        //• Let the user search for a mentee.
        Student locatedMentee = StudentPool.Global.findMenteeByP("P2531120");

    }
}
