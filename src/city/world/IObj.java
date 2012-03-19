package city.world;

/**
 * An in game object.
 */
public interface IObj {
    public void accept (IObjVisitor visitor);
}
