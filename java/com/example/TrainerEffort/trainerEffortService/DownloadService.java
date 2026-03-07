package com.example.TrainerEffort.trainerEffortService;
 
import java.util.ArrayList;

import java.util.List;

import java.util.Optional;
 
import org.springframework.stereotype.Service;
 
import com.example.TrainerEffort.effortDAO.Joined;

import com.example.TrainerEffort.effortDAO.TrainerInfo;

import com.example.TrainerEffort.effortDAO.effortDAO;

import com.example.TrainerEffort.feigns.FeignTrainer;

import com.example.TrainerEffort.trainerEffortRepository.TrainerEffortRepository;

import com.example.TrainerEffort.trainerEffortRepository.updateRepository;
 
@Service

public class DownloadService {

	private TrainerEffortRepository trainerEffortRepository;

	private updateRepository updteRepo;

	private FeignTrainer feignTrainer;
 
	public DownloadService(TrainerEffortRepository trainerEffortRepository, updateRepository updteRepo,

			FeignTrainer feignTrainer) {

		super();

		this.trainerEffortRepository = trainerEffortRepository;

		this.updteRepo = updteRepo;

		this.feignTrainer = feignTrainer;

	}
 
	//This is to download entire Trainer Data

	public List<Joined> downloadXL() {

    	List<Joined> effortList=new ArrayList<>();

		List<effortDAO> effort=trainerEffortRepository.findByStatus("accept");

		for(effortDAO eff:effort) {
 
			String id=eff.getInfo().getID();

			Optional<TrainerInfo> op=updteRepo.findById(id);

			if(op.isPresent()) {

				TrainerInfo tf=op.get();

				Joined j=new Joined(eff.getInfo().getCohortCode(),tf.getSkills().toString(), tf.getMappedType(), id,tf.getRole(),

						eff.getMode(), eff.getReason(), "hr",eff.getEffortHours(),eff.getInfo().getDate() );

				effortList.add(j);

			}

		}

		return effortList;

	}

	//To Download the data of the cohorts Specific to HR

	public List<Joined> downloadXLByHR(String hrId){

		List<String> cohorts=feignTrainer.getCohorts(hrId);

		List<Joined> effortList=new ArrayList<>();

		for(String cohort:cohorts) {

			List<effortDAO> effort=trainerEffortRepository.findByInfoCohortCodeAndStatus(cohort,"accept");

			for(effortDAO eff:effort) {

				String id=eff.getInfo().getID();

				Optional<TrainerInfo> op=updteRepo.findById(id);

				if(op.isPresent()) {

					TrainerInfo tf=op.get();

					Joined j=new Joined(eff.getInfo().getCohortCode(),tf.getSkills().toString(), tf.getMappedType(), id,tf.getRole(),

							eff.getMode(), eff.getReason(), "hr",eff.getEffortHours(),eff.getInfo().getDate() );

					effortList.add(j);

				}

			}

		}

		return effortList;

	}

	public List<Joined> downloadXLByTrainer(String Id){

		List<effortDAO> effort=trainerEffortRepository.findByInfoIDAndStatus(Id,"accept");

		//System.out.println(Id);

		List<Joined> effortList=new ArrayList<>();

		for(effortDAO eff:effort) {

			String id=eff.getInfo().getID();

			Optional<TrainerInfo> op=updteRepo.findById(id);

			if(op.isPresent()) {

				TrainerInfo tf=op.get();

				Joined j=new Joined(eff.getInfo().getCohortCode(),tf.getSkills().toString(), tf.getMappedType(), id,tf.getRole(),

						eff.getMode(), eff.getReason(), "hr",eff.getEffortHours(),eff.getInfo().getDate() );

				effortList.add(j);

			}

		}

		return effortList;

	}
 
	

	public List<Joined> getEffortByDate(String startDate, String endDate) {

		List<effortDAO> effort=effort=trainerEffortRepository.findEffortBasedOnRange(startDate, endDate);

		List<Joined> effortList=new ArrayList<>();

		for(effortDAO eff:effort) {

			String id=eff.getInfo().getID();

			Optional<TrainerInfo> op=updteRepo.findById(id);

			if(op.isPresent()) {

				TrainerInfo tf=op.get();

				Joined j=new Joined(eff.getInfo().getCohortCode(),tf.getSkills().toString(), tf.getMappedType(), id,tf.getRole(),

						eff.getMode(), eff.getReason(), "hr",eff.getEffortHours(),eff.getInfo().getDate() );

				effortList.add(j);

			}

		}

		return effortList;

	}

