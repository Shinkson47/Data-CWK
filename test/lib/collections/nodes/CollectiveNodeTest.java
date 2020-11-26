package lib.collections.nodes;


import lib.collection.nodes.CollectiveNode;
import lib.student.Student;
import org.junit.Assert;
import org.junit.Test;

import javax.rmi.CORBA.Stub;

public class CollectiveNodeTest {

    @Test
    public static void testAddChild(){}


    @Test
    public static void testSetLast(){}

    /**
     * <h2>Tests the Collective Node's ability to decompose relationships with all of its children.</h2>
     */
    @Test
    public static void testDecomposeSelf(){
        CollectiveNode<Student> node = new CollectiveNode<>();

        Student s1 = new Student("");
        Student s2 = new Student("");
        Student s3 = new Student("");

        node.addChild(s1);
        node.addChild(s2);
        node.addChild(s3);

        Assert.assertEquals(3, node.getChildren().size());

    }



}