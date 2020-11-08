package lib.collection.nodes;

/**
 * <h1>A Bi-Directional linked list node</h1>
 * <br>
 * <p>
 * For a linked list where items are nodes which have pointers
 * to the next and previous item in the list.
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
public abstract class BiNode<T extends BiNode> extends LinearNode<BiNode<T>> {

    //#region fields
    /**
     * <h2>The next last in the list.</h2>
     * May be null, indicating that this node is the <b>head</b>.
     */
    private BiNode<T> last;
    //#endregion fields


    //#region get/set

    /**
     * <h2>Get the previous item in the linked list</h2>
     * @apiNote May be null if this is the head of the list.
     * @return this.last
     */
    public BiNode<T> getLast() {
        return last;
    }

    /**
     * <h2>Overwrite the current <i>last</i> link</h2>
     * @apiNote No other nodes are notified of the change.
     * @param newLast the new link to store.
     */
    public void setLast(BiNode<T> newLast) {
        if (last != null) last.clearNext();
        last = newLast;
        if (last == null) return;
        last.setNext(this);
    }

    public void clearLast() {
        setLast(null);
    }



    //#endregion

    /**
     * <h2>Has a link to the previous node?</h2>
     * @return true if last is not null.
     */
    public boolean hasLast(){
        return getLast() != null;
    }
}
