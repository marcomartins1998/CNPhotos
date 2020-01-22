import com.google.api.services.compute.model.Instance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InstanceGroupMetrics {
    public final Object mutex = new Object();
    private Map<String, InstanceMetrics> groupInfo = new HashMap<>();

    InstanceGroupMetrics(List<Instance> groupInstance){
        groupInstance.forEach(i -> this.groupInfo.put(i.getName(), InstanceMetrics.baseInstanceMetrics()));
    }

    public double getGlobalCpuUsage(){ synchronized (mutex){
        return groupInfo.values().stream().map(InstanceMetrics::getCpu_usage).reduce(Double::sum).get();
    }}

    public double getGlobalMemUsage(){ synchronized (mutex){
        return groupInfo.values().stream().map(InstanceMetrics::getMem_usage).reduce(Double::sum).get();
    }}

    public double getGlobalImgPerSec(){ synchronized (mutex){
        return groupInfo.values().stream().map(InstanceMetrics::getImg_per_sec).reduce(Double::sum).get();
    }}

    public Map<String, Double> getGlobalMetrics(){ synchronized (mutex){
        Map<String, Double> globalMetrics = new HashMap<>();
        globalMetrics.put("cpu", groupInfo.values().stream().map(InstanceMetrics::getCpu_usage).reduce(Double::sum).get()/groupInfo.size());
        globalMetrics.put("mem", groupInfo.values().stream().map(InstanceMetrics::getMem_usage).reduce(Double::sum).get()/groupInfo.size());
        globalMetrics.put("app", groupInfo.values().stream().map(InstanceMetrics::getImg_per_sec).reduce(Double::sum).get()/groupInfo.size());
        return globalMetrics;
    }}

    public void updateInstanceCpuMemUsage(String instanceName, double cpuUsage, double memUsage){ synchronized (mutex){
        if(this.groupInfo.containsKey(instanceName)) {
            this.groupInfo.get(instanceName).setCpu_usage(cpuUsage);
            this.groupInfo.get(instanceName).setMem_usage(memUsage);
        }
    }}

    public void updateInstanceImgPerSec(String instanceName, double imgPerSec){ synchronized (mutex){
        if(this.groupInfo.containsKey(instanceName)) {
            this.groupInfo.get(instanceName).setImg_per_sec(imgPerSec);
        }
    }}

    public int getGroupSize(){
        return groupInfo.size();
    }

    public void updateGroup(List<Instance> newGroup){ synchronized (mutex){
        //Map<String, InstanceMetrics> newGroupInfo = new HashMap<>();
        this.groupInfo.clear();
        newGroup.forEach(inst -> this.groupInfo.put(inst.getName(), InstanceMetrics.baseInstanceMetrics()));
        /*newGroup.stream().map(Instance::getName).forEach(inst -> {
            if(groupInfo.containsKey(inst)) newGroupInfo.put(inst, groupInfo.get(inst));
            else newGroupInfo.put(inst, InstanceMetrics.baseInstanceMetrics());
        });*/
        //this.groupInfo = newGroupInfo;
    }}

}
