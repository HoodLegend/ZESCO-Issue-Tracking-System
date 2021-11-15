package com.zesco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IssuesRepository extends JpaRepository<Issues, Long> {

	@Query("SELECT u FROM Issues u WHERE u.equipment =?1 ")
	Issues  findByEquipment(String equipment);
	
}
