package lib.collection.nodes;

/**
 * <h1>A linked list node</h1>
 * <br>
 * <p>
 * For a linear, single direction, linked list where items are nodes
 * which have a pointer to the next item in the linked list
 * <br>
 * This data structure is peer reliant, and non centralised,
 * meaning there is no central storage of all nodes for a specific list. A list is purely defined and
 * accessed via it's nodes.
 * </p>
 *
 * @author <a href="https://www.shinkson47.in">Jordan T. Gray on 03/11/2020</a>
 * @version 1
 * @since v1
 */
public abstract class LinearNode<T extends LinearNode> {

    //#region fields
    /**
     * <h2>The next item in the list.</h2>
     * May be null, indicating that this node is the <b>tail</b>.
     */
    private LinearNode<T> next;

    //#endregion fields

    //#region get/set
    /**
     * <h2>Get the next item in the linked list</h2>
     * @apiNote May be null if this is the tail of the list.
     * @return this.next
     */
    public LinearNode<T> getNext() {
        return next;
    }

    /**
     * <h2>Overwrite the current link</h2>
     * @apiNote No other nodes are notified of the change.
     * @param newNext the new link to store.
     */
    public void setNext(LinearNode<T> newNext) {
        this.next = newNext;
    }

    protected void clearNext() {
        setNext(null);
    }
    //#endregion

    /**
     * <h2>Has a link to the next node?</h2>
     * @return true if next is not null.
     */
    public boolean hasNext(){
        return next != null;
    }
}
