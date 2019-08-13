package server;

import messages.EmailReqMsg;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class QueueService {
    private static QueueService instance = null;
    private static BlockingQueue<EmailReqMsg> emailReqMsgBlockingQueue = null;
    private QueueService() {}
    public static QueueService getInstance() {
        if (instance == null) {
            instance = new QueueService();
        }
        return instance;
    }
    private void initialize() {
        if (emailReqMsgBlockingQueue == null) {
            emailReqMsgBlockingQueue = new LinkedBlockingQueue<EmailReqMsg>();
            EventProcessor eventProcessor = new EventProcessor();
            eventProcessor.start();
        }
    }
    public void putRequestInQueue(EmailReqMsg emailReqMsg) {
        try {
            initialize();
            emailReqMsgBlockingQueue.put(emailReqMsg);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void sendMail(EmailReqMsg emailReqMsg)
    {
        //from,password,to,subject,message
		String from = "janithalokuge@gmail.com";
		String password = "bahxfrsxhpuvjbhz";
        Mailer.send(from,password,emailReqMsg.getReceipentEmail(),emailReqMsg.getSubject(),emailReqMsg.getBody());
        //change from, password and to

    }

    class EventProcessor extends Thread {
        @Override
        public void run() {
            for (;;) {
                EmailReqMsg emailReqMsg = null;
                try {
                    emailReqMsg = emailReqMsgBlockingQueue.take();
                    sendMail(emailReqMsg);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
