package main;

import lib.collection.pool.StudentPool;
import lib.student.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <h1>Mentee / Mentor operation demonstration source.</h1>
 * <br>
 * <p>
 *  Demonstrates creating and operating on a mentor / mentee demonstration.
 * </p>
 * @author <a href="https://www.shinkson47.in">Jordan T. Gray on 03/11/2020</a>
 * @version 1
 * @since v1
 */
public class OperationImplDemo {
    // STOPSHIP : 8/11/2020 ; For demonstration, not distribution.

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

        /*
            This call creates all students from a list of p numbers, collecting them in the global student pool,
            with complete ignorance to relationships, which will be defined later.
         */
        StudentPool.createManyStudents(exampleStudents);

        // Get one mentor / mentee relationship pair for demonstration.
        Student mentee = StudentPool.Global.findByP("P2531120");
        Student mentor = StudentPool.Global.findByP("P2530001");                                                     // This isn't the mentor defined in the spec for 2531120, but it'll be reassigned to correct it later. This is just for demonstration.

        //• Assign student mentors to mentees.
        //• Can be used to re-assign a new mentor.
        mentee.setMentor(mentor);

        //• determine if the mentor has been assigned
        boolean hasMentor = mentee.hasMentor();

        //• Reassign mentors
        //• Reassign mentees
        Student newMentor = StudentPool.Global.findByP("P2531111");                                                  // This is the correct mentor for mentee 2531120
        mentor.transferMenteesTo(newMentor);      // FIXME i don't think this works correctly. Need to test.

        //• Find a mentor for a given mentee
        mentor = mentee.getMentor();

        //• Remove mentors
        /*
          This call decomposes the mentors relationships, and
          removes the mentor from the pool.
         */
        mentor.delete();

        //• Remove mentees at any level of study
        /*
          Again, decomposes relationships and removes from the pool.
         */
        mentee.delete();

        //• Find all the mentees for the given mentor
        ArrayList<Student> mentees = mentor.getMentees();

        // Search for any student. Finds a matching student, else returns null.
        Student student = StudentPool.Global.findByP("P2534141");

        //• Let the user search for a mentor
        // Uses findByP, but only returns the matching student if they're a mentor.
        Student locatedMentor = StudentPool.Global.findMentorByP("P2531111");

        //• Let the user search for a mentee.
        // Uses findByP, but only returns the matching student if they're a mentee.
        Student locatedMentee = StudentPool.Global.findMenteeByP("P2531120");


        /*
         Define all relationships declared within the specification.

         Given the consistency with this call, this could be easily automated in some way to define all students and relationships in memory,
         i.e from a saved file, if writing students to disk is ever implemented.
         */

        // Level 6
            // P2531111, Two mentees.
            StudentPool.Global.setRelationship("P2531111", "P25301120");
            StudentPool.Global.setRelationship("P2531111", "P25302211");

            // P2530001, Two mentees
            StudentPool.Global.setRelationship("P2530001", "P2534141");
            StudentPool.Global.setRelationship("P2530001", "P2530230");

        // Level 5
            // P2531120, One mentee
            StudentPool.Global.setRelationship("P2531120", "P2530201");

            // P2530150, Three mentees
            StudentPool.Global.setRelationship("P2532211", "P2530150");
            StudentPool.Global.setRelationship("P2532211", "P2530190");
            StudentPool.Global.setRelationship("P2532211", "P2530210");

            // P2534141, Two mentees.
            StudentPool.Global.setRelationship("P2534141", "P2530229");
            StudentPool.Global.setRelationship("P2534141", "P2530250");
    }
}
