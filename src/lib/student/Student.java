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


}
