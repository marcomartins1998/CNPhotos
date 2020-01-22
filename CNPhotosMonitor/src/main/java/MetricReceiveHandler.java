import com.google.cloud.pubsub.v1.AckReplyConsumer;
import com.google.pubsub.v1.PubsubMessage;

public class MetricReceiveHandler {
    public MetricReceiveHandler(){

    }

    public void receiveMessage(PubsubMessage pubsubMessage, AckReplyConsumer ackReplyConsumer){
        ackReplyConsumer.ack();
    }
}
