import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import org.jclouds.ContextBuilder;
import org.jclouds.openstack.marconi.v1.MarconiApi;
import org.jclouds.openstack.marconi.v1.domain.CreateMessage;
import org.jclouds.openstack.marconi.v1.domain.Message;
import org.jclouds.openstack.marconi.v1.domain.MessagesCreated;
import org.jclouds.openstack.marconi.v1.domain.Queue;
import org.jclouds.openstack.marconi.v1.features.ClaimApi;
import org.jclouds.openstack.marconi.v1.features.MessageApi;
import org.jclouds.openstack.marconi.v1.features.QueueApi;

import java.util.List;
import java.util.UUID;

public class CloudQueues {
    public static final String PROVIDER = System.getProperty("provider", "rackspace-cloudqueues-us");
    public static final String ZONE = System.getProperty("zone", "IAD");
    public static final String QUEUE_NAME = System.getProperty("queuename", "sample_queue");

    public static final String USERNAME = System.getProperty("username", "{username}");
    public static final String API_KEY = System.getProperty("apikey", "{apiKey}");
    public static final UUID CLIENT_ID = UUID.fromString(System.getProperty("uuid", "0128107c-f4cd-43d4-83df-1393cbefd987"));

    public static void main(String[] args) throws Exception {
        MarconiApi marconiApi = ContextBuilder.newBuilder(PROVIDER)
                .credentials(USERNAME, API_KEY)
                .buildApi(MarconiApi.class);

        createQueue(marconiApi);
        listQueues(marconiApi);
        postMessage(marconiApi);
        List<Message> messages = claimMessage(marconiApi);
        releaseMessage(marconiApi, messages);
        deleteMessage(marconiApi, messages.get(0).getId());
        deleteQueue(marconiApi);
    }

    private static void createQueue(MarconiApi marconiApi) {
        QueueApi queueApi = marconiApi.getQueueApiForZoneAndClient(ZONE, CLIENT_ID);
        queueApi.create(QUEUE_NAME);
    }

    private static List<Queue> listQueues(MarconiApi marconiApi) {
        QueueApi queueApi = marconiApi.getQueueApiForZoneAndClient(ZONE, CLIENT_ID);
        List<Queue> queues = queueApi.list(true).concat().toList();

        return queues;
    }

    private static MessagesCreated postMessage(MarconiApi marconiApi) {
        MessageApi messageApi = marconiApi.getMessageApiForZoneAndClientAndQueue(ZONE, CLIENT_ID, QUEUE_NAME);
        CreateMessage createMessage = CreateMessage.builder().ttl(900).body("{\"play\": \"hockey\"}").build();
        List<CreateMessage> createMessages = ImmutableList.of(createMessage);

        MessagesCreated messagesCreated = messageApi.create(createMessages);

        return messagesCreated;
    }

    private static List<Message> claimMessage(MarconiApi marconiApi) {
        ClaimApi claimApi = marconiApi.getClaimApiForZoneAndClientAndQueue(ZONE, CLIENT_ID, QUEUE_NAME);
        List<Message> messages = claimApi.claim(900, 120, 4);

        return messages;
    }

    private static void releaseMessage(MarconiApi marconiApi, List<Message> messages) {
        ClaimApi claimApi = marconiApi.getClaimApiForZoneAndClientAndQueue(ZONE, CLIENT_ID, QUEUE_NAME);
        claimApi.release(messages.get(0).getClaimId().get());
    }

    private static void deleteMessage(MarconiApi marconiApi, String messageId) {
        MessageApi messageApi = marconiApi.getMessageApiForZoneAndClientAndQueue(ZONE, CLIENT_ID, QUEUE_NAME);
        List<String> messageIds = ImmutableList.of(messageId);

        messageApi.delete(messageIds);
    }

    private static void deleteQueue(MarconiApi marconiApi) {
        QueueApi queueApi = marconiApi.getQueueApiForZoneAndClient(ZONE, CLIENT_ID);
        queueApi.delete(QUEUE_NAME);
    }
}
