package com.app.backend.service;

import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.backend.dto.EmailDTO;
import com.app.backend.model.OnbEntity;
import com.app.backend.model.UsersEntity;
import com.app.backend.repository.OnbRepository;
import com.app.backend.repository.UsersRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class AppServiceImpl implements AppService {
    @Value("${sendgrid.api-key}")
    private String sgApiKey;

    @Value("${sendgrid.from}")
    private String fromEmail;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private OnbRepository onbRepository;

    @Override
    public ResponseEntity<UsersEntity> createUser(UsersEntity user) {
        try {
            Boolean userExists = usersRepository.existsByEmail(user.getEmail());
            if (userExists) {
                return ResponseEntity.status(HttpStatus.SC_CONFLICT).body(null); // Code 409 Conflict
            }
            UsersEntity newUser = usersRepository.save(user);
            return ResponseEntity.status(HttpStatus.SC_CREATED).body(newUser);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    public ResponseEntity<List<UsersEntity>> getAllUsers() {
        List<UsersEntity> users = usersRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<List<UsersEntity>> updateUsers(List<UsersEntity> updatedUsers) {

        for (UsersEntity users : updatedUsers) {
            Long id = users.getId();
            if (usersRepository.existsById(id)) {
                if (!usersRepository.existsById(id)) {
                    return ResponseEntity.status(HttpStatus.SC_NOT_FOUND).body(null);
                }
                usersRepository.findById(id).map(u -> {
                    u.setGeneralOnbStatus(users.getGeneralOnbStatus());
                    u.setTechOnbStatus(users.getTechOnbStatus());
                    return usersRepository.save(u);
                }).orElse(null);
            }
        }
        List<UsersEntity> newUsers = usersRepository.findAll();
        return ResponseEntity.ok(newUsers);
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long id) {
        usersRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.SC_NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<List<OnbEntity>> getAllOnbs() {
        if (onbRepository.count() == 0) {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("OnboardingData.json");
            if (inputStream != null) {
                try {
                    List<OnbEntity> onboardings = mapper.readValue(inputStream, new TypeReference<List<OnbEntity>>() {
                    });
                    onbRepository.saveAll(onboardings);
                } catch (Exception e) {
                    System.out.println("Error reading OnboardingData.json: " + e.getMessage());
                }
            }
        }

        List<OnbEntity> onb = onbRepository.findAll();
        return ResponseEntity.ok(onb);
    }

    @Override
    public ResponseEntity<String> sendEmail(EmailDTO emailDTO) {
        System.out.println("emailDTO.getTo()" + emailDTO.getTo());
        for (String em : emailDTO.getTo()) {

            Email email = new Email(em);
            Content content = new Content("text/plain", emailDTO.getBody());
            Mail mail = new Mail(new Email(fromEmail), emailDTO.getSubject(), email, content);

            System.out.println("SG API Key: " + sgApiKey);
            System.out.println("From Email: " + fromEmail);
            SendGrid sg = new SendGrid(sgApiKey);
            Request request = new Request();

            try {
                System.out.println("Sending email to: " + em);
                System.out.println("Email subject: " + emailDTO.getSubject());
                System.out.println("Email body: " + emailDTO.getBody());
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());
                sg.api(request);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("null");
            }

        }
        return ResponseEntity.ok("Complete");
    }
}
