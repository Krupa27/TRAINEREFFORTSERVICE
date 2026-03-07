package com.example.TrainerEffort.trainerEffortService;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.example.TrainerEffort.Specification.TrainerSpecification;
import com.example.TrainerEffort.effortDAO.Info;
import com.example.TrainerEffort.effortDAO.Joined;
import com.example.TrainerEffort.effortDAO.effortDAO;
import com.example.TrainerEffort.feigns.FeignTrainer;
import com.example.TrainerEffort.effortDAO.TrainerInfo;
import com.example.TrainerEffort.trainerEffortRepository.TrainerEffortRepository;
import com.example.TrainerEffort.trainerEffortRepository.updateRepository;
import com.example.TrainerEffort.trainerEffortcontroller.password;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service
public class TrainerEffortService {
	
	@Autowired
	FeignTrainer fot;
	
	@Autowired
	TrainerEffortRepository rob;
	@Autowired
	updateRepository rob2;

	
	
	public String updateStatus(effortDAO eff) {
		if ("reject".equalsIgnoreCase(eff.getStatus())) {
	        if (eff.getRemarks() == null || eff.getRemarks().trim().isEmpty()) {
	            return "Rejection must include remarks.";
	        }
	    } else {
	        // If not rejected, ignore any provided remarks
	        eff.setRemarks(null);
	    }

	    // Save the updated effort to the repository
	    rob.save(eff);
	    return "Status updated successfully.";
	}
	
	public List<effortDAO> approvalsStatus(String Id){
		   return rob.findByInfoID(Id);
		}
	
	
	public List<effortDAO> pendingApprovals(String hrId) {
		List<effortDAO> l=rob.findByStatus("Pending");
		HashSet<String> hs=new HashSet<>();
		for(String c:fot.getCohorts(hrId)) {
			hs.add(c);
		}
		List<effortDAO> ans=new ArrayList<>();
		for(effortDAO eff:l) {
			if(hs.contains(eff.getInfo().getCohortCode()))
				ans.add(eff);
		}
		return ans;
	}

//	public List<Joined> downloadXL() {
//    	List<Joined> effortList=new ArrayList<>();
//		List<effortDAO> effort=rob.findAll();
//		for(effortDAO eff:effort) {
//			String id=eff.getInfo().getID();
//			Optional<TrainerInfo> op=rob2.findById(id);
//			if(op.isPresent()) {
//				TrainerInfo tf=op.get();
//				Joined j=new Joined(eff.getInfo().getCohortCode(),tf.getSkills().toString(), tf.getMappedType(), id,tf.getRole(),
//						eff.getMode(), eff.getReason(), "hr",eff.getEffortHours(),eff.getInfo().getDate() );
//				effortList.add(j);
//			}
//			
//		}
//		
//		return effortList;
//		
//	}
//	public String geteffort(effortDAO incomingEffortData) {
//		
//		
//		Info infoId = incomingEffortData.getInfo();
//		System.out.println(incomingEffortData);
//		System.out.println(infoId);
//        if (infoId == null || infoId.getID() == null || infoId.getID().isEmpty() ||
//            infoId.getCohortCode() == null || infoId.getCohortCode().isEmpty()) {
//            return "Error: Trainer ID and Cohort ID must be provided and not empty.";
//        }
//
//        Optional<effortDAO> existingTrainerEffortOptional = rob.findById(infoId);
//
//        if (existingTrainerEffortOptional.isPresent()) {
//            effortDAO existingTrainerEffort = existingTrainerEffortOptional.get();
//
//            if (incomingEffortData.getMode() != null) {
//                existingTrainerEffort.setMode(incomingEffortData.getMode());
//            }
//            if (incomingEffortData.getReason() != null) {
//                existingTrainerEffort.setReason(incomingEffortData.getReason());
//            }
//            if (incomingEffortData.getEffortHours() != null) {
//                existingTrainerEffort.setEffortHours(incomingEffortData.getEffortHours());
//            }
//          
//            if (incomingEffortData.getTopic() != null) {
//                existingTrainerEffort.setTopic(incomingEffortData.getTopic());
//            }
//            if (incomingEffortData.getHighlights() != null) {
//                existingTrainerEffort.setHighlights(incomingEffortData.getHighlights());
//            }
//            if (incomingEffortData.getStatus() != null) {
//            	existingTrainerEffort.setStatus(incomingEffortData.getStatus());
//            }
//
//            
//            if(fot.validateTr(incomingEffortData.getInfo().getID(),incomingEffortData.getInfo().getCohortCode()) == false ) {
//            	return "Trainer: "+incomingEffortData.getInfo().getID()+" is not allocated to the Cohort: "+incomingEffortData.getInfo().getCohortCode();
//            }
//            System.out.println(incomingEffortData);
//            rob.save(existingTrainerEffort);
//            return "Trainer effort updated successfully for ID: " + infoId.getID() + ", Cohort ID: " + infoId.getCohortCode();
//
//        } else {
//
//        	 effortDAO newTrainerEffort = new effortDAO();
//             newTrainerEffort.setInfo(infoId);
//             newTrainerEffort.setMode(incomingEffortData.getMode());
//             newTrainerEffort.setReason(incomingEffortData.getReason());
//             newTrainerEffort.setEffortHours(incomingEffortData.getEffortHours());
//             newTrainerEffort.setTopic(incomingEffortData.getTopic());
//             newTrainerEffort.setHighlights(incomingEffortData.getHighlights());
//             newTrainerEffort.setStatus(incomingEffortData.getStatus());
//             
//             
//             if(fot.validateTr(incomingEffortData.getInfo().getID(),incomingEffortData.getInfo().getCohortCode()) == false ) {
//             	return "Trainer: "+incomingEffortData.getInfo().getID()+" is not allocated to the Cohort: "+incomingEffortData.getInfo().getCohortCode();
//             }
//             System.out.println(incomingEffortData);
//             rob.save(newTrainerEffort); 
//             return "New trainer effort record created successfully for ID: " + infoId.getID() + ", Cohort ID: " + infoId.getCohortCode();
//        }
//    
//    }
	
