public class InstanceMetrics {
    private double cpu_usage;
    private double mem_usage;
    private double img_per_sec;

    public InstanceMetrics(double cpu_usage, double mem_usage, double img_per_sec) {
        this.cpu_usage = cpu_usage;
        this.mem_usage = mem_usage;
        this.img_per_sec = img_per_sec;
    }

    public static InstanceMetrics baseInstanceMetrics(){
        return new InstanceMetrics(0, 0, 0);
    }

    public double getCpu_usage() {
        return cpu_usage;
    }

    public void setCpu_usage(double cpu_usage) {
        this.cpu_usage = cpu_usage;
    }

    public double getMem_usage() {
        return mem_usage;
    }

    public void setMem_usage(double mem_usage) {
        this.mem_usage = mem_usage;
    }

    public double getImg_per_sec() {
        return img_per_sec;
    }

    public void setImg_per_sec(double img_per_sec) {
        this.img_per_sec = img_per_sec;
    }
}
