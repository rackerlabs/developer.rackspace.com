import static org.jclouds.rackspace.clouddns.v1.predicates.JobPredicates.awaitComplete;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import org.jclouds.ContextBuilder;
import org.jclouds.rackspace.clouddns.v1.CloudDNSApi;
import org.jclouds.rackspace.clouddns.v1.domain.CreateDomain;
import org.jclouds.rackspace.clouddns.v1.domain.Domain;
import org.jclouds.rackspace.clouddns.v1.domain.Record;
import org.jclouds.rackspace.clouddns.v1.domain.RecordDetail;
import org.jclouds.rackspace.clouddns.v1.domain.UpdateDomain;
import org.jclouds.rackspace.clouddns.v1.features.DomainApi;
import org.jclouds.rackspace.clouddns.v1.features.RecordApi;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.io.Closeables;

public class CloudDNS {
    // The jclouds Provider for the Rackspace Cloud DNS US cloud service. It contains information
    // about the cloud service API and specific instantiation values, such as the endpoint URL.
    public static final String PROVIDER = System.getProperty("provider", "rackspace-clouddns-us");
    // Authentication credentials
    public static final String USERNAME = System.getProperty("username", "{username}");
    public static final String API_KEY = System.getProperty("apikey", "{apiKey}");

    public static final String DOMAIN_NAME = System.getProperty("domain", "domain.com");

    public static void main(String[] args) throws Exception {

        CloudDNSApi cloudDNSApi = authenticate(USERNAME, API_KEY);

        Set<Domain> domains = createZone(cloudDNSApi);
        Domain domain = getZone(cloudDNSApi, domains);
        modifyZone(cloudDNSApi, domain);

        Set<RecordDetail> records = createRecord(cloudDNSApi, domain);
        RecordDetail record = getRecord(cloudDNSApi, domain, records);
        modifyRecord(cloudDNSApi, domain, record);

        deleteResources(cloudDNSApi, domain, record);
    }

    public static CloudDNSApi authenticate(String username, String apiKey) {
        CloudDNSApi cloudDNSApi = ContextBuilder.newBuilder(PROVIDER)
            .credentials(username, apiKey)
            .buildApi(CloudDNSApi.class);

        return cloudDNSApi;
    }

    private static Set<Domain> createZone(CloudDNSApi cloudDNSApi) throws TimeoutException {
        DomainApi domainApi = cloudDNSApi.getDomainApi();
        List<CreateDomain> createDomains = Lists.newArrayList();
        CreateDomain createDomain = CreateDomain.builder()
                .name(DOMAIN_NAME)
                .email("admin@domain.com")
                .ttl(300)
                .build();

        createDomains.add(createDomain);

        Set<Domain> domains = awaitComplete(cloudDNSApi, domainApi.create(createDomains));

        return domains;
    }

    private static Domain getZone(CloudDNSApi cloudDNSApi, Set<Domain> domains) {
        DomainApi domainApi = cloudDNSApi.getDomainApi();
        Domain domain = domainApi.get(domains.iterator().next().getId());

        return domain;
    }

    private static void modifyZone(CloudDNSApi cloudDNSApi, Domain domain) throws TimeoutException {
        DomainApi domainApi = cloudDNSApi.getDomainApi();
        UpdateDomain updateDomain = UpdateDomain.builder()
                .email("changed@domain.com")
                .ttl(3600)
                .build();

        awaitComplete(cloudDNSApi, domainApi.update(domain.getId(), updateDomain));
    }

    private static Set<RecordDetail> createRecord(CloudDNSApi cloudDNSApi, Domain domain) throws TimeoutException {
        RecordApi recordApi = cloudDNSApi.getRecordApiForDomain(domain.getId());
        Record createARecord = Record.builder()
                .type("A")
                .name("app." + DOMAIN_NAME)
                .data("192.168.1.1")
                .ttl(3600)
                .build();

        List<Record> createRecords = ImmutableList.of(createARecord);

        Set<RecordDetail> records = awaitComplete(cloudDNSApi, recordApi.create(createRecords));

        return records;
    }

    private static RecordDetail getRecord(CloudDNSApi cloudDNSApi, Domain domain, Set<RecordDetail> records) {
        RecordApi recordApi = cloudDNSApi.getRecordApiForDomain(domain.getId());
        RecordDetail record = recordApi.get(records.iterator().next().getId());

        return record;
    }

    private static void modifyRecord(CloudDNSApi cloudDNSApi, Domain domain, RecordDetail record) throws TimeoutException {
        RecordApi recordApi = cloudDNSApi.getRecordApiForDomain(domain.getId());
        Record updateRecord = Record.builder()
                .data("192.168.1.2")
                .build();

        awaitComplete(cloudDNSApi, recordApi.update(record.getId(), updateRecord));
    }

    private static void deleteRecord(CloudDNSApi cloudDNSApi, Domain domain, RecordDetail record) throws TimeoutException {
        List<String> recordIds = ImmutableList.of(record.getId());
        RecordApi recordApi = cloudDNSApi.getRecordApiForDomain(domain.getId());

        awaitComplete(cloudDNSApi, recordApi.delete(recordIds));
    }

    private static void deleteZone(CloudDNSApi cloudDNSApi, Domain domain) throws TimeoutException {
        List<Integer> domainIds = ImmutableList.of(domain.getId());
        DomainApi domainApi = cloudDNSApi.getDomainApi();

        awaitComplete(cloudDNSApi, domainApi.delete(domainIds, true));
    }

    private static void deleteResources(CloudDNSApi cloudDNSApi, Domain domain, RecordDetail record)
            throws TimeoutException, IOException {
        deleteRecord(cloudDNSApi, domain, record);
        deleteZone(cloudDNSApi, domain);
        Closeables.close(cloudDNSApi, true);
    }
}
