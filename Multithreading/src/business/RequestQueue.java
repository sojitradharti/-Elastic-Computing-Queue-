package business;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author Sojit
 * 
 */
public class RequestQueue {

    private Queue<Request> reqQueue;

    public RequestQueue() {
        this.reqQueue = new LinkedList<>();
    }

    public Queue<Request> getReqQueue() {
        return reqQueue;
    }

}
