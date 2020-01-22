import org.hyperic.sigar.*;

import java.io.Serializable;

public class StatsCalculator implements Serializable {

    private double freeRAM;
    private double UsedRAMPercent;
    private int cpuFreq;
    private double[] cpuLoad;
    private int numCores;
    private double CpuSum;
    private CpuPerc cpuPerc;

    public StatsCalculator() {
        calculateStats();
    }

    public void calculateStats() {
        numCores = Runtime.getRuntime().availableProcessors();
        Sigar sigar = new Sigar();
        try {
            CpuInfo[] cpuInfo = sigar.getCpuInfoList();
            cpuFreq = cpuInfo[0].getMhz();
            Mem memInfo = sigar.getMem();
            freeRAM = memInfo.getFreePercent();
            UsedRAMPercent = memInfo.getUsedPercent();
            cpuPerc = sigar.getCpuPerc();

        } catch (SigarException ex) {
            ex.printStackTrace();
        }
    }

    public double getFreeRAM() {
        return freeRAM;
    }

    public double getUsedRAM() {
        return UsedRAMPercent;
    }

    public int getCpuFreq() {
        return cpuFreq;
    }

    public double[] getCpuLoad() {
        return cpuLoad;
    }

    public CpuPerc CpuPerc(){
        return cpuPerc;
    }

    public void printStats() {
        System.out.println("---------------------");
        System.out.println("CPUFreq: " + cpuFreq);
        System.out.println("NumCores: " + numCores);
        System.out.println("FreeRAM: " + freeRAM);
        System.out.println("Load: ");
        for (double load : cpuLoad) {
            System.out.println("Load: " + load);
        }
        System.out.println("---------------------");
    }
}