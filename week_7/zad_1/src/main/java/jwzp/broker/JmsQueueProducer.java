package jwzp.broker;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jwzp.data.Transaction;
import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;
import java.util.List;

public class JmsQueueProducer {

    private String brokerURL;
    private String queueName;
    private List<Transaction> transactions;

    public JmsQueueProducer(String brokerURL, String queueName, List<Transaction> transactions) {
        this.brokerURL = brokerURL;
        this.queueName = queueName;
        this.transactions = transactions;
    }

    public void sendTransactionsAsMessages(){
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(queueName);

            MessageProducer messageProducer = session.createProducer(queue);

            ObjectMapper objectMapper = new ObjectMapper();

            for(Transaction t : transactions){
                Message message = session.createTextMessage(objectMapper.writeValueAsString(t));
                messageProducer.send(message);
            }
            connection.close();
            session.close();
        } catch (JMSException | JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}