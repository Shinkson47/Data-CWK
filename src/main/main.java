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

    /**
     * All students from the specification example.
     */
    public static String[] exampleStudents = new String[]{
            // Level 6
            "P2531111",
            "P2530001",

            // Level 5
            "P2531120",
            "P2532211",
            "P2534141",

            "P2530230",

            // Level 4
            "P2530201",
            "P2530150",
            "P2530190",
            "P2530210",
            "P2530250"
    };

    public static void main(String[] args) {

        // Define all students, ignoring relationships.
        StudentPool.createManyStudents(exampleStudents);

        // Get one mentor / mentee relationship pair.
        Student mentee = StudentPool.Global.findByP("P2531120");
        Student mentor = StudentPool.Global.findByP("P2530001");                                                     // This isn't the mentor defined in the spec for 2531120, but it'll be reassigned to correct it later.

        //• Assign student mentors to mentees.
        //• Reassign mentors
        mentee.setMentor(mentor);

        //• determine if the mentor has been assigned
        boolean hasMentor = mentee.hasMentor();

        //• Reassign mentees
        Student newMentor = StudentPool.Global.findByP("P2531111");                                                  // This is the correct mentor for mentee 2531120
        mentor.transferMenteesTo(newMentor);      // FIXME i dont think this works.

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



        // Define all relationships
        StudentPool.Global.setRelationship("P2531111", "P25301120");
        StudentPool.Global.setRelationship("P2531111", "P25302211");

        StudentPool.Global.setRelationship("P2530001", "P2534141");
        StudentPool.Global.setRelationship("P2530001", "P2530230");

        StudentPool.Global.setRelationship("P2531120", "P2530201");

        StudentPool.Global.setRelationship("P2532211", "P2530150");
        StudentPool.Global.setRelationship("P2532211", "P2530190");
        StudentPool.Global.setRelationship("P2532211", "P2530210");

        StudentPool.Global.setRelationship("P2534141", "P2530229");
        StudentPool.Global.setRelationship("P2534141", "P2530250");
    }
}