	public List<Joined> getEffortByMonthAndYear(String month, String year){

		List<effortDAO> effort=effort=trainerEffortRepository.findEffortByMonthAndYear(month, year);

		List<Joined> effortList=new ArrayList<>();

		for(effortDAO eff:effort) {

			String id=eff.getInfo().getID();

			Optional<TrainerInfo> op=updteRepo.findById(id);

			if(op.isPresent()) {

				TrainerInfo tf=op.get();

				Joined j=new Joined(eff.getInfo().getCohortCode(),tf.getSkills().toString(), tf.getMappedType(), id,tf.getRole(),

						eff.getMode(), eff.getReason(), "hr",eff.getEffortHours(),eff.getInfo().getDate() );

				effortList.add(j);

			}

		}

		return effortList;

	}

	public List<Joined> filterEffortIdBasedOnRange(String Id,String startDate,String endDate){

		List<effortDAO> effort=trainerEffortRepository.filterEffortIdBasedOnRange(Id,startDate,endDate);

		List<Joined> effortList=new ArrayList<>();

		for(effortDAO eff:effort) {

			String id=eff.getInfo().getID();

			Optional<TrainerInfo> op=updteRepo.findById(id);

			if(op.isPresent()) {

				TrainerInfo tf=op.get();

				Joined j=new Joined(eff.getInfo().getCohortCode(),tf.getSkills().toString(), tf.getMappedType(), id,tf.getRole(),

						eff.getMode(), eff.getReason(), "hr",eff.getEffortHours(),eff.getInfo().getDate() );

				effortList.add(j);

			}

		}

		return effortList;

	}

	public List<Joined> filterEffortIdBasedOnMonth(String Id,String month,String year){

		List<effortDAO> effort=trainerEffortRepository.filterEffortIdBasedOnMonth(Id,month,year);

		List<Joined> effortList=new ArrayList<>();

		for(effortDAO eff:effort) {

			String id=eff.getInfo().getID();

			Optional<TrainerInfo> op=updteRepo.findById(id);

			if(op.isPresent()) {

				TrainerInfo tf=op.get();

				Joined j=new Joined(eff.getInfo().getCohortCode(),tf.getSkills().toString(), tf.getMappedType(), id,tf.getRole(),

						eff.getMode(), eff.getReason(), "hr",eff.getEffortHours(),eff.getInfo().getDate() );

				effortList.add(j);

			}

		}

		return effortList;

	}

	public List<Joined> downloadXLByHRBasedOnRange(String hrId,String startDate,String endDate){

		List<String> cohorts=feignTrainer.getCohorts(hrId);

		List<Joined> effortList=new ArrayList<>();

		for(String cohort:cohorts) {

			List<effortDAO> effort=trainerEffortRepository.filterEffortCohortBasedOnRange(cohort, startDate, endDate);

			for(effortDAO eff:effort) {

				String id=eff.getInfo().getID();

				Optional<TrainerInfo> op=updteRepo.findById(id);

				if(op.isPresent()) {

					TrainerInfo tf=op.get();

					Joined j=new Joined(eff.getInfo().getCohortCode(),tf.getSkills().toString(), tf.getMappedType(), id,tf.getRole(),

							eff.getMode(), eff.getReason(), "hr",eff.getEffortHours(),eff.getInfo().getDate() );

					effortList.add(j);

				}

			}

		}

		return effortList;

	}
 
	public List<Joined> downloadXLByHRBasedOnMonth(String hrId, String month, String year) {

		List<String> cohorts=feignTrainer.getCohorts(hrId);

		List<Joined> effortList=new ArrayList<>();

		for(String cohort:cohorts) {

			List<effortDAO> effort=trainerEffortRepository.filterEffortCohortBasedOnMonth(cohort, month, year);

			for(effortDAO eff:effort) {

				String id=eff.getInfo().getID();

				Optional<TrainerInfo> op=updteRepo.findById(id);

				if(op.isPresent()) {

					TrainerInfo tf=op.get();

					Joined j=new Joined(eff.getInfo().getCohortCode(),tf.getSkills().toString(), tf.getMappedType(), id,tf.getRole(),

							eff.getMode(), eff.getReason(), "hr",eff.getEffortHours(),eff.getInfo().getDate() );

					effortList.add(j);

				}

			}

		}

		return effortList;

	}


}

 