import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import monitorstubs.*;

import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class MonitorService extends MonitorServiceGrpc.MonitorServiceImplBase{
    public final LimitMetrics limitMetrics;
    public final Object limitMutex;
    public final InstanceGroupMetrics instanceGroupMetrics;
    public final ScheduledExecutorService ses;
    public ScheduledFuture sf;
    public final Runnable task;

    MonitorService(LimitMetrics limitMetrics,
                   Object limitMutex,
                   InstanceGroupMetrics instanceGroupMetrics,
                   ScheduledExecutorService ses,
                   ScheduledFuture<?> sf,
                   Runnable task){
        this.limitMetrics = limitMetrics;
        this.limitMutex = limitMutex;
        this.instanceGroupMetrics = instanceGroupMetrics;
        this.ses = ses;
        this.sf = sf;
        this.task = task;
    }

    @Override
    public void getInstanceNum(Empty request, StreamObserver<InstanceNumber> responseObserver) {
        responseObserver.onNext(InstanceNumber
                .newBuilder()
                .setSize(this.instanceGroupMetrics.getGroupSize())
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void getGlobalStatus(Empty request, StreamObserver<GlobalStatus> responseObserver) {
        Map<String, Double> globalMetrics = this.instanceGroupMetrics.getGlobalMetrics();
        responseObserver.onNext(GlobalStatus
                .newBuilder()
                .setCpuUsage(globalMetrics.get("cpu"))
                .setMemUsage(globalMetrics.get("mem"))
                .setImgPerSec(globalMetrics.get("app"))
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void setCpuLimit(MetricLimit request, StreamObserver<Empty> responseObserver) { synchronized (limitMutex){
        this.limitMetrics.CPU_LIMIT[0] = request.getLowLimit();
        this.limitMetrics.CPU_LIMIT[1] = request.getHighLimit();
        responseObserver.onCompleted();
    }}

    @Override
    public void setMemLimit(MetricLimit request, StreamObserver<Empty> responseObserver) { synchronized (limitMutex){
        this.limitMetrics.MEM_LIMIT[0] = request.getLowLimit();
        this.limitMetrics.MEM_LIMIT[1] = request.getHighLimit();
        responseObserver.onCompleted();
    }}

    @Override
    public void setImgPerSecLimit(MetricLimit request, StreamObserver<Empty> responseObserver) { synchronized (limitMutex){
        this.limitMetrics.IMG_PER_SEC_LIMIT[0] = request.getLowLimit();
        this.limitMetrics.IMG_PER_SEC_LIMIT[1] = request.getHighLimit();
        responseObserver.onCompleted();
    }}

    @Override
    public synchronized void setActionPeriod(ActionPeriod request, StreamObserver<Empty> responseObserver) {
        synchronized (limitMutex){
            this.limitMetrics.ACTION_PERIOD_LIMIT = request.getVal();
        }
        this.sf.cancel(true);
        this.sf = ses.scheduleAtFixedRate(task, 0, request.getVal(), TimeUnit.SECONDS);
        responseObserver.onCompleted();
    }
}
