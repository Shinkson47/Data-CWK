package lib.student;

import lib.collection.nodes.CollectiveNode;
import lib.collection.pool.CollectiveNodePoolItem;
import lib.collection.pool.StudentPool;

import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * <h1>Representation of a student with respect to student mentors.</h1>
 * <br>
 * This class stores a students' P Number only. No other student data is stored here.
 * <p>
 * Uses extension of collective node to create a one to many relationship between itself and many other students.
 * Super is the representation of mentees, and mentor; where super#last is the mentor, and super#children are mentees.
 * </p>
 *
 * @author <a href="https://www.shinkson47.in">Jordan T. Gray on 03/11/2020</a>
 * @version 1
 * @since v1
 */
public class Student extends CollectiveNodePoolItem<Student, StudentPool> {

    //#region fields
    /**
     * The local student identification in the DMU standard; (P/X)#######
     * @see Student#isValidPNumber(String);
     */
    private String pNumber;

    //#endregion fields

    //#region constructor
    public Student(String pNumber) {
        super(StudentPool.Global);
        setpNumber(pNumber);
    }

    //#endregion constructor

    //#region get/set

    /**
     * @return The academic ID of this student.
     */
    public String getpNumber() {
        return pNumber;
    }

    /**
     * Sets the academic ID of this student.<br>
     * Protected; changing of ID is not considered a public feature.
     * @param pNumber The new ID of this student.
     */
    protected void setpNumber(String pNumber) {
        this.pNumber = pNumber;
    }

    /**
     * <h2>Creates a Mentor/Mentee relationship with the provided Mentor.</h2>
     * Overwrites 'Last' with the new Mentor, then adds self as a mentee within the mentor.
     * @param s
     */
    public void setMentor(Student s){
        super.setLast(s);                           // Overwrites last element, and replaces the mentor's 'Next' to be this.
        super.addChild((Student) super.getNext());  // 'Next' is for singular relationships, not collective; we need to add the Next as a child,
        super.clearNext();                          // Then remove it.
    }

    /**
     * @return The Student Mentor of this student. May be null if there's no mentor.
     */
    public Student getMentor(){
        return (Student) super.getLast();
    }

    /**
     * <h2>Returns the working list of mentees.</h2>
     * {@inheritDoc}
     */
    public ArrayList<Student> getMentees(){
        return super.getChildren();
    }
    //#endregion


    /**
     * <h2>Creates a Mentee/Mentor relationship with a new mentee.</h2>
     * Adds mentee as a child, then sets the mentee's mentor to this.
     * @param s The new mentee.
     */
    public void addMentee(Student s){
        super.addChild(s);
    }

    /**
     * <h2>Adds a collection of students using {@link Student#addMentee}</h2>
     * @param s An array list of students.
     */
    public void addMentees(ArrayList<Student> s){
        s.forEach(this::addMentee);
    }

    /**
     * <h2>Adds a collection of students using {@link Student#addMentees(ArrayList)}</h2>
     * @param s
     */
    public void addMentees(Student... s){
        addMentees((ArrayList<Student>) Arrays.asList(s));
    }

    /**
     * <h2>Determines if this student has a mentor.</h2>
     * @return true if super {@link CollectiveNode#getLast()} is not null.
     */
    public boolean hasMentor(){
        return super.hasLast();
    }

    /**
     * <h2>Breaks down the relationship between this student and it's mentor.</h2>
     * <br>
     * @apiNote Call is silently rejected if this student has no mentor.
     * Removes self as a mentee from current mentor, then removes mentor from self.
     */
    public void clearMentor(){
        if (getLast() == null) return;
        getMentor().getMentees().remove(this); // TODO test
        super.clearLast();
    }

    /**
     * <h2>Breaks down the relationships between this student and all of its mentors, using {@link Student#clearMentee(Student)}</h2>
     */
    public void clearMentees(){
        getMentees().forEach(this::clearMentee);
    }

    /**
     * <h2>Breaks down then relationship between this student and one of it's mentees.</h2>
     * @param student The mentee to remove. Must not be null.
     */
    private void clearMentee(Student student) {
        Objects.requireNonNull(student);
        student.clearMentor();
        getMentees().remove(student);
    }

    /**
     * <h2>Breaks down relationship with all mentees, then re-creates this relationship with a new mentor.</h2>
     * Rejects call if there is no children to transfer.
     * @param newMentor The student mentor to transfer mentees to.
     * @throws NullPointerException if newMentor is null.
     */
    public void transferMenteesTo(Student newMentor){
        Objects.requireNonNull(newMentor);
        if(!hasChildren()) return;
        getChildren().forEach(student -> student.setMentor(newMentor));
    }

    /**
     * <h2>Unenroll this student.</h2>
     * Decomposes all relationships this student has, then removes self from the public student pool,
     * then awaits garbage collection.
     * @implNote For garbage collection to function as expected, there must be no active pointer variables to this student.
     */
    public void delete(){
        decompose();
        deregisterSelf();
    }


    // TODO these're not in the UML.
    /**
     * <h2>Determines if this student is a mentee.</h2>
     * Since there is no definitive definition of the state of this student's menteeship, it's inherited from relationships.
     * @return true if the student has a mentor.
     */
    public boolean isMentee(){
        return hasMentor();
    }

    /**
     * <h2>Determines if the student is a mentor.</h2>
     * Since there is no definitive definition of the state of this student's mentorship, it's inherited from relationships.
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
     * <b>Raw REGEX: <i>P[0-9]*</i></b>
     * @param s A string to test.
     * @return true if it matches the regex.
     */
    public static boolean isValidPNumber(String s) {
        return s.matches("P[0-9]*");
    }




}
