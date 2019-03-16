import edu.wpi.first.wpilibj.networktables.*;

/**
 * Factory class to wire up a blue ball image processor
 * with all the correct dependencies.
 */
public class TargetContourImageProcessorFactory {
  /**
   * Static helper to create an image processor instance.
   * @param networkTable  The network table to write to
   * @return
   */
    public static ContourImageProcessor CreateImageProcessor(NetworkTable networkTable) {
        IContourPipeline targetContourPipeline = new TargetContourPipeline();
        return 
          new ContourImageProcessor(
            targetContourPipeline, 
            new TargetContourNetworkTableWriter(
              new ContourPipelineInterpreter(targetContourPipeline), 
              networkTable));
    }
}