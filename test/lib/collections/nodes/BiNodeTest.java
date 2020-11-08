package lib.collections.nodes;

import lib.collection.nodes.BiNode;
import lib.collection.nodes.CollectiveNode;
import lib.student.Student;
import org.junit.Assert;
import org.junit.Test;
import sun.jvm.hotspot.runtime.StubRoutines;

import java.sql.Struct;

/**
 * <h1>Uses a CollectiveNode implementation to test the BiNode abstract.</h1>
 * <br>
 * <p>
 *
 * </p>
 *
 * @author <a href="https://www.shinkson47.in">Jordan T. Gray on 07/11/2020</a>
 * @version 1
 * @since v1
 */
public class BiNodeTest {

    //#region util
    private CollectiveNode createNodeWithLast(){
        return createNodeWithLast(new CollectiveNode<Student>());
    }

    private CollectiveNode createNodeWithLast(CollectiveNode last){
        CollectiveNode node = new CollectiveNode<Student>();
        node.setLast(last);
        return node;
    }
    //#endregion util

    /**
     * Tests that the last node can be set correctly.
     */
    @Test
    public void testSetLast(){
        CollectiveNode testLast = new CollectiveNode<Student>();
        CollectiveNode testResult = createNodeWithLast(testLast);
        Assert.assertEquals(testLast, testResult);
    }

    @Test
    public void testClearLast(){
        CollectiveNode node = createNodeWithLast();
        node.clearLast();
        Assert.assertNull(node.getLast());
    }

    @Test
    public void testOverwriteLast(){
       CollectiveNode node = createNodeWithLast();
        CollectiveNode newLast = new CollectiveNode<Student>();

        node.setLast(newLast);                                      // Expected to set the last node, and to correctly set 'next' on the last to 'node'
        node.setLast(newLast);                                      // Call should be rejected, but this can't be tested for here. Just have to watch it in the debugger.
        Assert.assertEquals(newLast, node.getLast());               // Test the last node has been overwritten with newLast.
        Assert.assertEquals(node, newLast.superGetNext());          // Test that the newLast node has had it's 'next' correctly set.

        node.setNext(null);                                         // Shouldn't typically be used. Superseded by clearNext().
        Assert.assertNull(node.getNext());                          // Ensure the next was set.
        Assert.assertNull(newLast.getNext());                       // Ensure the 'next' on the previous node was voided.

        CollectiveNode newNewLast = new CollectiveNode<Student>();  // Another new node.
        node.setLast(newNewLast);                                   // Change from null to the new node.
        Assert.assertEquals(newNewLast, node.getLast());            // Ensure the last node has changed.
        Assert.assertNotNull(newNewLast.superGetNext());            // Ensure the next was set.
        node.setLast(null);
    }

}
