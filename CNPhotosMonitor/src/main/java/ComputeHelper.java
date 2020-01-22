import com.google.api.services.compute.Compute;
import com.google.api.services.compute.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ComputeHelper {
    private final Compute compute;
    private final String projectId;

    ComputeHelper(Compute compute, String projectId){
        this.compute = compute;
        this.projectId = projectId;
    }

    public void printInstancesInfo(String zoneName) throws IOException {
        Compute.Instances.List instances =
                compute.instances().list(projectId, zoneName);
        InstanceList list = instances.execute();
        if (list.getItems() != null ) {
            for (Instance instance : list.getItems()) {
                //System.out.println(instance.toPrettyString());
                System.out .println(instance.getName());
            }
        }
    }

    public Operation resizeInstanceGroup(String zoneName, String instanceGroup, int newSize) throws IOException {
        Compute.InstanceGroupManagers.Resize request = compute
                .instanceGroupManagers()
                .resize(projectId, zoneName, instanceGroup, newSize);
        return request.execute();
    }

    public Operation deleteVM(String zoneName, String instanceName) throws IOException {
        Compute.Instances.Delete delete = compute.instances().delete(projectId, zoneName, instanceName);
        return delete.execute();
    }

    public Operation createVM(String zoneName, String instanceName, String imageName, String machineType) throws IOException {
        Instance instance = new Instance();
        instance.setName(instanceName);
        instance.setMachineType(
                "https://www.googleapis.com/compute/v1/projects/"
                + projectId + "/zones/"
                + zoneName + "/machineTypes"
                + machineType);

        instance.setNetworkInterfaces(Collections.singletonList(createNetworkInterface(projectId)));
        instance.setDisks(Collections.singletonList(createDisk(instanceName, projectId, zoneName, imageName)));
        Compute.Instances.Insert insert = compute.instances().insert(projectId, zoneName, instance);
        return insert.execute();
    }

    public List<Instance> getInstancesInInstanceGroup(String instangeGroup, String zoneName) throws IOException {
        Compute.Instances.List instances = compute.instances().list(projectId, zoneName);
        InstanceList list = instances.execute();
        return list.getItems().stream().filter(inst -> inst.getName().contains(instangeGroup)).collect(Collectors.toList());
    }

    public Operation waitOperation(Operation op) throws InterruptedException, IOException {
        String zone = op.getZone();
        if(zone != null) {
            String[] bits = zone.split("/");
            zone = bits[bits.length - 1];
        }
        while(!op.getStatus().equals("DONE")){
            Thread.sleep(100);
            Compute.ZoneOperations.Get get = compute.zoneOperations().get(projectId, zone, op.getName());
            op = get.execute();
        }
        return op;
    }

    public static NetworkInterface createNetworkInterface(String projectId){
        NetworkInterface ifc = new NetworkInterface();

        ifc.setNetwork("https://www.googleapis.com/compute/v1/projects/"
                + projectId
                + "/global/networks/default");
        List<AccessConfig> configs = new ArrayList<>();
        AccessConfig config = new AccessConfig();
        config.setType("ONE_TO_ONE_NAT");
        config.setName("External NAT");
        configs.add(config);
        ifc.setAccessConfigs(configs);
        return ifc;
    }

    public static AttachedDisk createDisk(String instanceName, String projectId, String zone, String imageName){
        AttachedDisk disk = new AttachedDisk();
        disk.setBoot(true);
        disk.setAutoDelete(true);
        disk.setType("PERSISTENT");
        AttachedDiskInitializeParams params = new AttachedDiskInitializeParams();
        params.setDiskName(instanceName);

        params.setSourceImage("global/images/" + imageName);
        params.setDiskType("https://www.googleapis.com/compute/v1/projects/"
                + projectId + "/zones/"
                + zone + "/diskTypes/pd-standard");
        disk.setInitializeParams(params);
        return disk;
    }
}
