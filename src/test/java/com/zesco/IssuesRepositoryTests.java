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
public class IssuesRepositoryTests {

	@Autowired
	private IssuesRepository repo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateIssue() {
		Issues issues = new Issues();
		issues.setEquipment("Lights");
		issues.setDescription("The lights are not working");
		issues.setLocation("Riverside");
		
		Issues savedIssue = repo.save(issues);
		
		Issues existIssues = entityManager.find(Issues.class, savedIssue.getId());
		
		assertThat(existIssues.getEquipment()).isEqualTo(issues.getEquipment());
		
	
	}
	
	@Test
	public void testFindEquipment() {
		String equipment ="Lights";
		
		Issues issue =repo.findByEquipment(equipment);
		
		assertThat(issue).isNotNull();
	}
}
