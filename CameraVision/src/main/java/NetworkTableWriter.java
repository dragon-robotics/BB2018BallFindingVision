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
    // ContourPipelineInterpreter contourInterpreter;
    // BallPipelineInterpreter ballInterpreter;
    NetworkTable publishingTable;

    /**
     * A network table writer needs an interpreter object to determine what has been found
     * from a process pipeline and then a network table to write to.  We send these external
     * dependencies into this class becuase this class only care about writing results
     * out to network tables, not the pre-steps requied to get there.
     * 
     * @param ballInterpreter   The interpreter class that converts blob results to interpreted data
     * @param publishingTable   An instantiated network table that interpreted data will get written to
     */
    public NetworkTableWriter(NetworkTable publishingTable) {
        this.publishingTable = publishingTable;
    }

    // public void write() {
    //     if(ballInterpreter != null){
    //         publishingTable.putBoolean(getBallFoundKey(), ballInterpreter.ballsFound());
    //         publishingTable.putNumber(getBallCountKey(), ballInterpreter.ballCount());
    //     }
    //     else{
    //         publishingTable.putBoolean(getContourFoundKey(), contourInterpreter.contoursFound());
    //         publishingTable.putNumber(getContourCountKey(), contourInterpreter.contoursCount());
    //     }
    // }

    /**
     * Implement a unique key name of the value to be written to network tables for the
     * ball being found on a frame.
     */
    // public abstract String getBallFoundKey();

    /**
     * Implement a unique key name of the count to be written to network tables for the
     * number of balls found on the frame.
     */
    // public abstract String getBallCountKey();

    /**
     * Implement a unique key name of the value to be written to network tables for the
     * ball being found on a frame.
     */
    // public abstract String getContourFoundKey();

    /**
     * Implement a unique key name of the count to be written to network tables for the
     * number of balls found on the frame.
     */
    // public abstract String getContourCountKey();

}