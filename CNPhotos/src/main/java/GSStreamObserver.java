import io.grpc.stub.StreamObserver;
import monitorstubs.GlobalStatus;

public class GSStreamObserver implements StreamObserver<GlobalStatus> {
    @Override
    public void onNext(GlobalStatus globalStatus) {
        System.out.println(
                "Current Global CPU usage: " + globalStatus.getCpuUsage() +
                "\nCurrent Global Memory usage: " + globalStatus.getMemUsage() +
                "\nCurrent Global Images per second: " + globalStatus.getImgPerSec()
        );
    }

    @Override
    public void onError(Throwable throwable) { System.out.println("Error ocurred in monitor: " + throwable.getMessage()); }

    @Override
    public void onCompleted() { }
}
