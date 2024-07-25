package likelion.hack6.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${spring.mail.senderEmail}")
    private String senderEmail;

    private final JavaMailSender emailSender;

    public MimeMessage createEmailCertificationContent(String code) {
        try {
            String title = "[바뱍] 이메일 인증 메일입니다.";
            String content = """
                    바뱟 인증 메일입니다.
                    아래 코드를 입력해주세요.
                    [%s]
                    """.formatted(code);

            MimeMessage message = emailSender.createMimeMessage();
            message.setSubject(title);
            message.setFrom(senderEmail);
            message.setText(content);
            return message;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendEmail(String receiverEmail, String code) {
        try {
            MimeMessage message = createEmailCertificationContent(code);
            message.addRecipients(MimeMessage.RecipientType.TO, receiverEmail); //보낼 이메일 설정
            emailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
