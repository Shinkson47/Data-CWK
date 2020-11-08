package lib.student;

import lib.collection.pool.CollectiveNodePoolItem;
import lib.collection.pool.StudentPool;

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
public class Student extends CollectiveNodePoolItem<Student, StudentPool> {

    //#region fields
    private String pNumber;

    //#endregion fields

    public Student(String pNumber) {
        super(StudentPool.Global);
        setpNumber(pNumber);
    }

    public String getpNumber() {
        return pNumber;
    }

    public void setpNumber(String pNumber) {
        this.pNumber = pNumber;
    }

    public void setMentor(Student s){
        super.setLast(s);
        super.addChild((Student) super.getNext());
        super.clearNext();
    }

    public void addMentee(Student s){
        super.addChild(s);
    }

    public Student getMentor(){
        return (Student) super.getLast();
    }

    public ArrayList<Student> getMentees(){
        return super.getChildren();
    }

    public boolean hasMentor(){
        return super.hasLast();
    }

    public void transferMenteesTo(Student newMentor){
        if(!hasChildren()) return;

        getChildren().forEach(student -> student.setMentor(newMentor));
    }

    public void delete(){
        decompose();
        deregisterSelf();
    }


    // TODO these're not in the UML.
    /**
     * <h2>Determines if this student is a mentee.</h2>
     * @return true if the student has a mentor.
     */
    public boolean isMentee(){
        return hasMentor();
    }

    /**
     * <h2>Determines if the student is a mentor.</h2>
     * @return true if the student has more than zero mentees.
     */
    public boolean isMentor(){
        return getMentees().size() > 0;
    }

    /**
     * <h2>Determines if a string may be concidered a valid p Number.</h2>
     * To be valid, it <i>s</i> must;
     * <blockquote>
     *     - Start with a capital '<b>P</b>'<br>
     *     - Be followed ONLY by any quantity of numbers, between 0-9.
     *     <br><br> The following are all valid within this test; <br>
     * <list>
     *     <br>P1,
     *     <br>P01,
     *     <br>P1455847776,
     *     <br>P2540338
     * </list>
     * </blockquote>
     *
     * <b>Raw REGEX: P[0-9]*</b>
     * @param s A string to test.
     * @return true if it matches the regex.
     */
    public static boolean isValidPNumber(String s) {
        return s.matches("P[0-9]*");
    }

}
