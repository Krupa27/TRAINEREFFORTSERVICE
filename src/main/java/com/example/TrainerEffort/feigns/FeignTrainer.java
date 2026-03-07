package com.example.TrainerEffort.feigns;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.TrainerEffort.effortDAO.Info;

@FeignClient("HRSERVICE")
public interface FeignTrainer {
	 @PostMapping("/validateCohort")
	   public boolean validate(@RequestBody String tid);
	 
	 
	 @PostMapping("/validateTrainer")
	   public boolean validateTr(@RequestParam String id,@RequestParam String cid);
	 
	 
	 @GetMapping("/getCohorts/{hrId}")
	   public List<String> getCohorts(@PathVariable String hrId);
	 

}
