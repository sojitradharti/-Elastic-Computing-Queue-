package business;

/**
 * 
 * @author Sojit
 * 
 */
public class Request {
    private String reqName;
    private int processTime;
    private long startTime;
    private long endTime;
    private String status;
    private int resTime;

    public Request(String reqName) {
        this.reqName = reqName;
    }

    public String getReqName() {
        return reqName;
    }

    public int getProcessingTime() {
        return processTime;
    }

    public void setProcessingTime(int processTime) {
        this.processTime = processTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getResponseTime() {
        return resTime;
    }

    public void setResponseTime(int responseTime) {
        this.resTime = responseTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

}
