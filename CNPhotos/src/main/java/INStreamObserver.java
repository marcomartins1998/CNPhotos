import io.grpc.stub.StreamObserver;
import monitorstubs.InstanceNumber;

public class INStreamObserver implements StreamObserver<InstanceNumber> {
    @Override
    public void onNext(InstanceNumber instanceNumber) { System.out.println("Number of active VMs: " + instanceNumber.getSize());}

    @Override
    public void onError(Throwable throwable) { System.out.println("Error ocurred in monitor: " + throwable.getMessage()); }

    @Override
    public void onCompleted() { }
}
