package lib.collection.nodes;

import java.util.ArrayList;

/**
 * <h1>A Bi-Directional node with one parent, and multiple children</h1>
 * <br>
 * <p>
 * This collective Node creates a one-to-many relationship between a parenting BiNode
 * and one or many children BiNodes.
 * <br>
 * It also deprecates the use of {@link LinearNode#setNext} and {@link LinearNode#getNext()},
 * since a single next element is of no use in a one to many. Instead, this class keeps an {@link ArrayList}
 * of <i>children</i>.
 * </p>
 *
 * @author <a href="https://www.shinkson47.in">Jordan T. Gray on 03/11/2020</a>
 * @version 1
 * @since v1
 */
public class CollectiveNode<T extends BiNode> extends BiNode<CollectiveNode> {

    /**
     * <h2>All children BiNodes</h2>
     */
    private ArrayList<T> children = new ArrayList<T>();

    /**
     * <h2>Get the working copy of all children</h2>
     * @apiNote <blockquote>
     *     For the sake of efficiency, this does not create a clone.
     *     The intended use is for searching or getting; <b><i><u>NOT</u> modifying!</i></b>
     *
     *     <br><br>
     *     <b><i>Directly changing data in the list returned <u>WILL</u> cause unintended side effects!</i></b>
     *     <br>
     * @implSpec The returned list must be treated as read-only.
     * </blockquote>
     * @return the working {@link ArrayList} instance of <i>children</i> nodes
     */
    public ArrayList<T> getChildren() {
        return children;
    }

    /**
     * {@inheritDoc}
     *
     * BiNode Override. If any children are assigned, returns the first. Otherwise null.
     *
     * @deprecated since collectiveNode revolves around collections of children, not 'Next'
     * @return the child found at index 0, null if there are no children.
     * @implNote May be null if there is no children.
     */
    @Deprecated
    @Override
    public LinearNode<BiNode<CollectiveNode>> getNext() {
        return (hasChildren()) ? getChildren().get(0) : null;
    }

    /**
     * Returns the next element from the super BiNode.
     *
     * @deprecated since Collective Node supercedes with collections of next.
     * @return (BiNode) super.getNext.
     */
    @Deprecated
    public LinearNode<BiNode<CollectiveNode>> superGetNext(){
        return super.getNext();
    }


    /**
     * {@inheritDoc}
     * @param newNext the new link to store.
     */
    @Deprecated
    @Override
    public void setNext(LinearNode<BiNode<CollectiveNode>> newNext) {
        super.setNext(newNext);
    }

    /**
     * <h2>Add a new child node</h2>
     * Adds a new child to this collection, changing it's last node to be this node instance;
     * thus declaring to the new child that this is the new parent.<br>
     * Silently rejects call if newChild is null.
     * @param newChild The child to add as a child.
     */
    public void addChild(T newChild){
        if (newChild == null) return;
        children.add(newChild);
        newChild.setLast(this);
    }

    /**
     * <h2>Has more than zero children?</h2>
     * @return true of children.size > 0
     */
    protected boolean hasChildren() {
        return children.size() > 0;
    }

    /**
     * <h2>Breaks down all node link relations</h2>
     * Clears this as being the parent of all children, then removes such that they're no longer children.<br>
     * Clears parent.
     * @implNote For safety, this also clears super(BiNode).next, even though next isn't intended to be used on a collective node.
     */
    public void decompose(){
        if (hasChildren())                                                                                              // Break links to all child nodes
            children.forEach(s -> {
                s.clearLast();
                children.remove(s);
            });

        if (hasNext())                                                                                                  // There shouldn't be any active link via next on a collective node, but just in case.
            clearNext();

        if (hasLast())                                                                                                  // Break links to parent
            clearLast();
    }
}
