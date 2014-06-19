import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.jclouds.ContextBuilder;
import org.jclouds.openstack.marconi.v1.MarconiApi;
import org.jclouds.openstack.marconi.v1.domain.CreateMessage;
import org.jclouds.openstack.marconi.v1.domain.Message;
import org.jclouds.openstack.marconi.v1.domain.MessagesCreated;
import org.jclouds.openstack.marconi.v1.domain.Queue;
import org.jclouds.openstack.marconi.v1.features.ClaimApi;
import org.jclouds.openstack.marconi.v1.features.MessageApi;
import org.jclouds.openstack.marconi.v1.features.QueueApi;

import com.google.common.collect.ImmutableList;
import com.google.common.io.Closeables;

public class CloudQueues {
    // The jclouds Provider for the Rackspace Cloud Queues US cloud service. It contains information
    // about the cloud service API and specific instantiation values, such as the endpoint URL.
    public static final String PROVIDER = System.getProperty("provider", "rackspace-cloudqueues-us");
    // jclouds refers to "regions" as "zones"
    public static final String REGION = System.getProperty("region", "IAD");
    // Authentication credentials
    public static final String USERNAME = System.getProperty("username", "{username}");
    public static final String API_KEY = System.getProperty("apikey", "{apiKey}");

    public static final String QUEUE_NAME = System.getProperty("queuename", "sample-queue");
    public static final UUID CLIENT_ID = UUID.fromString(System.getProperty("uuid", "0128107c-f4cd-43d4-83df-1393cbefd987"));

    public static void main(String[] args) throws Exception {
        
        MarconiApi marconiApi = authenticate(USERNAME, API_KEY);

        QueueApi queueApi = marconiApi.getQueueApiForZoneAndClient(REGION, CLIENT_ID);
        createQueue(queueApi);
        listQueues(queueApi);

        MessageApi messageApi = marconiApi.getMessageApiForZoneAndClientAndQueue(REGION, CLIENT_ID, QUEUE_NAME);
        postMessage(messageApi);

        ClaimApi claimApi = marconiApi.getClaimApiForZoneAndClientAndQueue(REGION, CLIENT_ID, QUEUE_NAME);
        List<Message> messages = claimMessage(claimApi);
        releaseMessage(claimApi, messages);

        deleteResources(marconiApi, messages);
    }

    public static MarconiApi authenticate(String username, String apiKey) {
        MarconiApi marconiApi = ContextBuilder.newBuilder(PROVIDER)
            .credentials(USERNAME, API_KEY)
            .buildApi(MarconiApi.class);

        return marconiApi;
    }

    public static void createQueue(QueueApi queueApi) {
        queueApi.create(QUEUE_NAME);
    }

    public static List<Queue> listQueues(QueueApi queueApi) {
        List<Queue> queues = queueApi.list(true).concat().toList();

        return queues;
    }

    public static MessagesCreated postMessage(MessageApi messageApi) {
        CreateMessage createMessage = CreateMessage.builder().ttl(900).body("{\"play\": \"hockey\"}").build();
        List<CreateMessage> createMessages = ImmutableList.of(createMessage);

        MessagesCreated messagesCreated = messageApi.create(createMessages);

        return messagesCreated;
    }

    public static List<Message> claimMessage(ClaimApi claimApi) {
        List<Message> messages = claimApi.claim(900, 120, 4);

        return messages;
    }

    public static void releaseMessage(ClaimApi claimApi, List<Message> messages) {
        claimApi.release(messages.get(0).getClaimId().get());
    }

    public static void deleteMessage(MarconiApi marconiApi, String messageId) {
        MessageApi messageApi = marconiApi.getMessageApiForZoneAndClientAndQueue(REGION, CLIENT_ID, QUEUE_NAME);
        List<String> messageIds = ImmutableList.of(messageId);

        messageApi.delete(messageIds);
    }

    public static void deleteQueue(MarconiApi marconiApi) {
        QueueApi queueApi = marconiApi.getQueueApiForZoneAndClient(REGION, CLIENT_ID);
        queueApi.delete(QUEUE_NAME);
    }

    public static void deleteResources(MarconiApi marconiApi, List<Message> messages) throws IOException {
        deleteMessage(marconiApi, messages.get(0).getId());
        deleteQueue(marconiApi);

        Closeables.close(marconiApi, true);
    }
}
