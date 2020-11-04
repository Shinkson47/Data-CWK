package lib.collection.pool;

import lib.collection.nodes.BiNode;
import lib.collection.nodes.CollectiveNode;

/**
 * <h1>CollectiveNode & PoolItem aggregate</h1>
 * <br>
 * <p>
 * Creates a collective node that may be stored within a pool.
 * </p>
 *
 * @author <a href="https://www.shinkson47.in">Jordan T. Gray on 03/11/2020</a>
 * @version 1
 * @since v1
 */
public class CollectiveNodePoolItem<T extends BiNode, PoolType extends Pool> extends CollectiveNode<T> implements PoolItem{

    /**
     * <h2>Local reference to the pool this is to be stored in.</h2>
     */
    protected PoolType pool;

    /**
     * <h2>Creates a new CollectiveNode, then adds it to the specified pool</h2>
     * @param _pool the pool instance this is to be added to.
     */
    public CollectiveNodePoolItem(PoolType _pool){
        pool = _pool;                               // Store local reference to linked pool.
        pool.registerSelf(this);                    // Add self to pool.
    }

    /**
     * <h2>Removes self from the pool linked on creation.</h2>
     */
    public void deregisterSelf(){
        pool.removeSelf(this);
    }
}
