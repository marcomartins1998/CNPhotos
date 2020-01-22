import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.pubsub.v1.PubsubMessage;

public class CpuMemMetricReceiveHandler implements MessageReceiver {
    private final InstanceGroupMetrics igm;

    public CpuMemMetricReceiveHandler(InstanceGroupMetrics igm){
        this.igm = igm;
    }

    public void receiveMessage(PubsubMessage pubsubMessage, AckReplyConsumer ackReplyConsumer){
        String msg = pubsubMessage.getData().toStringUtf8();
        String instanceName = msg.split(":")[0];
        double cpuValue = Double.parseDouble(msg.split(":")[1]);
        double memValue = Double.parseDouble(msg.split(":")[2]);
        this.igm.updateInstanceCpuMemUsage(instanceName, cpuValue, memValue);
        ackReplyConsumer.ack();
    }
}
