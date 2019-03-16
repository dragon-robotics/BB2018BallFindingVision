import edu.wpi.first.wpilibj.networktables.*;

/**
 * Factory class to wire up a blue ball image processor
 * with all the correct dependencies.
 */
public class RedBallImageProcessorFactory {
  /**
   * Static helper to create an image processor instance.
   * @param networkTable  The network table to write to
   * @return
   */
  public static BallImageProcessor CreateImageProcessor(NetworkTable networkTable) {
        IBallPipeline redBallPipeline = new RedBallPipeline();
        return 
          new BallImageProcessor(
            redBallPipeline, 
            new RedBallNetworkTableWriter(
              new BallPipelineInterpreter(redBallPipeline), 
              networkTable));    
    }
}