package serviciosCognitivos;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.MessagingException;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;


public class EnviarMail
{
    /**
     * main de prueba
     * @param args Se ignoran.
     */
    public void EnviarCorreo(String correo){
        try
        {
            // Propiedades de la conexi�n
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "profesorrebeldesswing@gmail.com");
            props.setProperty("mail.smtp.auth", "true");

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props,null);
            session.setDebug(true);

            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            
//          Aqui va el destinatario
            message.setFrom(new InternetAddress("profesorrebeldesswing@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
            
            message.setSubject("Profesor Cognitivo");
            message.setText("La aplicaci�n est� intentando conectarte contigo. Te felixito");

            // Lo enviamos.
           
            Transport t = session.getTransport("smtp");
            t.connect("profesorrebeldesswing@gmail.com", "rebeldesdelswing");
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}