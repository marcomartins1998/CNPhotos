import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.cloud.pubsub.v1.MessageReceiver;
import com.google.pubsub.v1.PubsubMessage;

public class AppMetricReceiveHandler implements MessageReceiver {
    private final InstanceGroupMetrics igm;

    public AppMetricReceiveHandler(InstanceGroupMetrics igm){
        this.igm = igm;
    }

    public void receiveMessage(PubsubMessage pubsubMessage, AckReplyConsumer ackReplyConsumer){
        String msg = pubsubMessage.getData().toStringUtf8();
        String instanceName = msg.split(":")[0];
        double value = Double.parseDouble(msg.split(":")[1]);
        this.igm.updateInstanceImgPerSec(instanceName, value);
        ackReplyConsumer.ack();
    }
}
