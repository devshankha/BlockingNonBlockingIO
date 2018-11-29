package NonBlockingServer;

public interface IMessageProcessor {

    public void process(Message message, WriteProxy writeProxy);

}