	public String geteffort(effortDAO incomingEffortData) {
	    Info infoId = incomingEffortData.getInfo();
	    System.out.println(incomingEffortData);
	    System.out.println(infoId);

	    if (infoId == null || infoId.getID() == null || infoId.getID().isEmpty() ||
	        infoId.getCohortCode() == null || infoId.getCohortCode().isEmpty()) {
	        return "Error: Trainer ID and Cohort ID must be provided and not empty.";
	    }

	    try {
	        int newEffortHours = Integer.parseInt(incomingEffortData.getEffortHours());
	        String trainerId = infoId.getID();


	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Adjust the pattern to match your actual string format
	        LocalDate effortDate = LocalDate.parse(infoId.getDate(), formatter);


	        // Fetch all entries for the same trainer and date
//	        List<effortDAO> sameDayEfforts = rob.findByInfo_IDAndInfo_Date(trainerId, effortDate);
	        List<effortDAO> sameDayEfforts = rob.findByInfo_IDAndInfo_Date(trainerId, infoId.getDate());
	        int totalEffort = newEffortHours;

	        for (effortDAO effort : sameDayEfforts) {
	            totalEffort += Integer.parseInt(effort.getEffortHours());
	        }

	        if (totalEffort > 9) {
	            return "Error: Total effort hours exceed 9 for trainer ID " + trainerId +
	                   " on date " + effortDate + ". Current total would be: " + totalEffort;
	        }
	    } catch (NumberFormatException e) {
	        return "Error: Invalid format for effort hours.";
	    }

	    Optional<effortDAO> existingTrainerEffortOptional = rob.findById(infoId);

	    if (existingTrainerEffortOptional.isPresent()) {
	        effortDAO existingTrainerEffort = existingTrainerEffortOptional.get();

	        if (incomingEffortData.getMode() != null) {
	            existingTrainerEffort.setMode(incomingEffortData.getMode());
	        }
	        if (incomingEffortData.getReason() != null) {
	            existingTrainerEffort.setReason(incomingEffortData.getReason());
	        }
	        if (incomingEffortData.getEffortHours() != null) {
	            existingTrainerEffort.setEffortHours(incomingEffortData.getEffortHours());
	        }
	        if (incomingEffortData.getTopic() != null) {
	            existingTrainerEffort.setTopic(incomingEffortData.getTopic());
	        }
	        if (incomingEffortData.getHighlights() != null) {
	            existingTrainerEffort.setHighlights(incomingEffortData.getHighlights());
	        }
	        if (incomingEffortData.getStatus() != null) {
	            existingTrainerEffort.setStatus(incomingEffortData.getStatus());
	        }

	        if (!fot.validateTr(infoId.getID(), infoId.getCohortCode())) {
	            return "Trainer: " + infoId.getID() + " is not allocated to the Cohort: " + infoId.getCohortCode();
	        }

	        rob.save(existingTrainerEffort);
	        return "Trainer effort updated successfully for ID: " + infoId.getID() + ", Cohort ID: " + infoId.getCohortCode();

	    } else {
	        effortDAO newTrainerEffort = new effortDAO();
	        newTrainerEffort.setInfo(infoId);
	        newTrainerEffort.setMode(incomingEffortData.getMode());
	        newTrainerEffort.setReason(incomingEffortData.getReason());
	        newTrainerEffort.setEffortHours(incomingEffortData.getEffortHours());
	        newTrainerEffort.setTopic(incomingEffortData.getTopic());
	        newTrainerEffort.setHighlights(incomingEffortData.getHighlights());
	        newTrainerEffort.setStatus(incomingEffortData.getStatus());

	        if (!fot.validateTr(infoId.getID(), infoId.getCohortCode())) {
	            return "Trainer: " + infoId.getID() + " is not allocated to the Cohort: " + infoId.getCohortCode();
	        }

	        rob.save(newTrainerEffort);
	        return "New trainer effort record created successfully for ID: " + infoId.getID() + ", Cohort ID: " + infoId.getCohortCode();
	    }
	}

	
	
	
	public boolean findById(String id) {
		return rob2.findById(id).isPresent();
	}
	public String addTrainer(TrainerInfo td) {
		rob2.save(td);
		return td.toString();
	}
	
