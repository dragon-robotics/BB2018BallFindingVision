import org.opencv.core.*;
import org.opencv.features2d.Features2d;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * This class performs the processing and resulting action to a given
 * ball pipeline.
 */
public class ContourImageProcessor extends ImageProcessor {
    private IContourPipeline pipeline;

    /**
     * ImageProcessor requires a pipeline to process and a network table writer to write
     * results to.
     * @param pipeline              The pipeline to process
     * @param networkTableWriter    A network table writer to send results to
     */
    public ContourImageProcessor(IContourPipeline pipeline, INetworkTableWriter networkTableWriter) {
        super(networkTableWriter);
        if (pipeline == null) {
            throw new IllegalArgumentException();
        }
        this.pipeline = pipeline;
    }

    /**
     * Process an image asynchronously.  Call awaitProcessCompletion to wait for completion.
     * You can only process one image at a time.
     * @param inputImage    The image to process
     */
    public void processAsync(Mat inputImage)
    {
        if (processAsyncFuture != null) {
            throw new IllegalAccessError("Only one process can be awaited at a time.");            
        }
        // Hold on the the future...use the awaiter to wait for completion
        processAsyncFuture = executor.submit(() -> {
            // Apply the pipeline to the image.
            pipeline.process(inputImage);

            // Update network table
            networkTableWriter.write();
            return null;
        });
    }

    /**
     * Await an image process async call to finish.
     */
    public void awaitProcessCompletion() {
        try {
            processAsyncFuture.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            throw new IllegalAccessError("You must call processAsync first before awaiting completion.");
        }
        // Reset our future
        processAsyncFuture = null;
    }

    /**
     * Annotate an image of found balls with circles and labels.
     * @param inputImage    The image to annotate.
     * @return              The annotated image.
     */
    public Mat annotate(Mat inputImage) {
        // Write a processed image that you want to restream
        // This is a marked up image of what the camera sees
        
        // ArrayList<MatOfPoint> contours = pipeline.findContoursOutput();
        ArrayList<MatOfPoint> contours = pipeline.filterContoursOutput();

        for (MatOfPoint contour : contours){
            // Minimum size allowed for consideration
            MatOfPoint2f approxCurve = new MatOfPoint2f();
            MatOfPoint2f contour2f = new MatOfPoint2f(contour.toArray());
            //Processing on mMOP2f1 which is in type MatOfPoint2f
            double approxDistance = Imgproc.arcLength(contour2f, true)*0.02;
            Imgproc.approxPolyDP(contour2f, approxCurve, approxDistance, true);
    
            //Convert back to MatOfPoint
            MatOfPoint points = new MatOfPoint( approxCurve.toArray() );
    
            // Get bounding rect of contour
            Rect rect = Imgproc.boundingRect(points);
    
            // Ident contours on image
            Imgproc.rectangle(inputImage, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(255, 0, 0, 255), 3);
            Imgproc.putText(inputImage, pipeline.getColor(), new Point(rect.x, rect.y), Core.FONT_HERSHEY_COMPLEX_SMALL, .75, new Scalar(2,254,255));
        }

        return inputImage;
    }
}