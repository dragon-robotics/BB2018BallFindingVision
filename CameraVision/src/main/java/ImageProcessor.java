import org.opencv.core.*;
import org.opencv.features2d.Features2d;
import org.opencv.imgproc.Imgproc;
import java.util.concurrent.*;

/**
 * This class performs the processing and resulting action to a given
 * ball pipeline.
 */
public abstract class ImageProcessor {
    protected INetworkTableWriter networkTableWriter;
    protected ExecutorService executor = Executors.newSingleThreadExecutor();
    protected Mat outputImage = new Mat();
    protected Future<?> processAsyncFuture;

    /**
     * ImageProcessor requires a pipeline to process and a network table writer to write
     * results to.
     * @param pipeline              The pipeline to process
     * @param networkTableWriter    A network table writer to send results to
     */
    public ImageProcessor(INetworkTableWriter networkTableWriter) {
        this.networkTableWriter = networkTableWriter;
        this.processAsyncFuture = null;
    }

    /**
     * Process an image asynchronously.  Call awaitProcessCompletion to wait for completion.
     * You can only process one image at a time.
     * @param inputImage    The image to process
     */
    public abstract void processAsync(Mat inputImage);

    /**
     * Await an image process async call to finish.
     */
    public abstract void awaitProcessCompletion();

    /**
     * Annotate an image of found balls with circles and labels.
     * @param inputImage    The image to annotate.
     * @return              The annotated image.
     */
    public abstract Mat annotate(Mat inputImage);
}