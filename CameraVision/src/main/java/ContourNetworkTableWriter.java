import edu.wpi.first.wpilibj.networktables.*;

/**
 * This abstract class implements what we should do when writing to network tables
 * after finding balls.  It is abstract so that the keys can be named within
 * a specific implementation, presumably to identify the ball's color.
 * 
 * @author Chuck Benedict, Mentor, Team 997
 */
public abstract class ContourNetworkTableWriter extends NetworkTableWriter
{
    ContourPipelineInterpreter interpreter;
    NetworkTable publishingTable;

    /**
     * A network table writer needs an interpreter object to determine what has been
     * found from a process pipeline and then a network table to write to. We send
     * these external dependencies into this class becuase this class only care
     * about writing results out to network tables, not the pre-steps requied to get
     * there.
     * 
     * @param interpreter       The interpreter class that converts contour results to interpreted data
     * @param publishingTable   An instantiated network table that interpreted data will get written to
     */
    public ContourNetworkTableWriter(ContourPipelineInterpreter interpreter, NetworkTable publishingTable) {
        super(publishingTable);
        this.interpreter = interpreter;
        this.publishingTable = publishingTable;
    }

    /**
     * Write the values to the network table sent into the class constructor.
     */
    public void write() {
        publishingTable.putBoolean(getContourFoundKey(), interpreter.contoursFound());
        publishingTable.putNumber(getContourCountKey(), interpreter.contoursCount());
    }

    /**
     * Implement a unique key name of the value to be written to network tables for the
     * ball being found on a frame.
     */
    public abstract String getContourFoundKey();

    /**
     * Implement a unique key name of the count to be written to network tables for the
     * number of balls found on the frame.
     */
    public abstract String getContourCountKey();

}