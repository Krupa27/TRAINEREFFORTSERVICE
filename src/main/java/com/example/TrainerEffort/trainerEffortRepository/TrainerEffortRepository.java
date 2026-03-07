package com.example.TrainerEffort.trainerEffortRepository;
 
 
import java.time.LocalDate;
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
 
import com.example.TrainerEffort.effortDAO.Info;

import com.example.TrainerEffort.effortDAO.effortDAO;
 
import feign.Param;
 
public interface TrainerEffortRepository extends JpaRepository<effortDAO, Info>  {
	
	List<effortDAO> findByInfo_IDAndInfo_Date(String id, String date);

 
	List<effortDAO> findByStatus(String status);

	List<effortDAO> findByInfoID(String ID);

	List<effortDAO> findByInfoCohortCode(String CohortCode);

	//To find based on both Id and Status

    List<effortDAO> findByInfoIDAndStatus(String ID, String status);
 
    List<effortDAO> findByInfoCohortCodeAndStatus(String CohortCode, String status);

    @Query(value="Select * from effortdao e where e.date Between :startDate and :endDate and e.status='accept' ",nativeQuery=true)

    List<effortDAO> findEffortBasedOnRange(@Param("startDate") String startDate,@Param("endDate") String endDate);

 
   @Query(value="SELECT * FROM effortdao e WHERE MONTH( e.date )= :month"

   		+ " AND YEAR(e.date) = :year  and e.status='accept'", nativeQuery=true)

   List<effortDAO> findEffortByMonthAndYear(@Param("month") String month, @Param("year") String year);

   @Query(value="Select * From effortdao e where e.id=:Id and e.date Between :startDate and :endDate and e.status='accept' ",nativeQuery=true)

   List<effortDAO> filterEffortIdBasedOnRange(@Param("Id") String Id, @Param("startDate") String startDate,@Param("endDate") String endDate);

   @Query(value="Select * From effortdao e where e.id=:Id and MONTH( e.date )= :month"

   		+" AND YEAR(e.date) = :year  and e.status='accept'", nativeQuery=true)

   List<effortDAO> filterEffortIdBasedOnMonth(@Param("Id") String Id, @Param("month") String month, @Param("year") String year);

   @Query(value="Select * From effortdao e where e.cohort_code=:cohortCode and e.date Between :startDate and :endDate and e.status='accept' ",nativeQuery=true)

   List<effortDAO> filterEffortCohortBasedOnRange(@Param("cohortCode") String cohortCode, @Param("startDate") String startDate,@Param("endDate") String endDate);
 
   @Query(value="Select * From effortdao e where e.cohort_code=:cohortCode and MONTH( e.date )= :month"

   		+" AND YEAR(e.date) = :year  and e.status='accept'", nativeQuery=true)

    List<effortDAO> filterEffortCohortBasedOnMonth(@Param("cohortCode") String cohortCode, @Param("month") String month, @Param("year") String year);

}

 
