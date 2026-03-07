package com.example.TrainerEffort.trainerEffortcontroller;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class password {
	
	@Autowired
    private JavaMailSender mailSender;

    public void sendOtp(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Reset OTP");
        message.setText("Your OTP for password reset is: " + otp + "\n\nPlease use this OTP within 5 minutes.");
        mailSender.send(message);
    }
    
    
    private static final String DIGITS = "0123456789";
    private static final int OTP_LENGTH = 6;

    public static String generateOTP() {
        SecureRandom random = new SecureRandom();
        StringBuilder otp = new StringBuilder(OTP_LENGTH);
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        }
        return otp.toString();
    }

    private Map<String, String> otpStore = new HashMap<>();

    @PostMapping("/request-otp")
    public String requestOtp(@RequestParam String email) {
        String otp = generateOTP();
        otpStore.put(email, otp);  // Store OTP temporarily
        sendOtp(email, otp);
        return "OTP sent to your email!";
    }

}
