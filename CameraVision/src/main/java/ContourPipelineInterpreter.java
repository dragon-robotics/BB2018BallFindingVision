/**
 * Interpret the result of the ball pipeline. This abstracts out the logic from
 * the pipeline class.
 *
 * @author Chuck Benedict, Mentor, Team 997
 */
public class ContourPipelineInterpreter {

    // Processed pipeline that we will do the interpretation against
    private IContourPipeline pipeline;

    /**
     * Constructor taking a processed pipeline
     *
     * @param pipeline A processed pipeline that returns blob found results
     */
    public ContourPipelineInterpreter(IContourPipeline pipeline) {
        if (pipeline == null) {
            throw new IllegalArgumentException("Pipline cannot be null.");
        }
        this.pipeline = pipeline;
    }

    /**
     * Did we find at least one contour on a processed frame?
     * 
     * @return True if at least one contour was found
     */
    public boolean contoursFound() {
        return !this.pipeline.findContoursOutput().isEmpty();
    }

    /**
     * Get the count of the number of contours found on a processed frame.
     * 
     * @return The count of the number of contours found
     */
    public long contoursCount() {
        return this.pipeline.findContoursOutput().size();
    }
}