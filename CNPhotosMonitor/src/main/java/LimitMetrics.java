public class LimitMetrics {
    public final Object mutex = new Object();
    double[] CPU_LIMIT;
    double[] MEM_LIMIT;
    double[] IMG_PER_SEC_LIMIT;
    long ACTION_PERIOD_LIMIT;

    LimitMetrics(double[] CPU_LIMIT, double[] MEM_LIMIT, double[] IMG_PER_SEC_LIMIT, long ACTION_PERIOD_LIMIT){
        this.CPU_LIMIT = CPU_LIMIT;
        this.MEM_LIMIT = MEM_LIMIT;
        this.IMG_PER_SEC_LIMIT = IMG_PER_SEC_LIMIT;
        this.ACTION_PERIOD_LIMIT = ACTION_PERIOD_LIMIT;
    }
}
