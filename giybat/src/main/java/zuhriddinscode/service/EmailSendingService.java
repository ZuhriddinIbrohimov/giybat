package zuhriddinscode.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import zuhriddinscode.util.JwtUtil;

@Service
public class EmailSendingService {

    private String fromAccount = "ibrohimovzuhriddin310@gmail.com";
    private String serverDomain="http://localhost:8080";


    @Autowired
    private JavaMailSender javaMailSender;

    public void sendRegistrationEmail(String email, Integer profileId) {
        String subject = "Complete Registration";
        String body = "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "    <style>\n" +
                "        a {\n" +
                "            padding: 10px 30px;\n" +
                "            display: inline-block;\n" +
                "        }\n" +
                "        .button-link {\n" +
                "            text-decoration: none;\n" +
                "            color: white;\n" +
                "            background-color: indianred;\n" +
                "        }\n" +
                "        .button-link:hover {\n" +
                "            background-color: #dd4444;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "</body>\n" +
                "</html>\n" +
                "\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Complete registration</h1>\n" +
                "<p>Salom yaxshimisiz?</p>\n" +
                "\n" +
                "<p>\n" +
                "    Please click to button for completing registration :<a class=\"button-link\"\n" +
                "                                                           href=\"%s/auth/registration/verification/%s\"\n" +
                "                                                           target=\"_blank\">Click here</a>\n" +
                "</p>\n" +
                "</body>\n" +
                "</html>";
        body = String.format(body, serverDomain,JwtUtil.encode(profileId));
        System.out.print(JwtUtil.encode(profileId));
        sendMimeEmail(email, subject, body);
    }

    private String sendMimeEmail(String email, String subject, String body) {
        try {
            MimeMessage msg = javaMailSender.createMimeMessage();
            msg.setFrom(fromAccount);
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(body,true);
            javaMailSender.send(msg);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return "Mail was send";
    }
}
