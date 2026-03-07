package com.example.TrainerEffort.trainerEffortRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.TrainerEffort.effortDAO.TrainerInfo
;
@Repository
public interface updateRepository extends JpaRepository<TrainerInfo, String>,JpaSpecificationExecutor<TrainerInfo> {
	@Modifying
	@Query("UPDATE TrainerInfo t SET t.password = :password WHERE t.ID = :trainerId")
	void updatePassword(@Param("trainerId") String trainerId, @Param("password") String password);


}
