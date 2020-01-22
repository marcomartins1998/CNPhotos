import com.google.api.core.ApiFuture;
import com.google.cloud.pubsub.v1.*;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.*;

import java.io.IOException;

public class TopicHelper {
    private final String projectId;
    private TopicAdminClient topicAdmin;
    private SubscriptionAdminClient subscriptionAdmin;

    TopicHelper(String projectId) throws IOException {
        this.projectId = projectId;
        this.topicAdmin = TopicAdminClient.create();
        this.subscriptionAdmin = SubscriptionAdminClient.create();
    }

    public Topic createTopic(String topicName){
        ProjectTopicName tName = ProjectTopicName.of(projectId, topicName);
        return topicAdmin.createTopic(tName);
    }

    public Iterable<Topic> listTopics(){
        TopicAdminClient.ListTopicsPagedResponse res = topicAdmin.listTopics(ProjectName.of(projectId));
        return res.iterateAll();
    }

    public void publishTopicMsg(String topicName, String txtMsg){
        ProjectTopicName tName = ProjectTopicName.of(projectId, topicName);
        try {
            Publisher publisher = Publisher.newBuilder(tName).build();
            ByteString msgData = ByteString.copyFromUtf8(txtMsg);
            PubsubMessage pubsubMessage = PubsubMessage.newBuilder()
                    .setData(msgData)
                    //Optional
                    //.putAttributes("key1", "value1")
                    .build();
            ApiFuture<String> future = publisher.publish(pubsubMessage);
            String msgID = future.get();
            System.out.println("Message Published with ID=" + msgID);
            publisher.shutdown();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Subscription createSubscription(String topicName, String subName){
        ProjectTopicName tName = ProjectTopicName.of(projectId, topicName);
        ProjectSubscriptionName subscriptionName = ProjectSubscriptionName.of(projectId, subName);
        try {
            PushConfig pconfig = PushConfig.getDefaultInstance();
            Subscription subscription = subscriptionAdmin.createSubscription(subscriptionName, tName, pconfig,0);
            return subscription;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Iterable<Subscription> listSubscriptions(){
        ProjectName projectName = ProjectName.of(projectId);
        return subscriptionAdmin.listSubscriptions(projectName).iterateAll();
    }

    public void createSubscriber(String subscriptionName, MessageReceiver messageReceiver){
        ProjectSubscriptionName projectSubscriptionName = ProjectSubscriptionName.of(projectId, subscriptionName);
        Subscriber subscriber = Subscriber.newBuilder(projectSubscriptionName, messageReceiver).build();
        subscriber.startAsync().awaitRunning();
    }
}
