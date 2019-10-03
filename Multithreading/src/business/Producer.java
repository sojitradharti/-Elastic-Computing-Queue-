package business;

import userinterface.mainJFrame;

/**
 * 
 * @author Sojit
 * 
 */
public class Producer implements Runnable {

    private RequestQueue requestQueue;
    private int requestRate;
    private int processingTime;
    private int count = 0;
    private mainJFrame mainFrame;
    private boolean flag = false;

    public Producer(mainJFrame mainFrame, RequestQueue requestQueue, int requestRate, int processingTime) {
        this.mainFrame = mainFrame;
        this.requestQueue = requestQueue;
        this.requestRate = requestRate;
        this.processingTime = processingTime;

    }

    @Override
    public void run() {
        while (flag) {

            try {
                Thread.sleep(requestRate * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
            Request request = new Request("Request " + count);
            request.setStatus("INCOMING");
            request.setProcessingTime(processingTime * 1000);
            request.setStartTime((System.currentTimeMillis()));
            requestQueue.getReqQueue().add(request);
            mainFrame.populateRequestTable(requestQueue);
        }
        mainFrame.populateRequestTable(requestQueue);

    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}
