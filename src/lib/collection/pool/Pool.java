package lib.collection.pool;

import java.util.ArrayList;

/**
 * <h1>A Collection of objects created globally</h1>
 * <br>
 * <p>
 * Where the contents are not manually added or specified, but rather objects add themselves to a specified
 * pool automatically upon creation
 * </p>
 *
 * @author <a href="https://www.shinkson47.in">Jordan T. Gray on 03/11/2020</a>
 * @version 1
 * @since v1
 */
public abstract class Pool<T extends PoolItem> extends ArrayList<T> {

    /**
     * <h2>Adds object to the pool</h2>
     * Pool Items automatically call this method when instantiated, parsing themselves.
     * @param self
     */
    public void registerSelf(T self){
        super.add(self);
    }

    /**
     * <h2>Removes the object from the pool</h2>
     * Pool Items automatically call this when requested. Since
     * Java is a garbage collected lanuage, there's no default deconstructor - but if there was, this'd be in it!
     * @param self object instance to remove from the pool.
     */
    public void removeSelf(T self){
        super.remove(self);
    }


    /**
     * @deprecated The contents of pools should not be manually appended to.
     */
    @Deprecated
    @Override
    public boolean add(T t) {
        return super.add(t);
    }
}
