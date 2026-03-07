package com.example.TrainerEffort.trainerEffortcontroller;
 
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
import com.example.TrainerEffort.effortDAO.Joined;
import com.example.TrainerEffort.trainerEffortService.DownloadService;
 
@RestController
@CrossOrigin("*")
public class DownloadController {
	private DownloadService downloadService;
	
	public DownloadController(DownloadService downloadService) {
		super();
		this.downloadService = downloadService;
	}
 
	@GetMapping("/download")
	public List<Joined> getXL(){
		return downloadService.downloadXL();
	}
	
	@GetMapping("/download/{hrId}")
	public List<Joined> getXL(@PathVariable String hrId){
		return downloadService.downloadXLByHR(hrId);
	}
	
	@GetMapping("/downloadByTrainerId/{Id}")
	public List<Joined> getXLTrainerId(@PathVariable String Id){
		return downloadService.downloadXLByTrainer(Id);
	}
	
	@GetMapping("/effortBetweenDates")
	public List<Joined> getEffortByDate(@RequestParam String startDate, @RequestParam String endDate){
		return downloadService.getEffortByDate(startDate,endDate);
	}
	
	@GetMapping("/effortByMonthAndYear")
	public List<Joined> getEffortByMonthAndYear(@RequestParam String month,@RequestParam String year){
		//System.out.println("Hello");
		return downloadService.getEffortByMonthAndYear(month, year);
	}
	
	@GetMapping("/effortIdBasedOnRange")
	public List<Joined> getEffortIdBasedOnRange(@RequestParam String Id,@RequestParam String startDate,@RequestParam String endDate){
		return downloadService.filterEffortIdBasedOnRange(Id, startDate, endDate);
	}
	
	@GetMapping("/effortIdBasedOnMonth")
	public List<Joined> getEffortIdBasedOnMonth(@RequestParam String Id,@RequestParam String month,@RequestParam String year){
		return downloadService.filterEffortIdBasedOnMonth(Id, month, year);
	}
	
	@GetMapping("/effortHrBasedOnRange")
	public List<Joined> getEffortHrBasedOnRange(@RequestParam String hrId,@RequestParam String startDate,@RequestParam String endDate){
		return downloadService.downloadXLByHRBasedOnRange(hrId, startDate, endDate);
	}
	
	@GetMapping("/effortHrBasedOnMonth")
	public List<Joined> getEffortHrBasedOnMonth(@RequestParam String hrId,@RequestParam String month,@RequestParam String year){
		return downloadService.downloadXLByHRBasedOnMonth(hrId, month, year);
	}
	
	
}
 
 