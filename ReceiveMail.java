
import com.sun.mail.pop3.POP3Store;
import java.io.IOException;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;

public class ReceiveMail {
    public ReceiveMail() {
    }

    public static void receiveEmail(String pop3Host, String storeType, String user, String password) {
        try {
            Properties properties = new Properties();
            properties.put("mail.pop3.host", pop3Host);
            properties.put("mail.pop3.port", "995");
            Session emailSession = Session.getDefaultInstance(properties);
            POP3Store emailStore = (POP3Store)emailSession.getStore(storeType);
            emailStore.connect(pop3Host, user, password);
            Folder emailFolder = emailStore.getFolder("INBOX");
            emailFolder.open(1);
            Message[] messages = emailFolder.getMessages();

            for(int i = 0; i < 5; ++i) {
                Message message = messages[i];
                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Text: " + message.getContent().toString());
            }

            emailFolder.close(false);
            emailStore.close();
        } catch (NoSuchProviderException var11) {
            var11.printStackTrace();
        } catch (MessagingException var12) {
            var12.printStackTrace();
        } catch (IOException var13) {
            var13.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String host = "pop.gmail.com";
        String mailStoreType = "pop3s";
        String username = "mail@gmail.com";
        String password = "password;
        receiveEmail(host, mailStoreType, username, password);
    }
}
