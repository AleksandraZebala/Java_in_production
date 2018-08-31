package jwzp.broker;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jwzp.data.Transaction;
import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;
import java.util.List;

public class JmsTopicProducer {

    private String brokerURL;
    private String topicName;
    private List<Transaction> transactions;

    public JmsTopicProducer(String brokerURL, String topicName, List<Transaction> transactions) {
        this.brokerURL = brokerURL;
        this.topicName = topicName;
        this.transactions = transactions;
    }

    public void sendTransactionsAsMessages(){
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic(topicName);

            MessageProducer messageProducer = session.createProducer(topic);

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
