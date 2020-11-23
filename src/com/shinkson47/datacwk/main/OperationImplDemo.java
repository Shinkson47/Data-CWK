package com.shinkson47.datacwk.main;

import com.shinkson47.datacwk.lib.collection.pool.StudentPool;
import com.shinkson47.datacwk.lib.student.Student;
import jdk.nashorn.internal.objects.Global;

import java.util.ArrayList;

/**
 * <h1>Non graphical operation implementation demo</h1>
 * <br>
 *      <blockquote>
 *     <u>
 *         <i>
 *             This class does not accept any input, or provide any output. see {@link GUIDemo}
 *         </i>
 *     </u>
 *     </blockquote>
 * <br><br>
 * <p>
 *  Source which shows how operations may be performed on the data structure. <br>
 *  for an interactive structure utility, execute the main found in {@link GUIDemo}
 * </p>
 * @author <a href="https://www.shinkson47.in">Jordan T. Gray on 03/11/2020</a>
 * @version 1
 * @since v1
 */
public class OperationImplDemo {
    // STOPSHIP : 8/11/2020 ; For demonstration, not distribution.

    public static void main(String[] args) {

        System.out.println("This class does not accept any input, or provide any output; it's purely a source demonstration, " +
                "showing how functions can be performed with code. ");

        System.out.println("see com.shinkson47.datacwk.main.GUIDemo for an interactive graphical tool.");
        /*
            This call creates all students from a list of p numbers, collecting them in the global student pool,
            with complete ignorance to relationships, which will be defined later.
         */
        StudentPool.createManyStudents(GlobalData.SPEC_TABLE_ONE);

        /*
         Define all relationships declared within the specification.

         Given the consistency with this call, this could be easily automated in some way to define all students and relationships in memory,
         i.e from a saved file, if writing students to disk is ever implemented.
         */
        GlobalData.CreateDefaultRelationships();

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
    }
}
