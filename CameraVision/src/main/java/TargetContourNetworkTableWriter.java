import edu.wpi.first.wpilibj.networktables.*;

/**
 * Specific implementation for writing red ball data to network tables.
 * 
 * @author Chuck Benedict, Mentor, Team 997
 */
public class TargetContourNetworkTableWriter extends ContourNetworkTableWriter {
    public TargetContourNetworkTableWriter(ContourPipelineInterpreter interpreter, NetworkTable publishingTable) {
        super(interpreter, publishingTable);
    }

    public String getContourFoundKey() {
        return "RedBallFound";
    }

    public String getContourCountKey() {
        return "RedBallCount";
    }
}