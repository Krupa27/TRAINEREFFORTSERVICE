package com.example.TrainerEffort.trainerEffortcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.TrainerEffort.effortDAO.Info;
import com.example.TrainerEffort.effortDAO.Joined;
import com.example.TrainerEffort.effortDAO.effortDAO;
import com.example.TrainerEffort.effortDAO.TrainerInfo;
import com.example.TrainerEffort.trainerEffortService.TrainerEffortService;

@RestController
@CrossOrigin("*")
public class TrainerEffortcontroller {
	
	@Autowired
	TrainerEffortService sob;
	
	
	@GetMapping("/hi")
	public String hi() {
		System.out.println("logged");
		return "hi";
	}
	
	
	@GetMapping("/getrepo")
	public List<TrainerInfo> findAll(){
		return sob.findAll();
	}
	
//	@GetMapping("/download")
//	public List<Joined> getXL(){
//		return sob.downloadXL();
//	}
	
	@GetMapping("/checkTrainer/{id}")
	public boolean  findById(@PathVariable String id) {
		System.out.println(id);
		return sob.findById(id);
	}
 
	
	@PostMapping("/addTrainer")
	public String addTrainer(@RequestBody TrainerInfo td) {
		return sob.addTrainer(td);
	}
	

//	@DeleteMapping("/deleteTrainer/{id}")
//	public String deleteTrainer(@PathVariable String id) {
//		return sob.deleteTrainer(id);
//	}
	
	
	@GetMapping("/searchTrainer/{id}")
	public TrainerInfo searchTrainer(@PathVariable String id) {
		return sob.searchTrainer(id);
	}
	
	@GetMapping("/search")
    public List<TrainerInfo> searchTrainers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer minExp,
            @RequestParam(required = false) Integer maxExp,
            @RequestParam(required = false) String mappedType,
            @RequestParam(required = false) String role,
            @RequestParam(required = false) String gender,
            @RequestParam(required = false) List<String> skills
    ) {
		System.out.println(name+" "+ minExp+" "+ maxExp+" "+mappedType+" "+role+" "+gender+" "+skills);
        return sob.searchTrainers(name, minExp, maxExp, mappedType, role, gender, skills);
    }
	@PostMapping("/effortupdate")
	public String updateEffort(@RequestBody effortDAO ob) {
		System.out.println(ob.getMode()+" "+ob.getReason());
		
		return sob.geteffort(ob);
	}
	
	
	@PostMapping("/updateInfo")
	public TrainerInfo updateInfo(@RequestBody TrainerInfo ob) {
		return sob.updateTrainerInfo(ob);
	}
	
	
	@PostMapping("/initiatePasswordReset")
	public String initiatePasswordReset(@RequestBody Map<String, String> payload) {
	    String trainerId = payload.get("trainerId");
	    return sob.initiatePasswordReset(trainerId); // Sends OTP
	}
	
	
	@PostMapping("/resetPassword")
	public String resetPassword(@RequestBody Map<String, String> payload) {
	    String trainerId = payload.get("trainerId");
	    String newPassword = payload.get("newPassword");
	    String enteredOtp = payload.get("enteredOtp");
	    return sob.resetTrainerPassword(trainerId, newPassword,enteredOtp);
	}
	
	@PostMapping("/registerTrainer")
	public String registerTrainer(@RequestBody TrainerInfo trainerInfo) {
	    return sob.registerTrainer(trainerInfo);
	}
	
	
	
	@GetMapping("/pendingApprovals/{hrId}")
    public List<effortDAO> pendingApprovals(@PathVariable String hrId){
    	return sob.pendingApprovals(hrId);
    }
	
	
    @GetMapping("approvalStatus/{id}")
    public List<effortDAO> approvalsStatus(@PathVariable String id) {
    	return sob.approvalsStatus(id);
    }
    
    @PostMapping("/updateStatus/")
    public String updateStatus(@RequestBody effortDAO eff) {
    	return sob.updateStatus(eff);
    }

	


}
