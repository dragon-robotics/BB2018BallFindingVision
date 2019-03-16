import edu.wpi.first.wpilibj.networktables.*;

/**
 * This abstract class implements what we should do when writing to network tables
 * after finding balls.  It is abstract so that the keys can be named within
 * a specific implementation, presumably to identify the ball's color.
 * 
 * @author Chuck Benedict, Mentor, Team 997
 */
public abstract class NetworkTableWriter implements INetworkTableWriter
{

    NetworkTable publishingTable;

    /**
     * A network table writer needs an interpreter object to determine what has been found
     * from a process pipeline and then a network table to write to.  We send these external
     * dependencies into this class becuase this class only care about writing results
     * out to network tables, not the pre-steps requied to get there.
     * 
     * @param publishingTable   An instantiated network table that interpreted data will get written to
     */
    public NetworkTableWriter(NetworkTable publishingTable) {
        this.publishingTable = publishingTable;
    }
}