	public List<TrainerInfo> findAll() {
		// TODO Auto-generated method stub
		return rob2.findAll();
		
	}


	
	public TrainerInfo searchTrainer(String id) {
		Optional<TrainerInfo> otf=rob2.findById(id);
		if(otf.isPresent()) {
			System.out.println(otf.get());
		  return otf.get();
		}
		return otf.get();
		
	}
	
	public List<TrainerInfo> searchTrainers(String name, Integer minExp, Integer maxExp,
            String mappedType, String role, String gender,
            List<String> skills) {
	
		Specification<TrainerInfo> spec = TrainerSpecification.hasName(name)
		.and(TrainerSpecification.hasExperienceGreaterThan(minExp))
		.and(TrainerSpecification.hasExperienceLessThan(maxExp))
		.and(TrainerSpecification.hasMappedType(mappedType))
		.and(TrainerSpecification.hasRole(role))
		.and(TrainerSpecification.hasGender(gender))
		.and(TrainerSpecification.hasSkills(skills));
		
		return rob2.findAll(spec);
	}

	
	
	public TrainerInfo updateTrainerInfo(TrainerInfo updatedTrainerData) {


        Optional<TrainerInfo> existingTrainerOptional = rob2.findById(updatedTrainerData.getId());
        
        TrainerInfo existingTrainer = existingTrainerOptional.get();
        if (existingTrainerOptional.isPresent()) {




            existingTrainer.setName(updatedTrainerData.getName());
            existingTrainer.setPhoneNumber(updatedTrainerData.getPhoneNumber());
            existingTrainer.setMappedType(updatedTrainerData.getMappedType());
            existingTrainer.setRole(updatedTrainerData.getRole());
            existingTrainer.setExperience(updatedTrainerData.getExperience());
            existingTrainer.setSkills(updatedTrainerData.getSkills());
            existingTrainer.setEmail(updatedTrainerData.getEmail());


            // Save updated trainer
            rob2.save(existingTrainer);

        } 
        return existingTrainer;
       
            //else {
            
//            return "Error: Trainer with ID " + updatedTrainerData.getId() + " not found.";
//        }
	}
	
	private String generateOTP() {
        SecureRandom random = new SecureRandom();
        int otp = 100000 + random.nextInt(900000); // Generates a 6-digit OTP
        return String.valueOf(otp);
    }

    /** Method to send OTP via Email */
    public void sendOTP(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Trainer Password Reset OTP");
        message.setText("Use this OTP to reset your password: " + otp + "\nThis OTP expires in 5 minutes.");
        mailSender.send(message);
    }
	
	
    private Map<String, String> temporaryOtpStore = new HashMap<>(); // Store OTP temporarily
    
