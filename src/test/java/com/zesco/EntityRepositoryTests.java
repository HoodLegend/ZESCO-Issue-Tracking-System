package com.zesco;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;


@DataJpaTest
@AutoConfigureTestDatabase(replace= Replace.NONE)
@Rollback(false)
public class EntityRepositoryTests {

	@Autowired
	private EquipmentsRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateEquipment() {
		Equipments equipments = new Equipments();
		equipments.setName("Lights");
		equipments.setDescription(" These are installed using the Latest LED Technology");
	
		Equipments savedEquipment = repo.save(equipments);
		
		Equipments existEquipment = entityManager.find(Equipments.class, savedEquipment.getId());
		
		assertThat(existEquipment.getName()).isEqualTo(equipments.getName());
	
	}
	
	@Test
	public void testFindEquipmentsbyName() {
		String name = "Heart";
		
	Equipments equipments =	repo.findByName(name);
	
	assertThat(equipments).isNotNull();
	}
}
