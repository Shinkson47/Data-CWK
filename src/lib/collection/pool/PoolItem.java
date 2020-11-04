package lib.collection.pool;

/**
 * <h1>Pool Item interface contract.</h1>
 * <br>
 * <p>
 * An item which <u>will</u>:<br>
 *     - add itself to the appropriate pool upon instantiation<br>
 *     - remove itself upon deconstruction, or on request.
 * </p>
 * @implSpec Not in the interface, but is required for a Pool item: <br>
 *           <init> MUST implement "Pool.registerSelf(this);", where Pool is the
 *           designated pool for the object. This implementation
 *           is the basis for automatically adding objects to pools.
 * @author <a href="https://www.shinkson47.in">Jordan T. Gray on 03/11/2020</a>
 * @version 1
 * @since v1
 */
public interface PoolItem{

    /**
     * <h2>Removes this PoolItem from its designated pool.</h2>
     */
    void deregisterSelf();
}