    public String initiatePasswordReset(String trainerId) {
        Optional<TrainerInfo> existingTrainerOptional = rob2.findById(trainerId);

        if (existingTrainerOptional.isPresent()) {
            String otp = generateOTP();
            TrainerInfo trainer = existingTrainerOptional.get();

            // Store OTP temporarily
            temporaryOtpStore.put(trainerId, otp);

            // Send OTP via email
            sendOTP(trainer.getEmail(), otp);

            return "OTP sent successfully to " + trainer.getEmail() + ". Please enter the received OTP to proceed.";
        } else {
            return "Trainer with ID " + trainerId + " not found.";
        }
    }

	
	
	
	@Autowired
	private RestTemplate restTemplate; // Ensure this is configured in your project
	public String resetTrainerPassword(String trainerId, String newPassword, String enteredOtp) {
	    if (trainerId == null || trainerId.trim().isEmpty() || 
	        newPassword == null || newPassword.trim().isEmpty() || 
	        enteredOtp == null || enteredOtp.trim().isEmpty()) {
	        return "Trainer ID, new password, and OTP must be provided.";
	    }

	    // Validate OTP
	    if (!temporaryOtpStore.containsKey(trainerId) || 
	        !temporaryOtpStore.get(trainerId).equals(enteredOtp)) {
	        return "Invalid/expired OTP or Invalid ID";
	    }

	    Optional<TrainerInfo> existingTrainerOptional = rob2.findById(trainerId);

	    if (existingTrainerOptional.isPresent()) {
	        TrainerInfo trainer = existingTrainerOptional.get();

	        // Encrypt and update password
	        trainer.setPassword(passwordEncoder.encode(newPassword));
	        rob2.save(trainer);
	        boolean jwtSaveSuccess = updateTrainerInJwtService(trainerId,newPassword);
	        
		    if (!jwtSaveSuccess) {
		        return "Trainer registered in TrainerInfo table, but failed in JWT service.";
		    }

	        // Remove OTP after successful verification
	        temporaryOtpStore.remove(trainerId);

	        sendPassword1(trainer.getEmail(), newPassword);
	        return "Password reset successfully! A confirmation email has been sent.";
	    } else {
	        return "Trainer with ID " + trainerId + " not found.";
	    }
	}

	
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
    private static final int PASSWORD_LENGTH = 10;

    public static String generatePassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            password.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return password.toString();
    }
	
	
	

    @Autowired
    private JavaMailSender mailSender;
    
    public void sendPassword1(String email, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Trainer Password Updated");
        message.setText("Congrats! Your password for Trainer login has been updated successfully" +
                        "\nYour NewPassword is "+password);
        mailSender.send(message);
    }

    public void sendPassword( String password,TrainerInfo trainerInfo) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(trainerInfo.getEmail());
        message.setSubject("Your Trainer Account Credentials");
        message.setText("Welcome! Your temporary Credentials are: \n Username:" +trainerInfo.getId()+" & password: "+ password +"\n User: "+trainerInfo.getName()+
                        "\nPlease reset it upon first login.");
        mailSender.send(message);
    }
	

	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	public String registerTrainer(TrainerInfo trainerInfo) {
	    String tempPassword =generatePassword();
	    
	    trainerInfo.setPassword(passwordEncoder.encode(tempPassword)); // ✅ Correct
	    rob2.save(trainerInfo);
	    
	    boolean jwtSaveSuccess = saveTrainerInJwtService(trainerInfo,tempPassword);

	    if (!jwtSaveSuccess) {
	        return "Trainer registered in TrainerInfo table, but failed in JWT service.";
	    }
	    // Send email with temporary password
	    sendPassword( tempPassword,trainerInfo);

	    return "Trainer registered successfully! Temporary password sent ";
	}
	
	
	
	public boolean saveTrainerInJwtService(TrainerInfo trainerInfo,String tempPass) {
	    String jwtServiceUrl = "http://localhost:8082/register"; // Change to actual JWT service URL

	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Content-Type", "application/json");

	    Map<String, Object> requestBody = new HashMap<>();
	    requestBody.put("username", trainerInfo.getId());  // Use trainer ID as username
	    requestBody.put("password",tempPass); // Send unhashed password, let JWT service hash it
	    requestBody.put("role", trainerInfo.getRole()); 
	    requestBody.put("email", trainerInfo.getEmail()); 
	    

	    HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
	    
	    ResponseEntity<String> response = restTemplate.exchange(jwtServiceUrl, HttpMethod.POST, requestEntity, String.class);

	    return response.getStatusCode().is2xxSuccessful();
	}
	
	public boolean updateTrainerInJwtService(String trainerID, String newPassword) {
	    String jwtServiceUrl = "http://localhost:8082/resetPassword"; // Change to actual JWT service URL

	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Content-Type", "application/json");

	    Map<String, Object> requestBody = new HashMap<>();
	    requestBody.put("username", trainerID);
	    requestBody.put("password", newPassword); // Send unhashed password
	    

	    HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

	    ResponseEntity<String> response = restTemplate.exchange(jwtServiceUrl, HttpMethod.POST, requestEntity, String.class);

	    return response.getStatusCode().is2xxSuccessful();
	}
	
	
	

	

	

	



	

	
	
	
	
	
	
	

	
	
	
}