import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;

public class EmptyStreamObserver implements StreamObserver<Empty> {
    @Override
    public void onNext(Empty empty) {}

    @Override
    public void onError(Throwable throwable) { System.out.println("Error ocurred in monitor: " + throwable.getMessage()); }

    @Override
    public void onCompleted() { System.out.println("Completed!\n"); }
}
