import java.util.ArrayList;

import org.opencv.core.*;

interface IContourPipeline {
    public void process(Mat source0);
    public ArrayList<MatOfPoint> findContoursOutput();
    public ArrayList<MatOfPoint> filterContoursOutput();
    public String getColor();
}