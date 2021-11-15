package com.zesco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EquipmentsRepository extends JpaRepository<Equipments, Long> {

	@Query("SELECT u FROM Equipments u WHERE u.name =?1 ")
	Equipments  findByName(String name);
	
}
