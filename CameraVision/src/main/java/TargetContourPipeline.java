import java.util.ArrayList;

import org.opencv.core.*;

public class TargetContourPipeline implements IContourPipeline {
    private TargetContourGripPipeline pipeline = new TargetContourGripPipeline();

    public void process(Mat source0) {
        pipeline.process(source0);
    }

    public ArrayList<MatOfPoint> findContoursOutput() {
        return pipeline.findContoursOutput();
    }

    public String getColor() {
        return "Green";
    }
}