package com.myshop.MyShop.Controller;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MailController {
    @Autowired
    private JavaMailSender mailSender;

    @PostMapping("/contact")
    public String handleContactForm(@RequestParam String name,
                                    @RequestParam String email,
                                    @RequestParam String subject,
                                    @RequestParam String message,
                                    Model model) {
        try {

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo("sprociux@gmail.com");
            helper.setSubject(subject);
            helper.setFrom(email);
            helper.setText("From: " + name + "\nEmail: " + email + "\nSubject: " + subject + "\n\n" + message);

            mailSender.send(mimeMessage);
            model.addAttribute("mensajeEnviado", true);
            return "contact";
        } catch (MessagingException | MailException e) {
            return "Error al enviar el mensaje: " + e.getMessage();
        }
    }

}
