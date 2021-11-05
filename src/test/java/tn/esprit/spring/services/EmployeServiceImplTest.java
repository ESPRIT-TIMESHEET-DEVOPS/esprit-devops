//package tn.esprit.spring.services;
//
//import static org.junit.Assert.assertArrayEquals;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
////import static org.junit.Assert.assertEquals;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import tn.esprit.spring.entities.Contrat;
//import tn.esprit.spring.entities.Employe;
//import tn.esprit.spring.entities.Role;
//import tn.esprit.spring.services.IEmployeService;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//class EmployeServiceImplTest {
//
//	@Autowired
//	IEmployeService iemployeservice;
//
//
//
//	@Test
//	void testAjouterEmploye() {
//	Employe employe = new Employe("alex", "bernar", "alex.bernar@gmail.com",true,Role.INGENIEUR);
//	 int i = iemployeservice.ajouterEmploye(employe);
//	 assertEquals(16, i);
//	}
//
//
//	@Test
//	void testAjouterEmploye2() {
//	Employe employe = new Employe("alex", "bernar", "alex.bernar@gmail.com",true,Role.INGENIEUR);
//	 int i = iemployeservice.ajouterEmploye(employe);
//	 assertNotEquals(20, i);
//	}
//
//
//	/*@Test
//	void testajouterContrat() {
//	Contrat contrat = new Contrat(new Date() ,"svp",1500);
//	int i = iemployeservice.ajouterContrat(contrat);
//	assertEquals(1, i);
//	}
//
//	@Test
//	void testajouterContrat2() {
//	Contrat contrat = new Contrat(new Date() ,"svp",1500);
//	int i = iemployeservice.ajouterContrat(contrat);
//	assertNotEquals(3, i);
//	}*/
//
//	@Test
//	void getEmployePrenomById() {
//		assertEquals("bernar", iemployeservice.getEmployePrenomById(15));
//	}
//
//
//	@Test
//	void getEmployePrenomById2() {
//		assertNotEquals("bernar2", iemployeservice.getEmployePrenomById(1));
//	}
//
//	@Test
//	void getNombreEmployeJPQL() {
//		assertEquals(20, iemployeservice.getNombreEmployeJPQL());
//	}
//
//
//	@Test
//	void getNombreEmployeJPQL2() {
//		assertNotEquals(17, iemployeservice.getNombreEmployeJPQL());
//	}
//
//	/*@Test
//	void getAllEmployeNamesJPQL() {
//		//String[] tab  = { "alex" , "alex"};
//
//		assertEquals(Arrays.asList("alex", "alex").toArray(), iemployeservice.getAllEmployeNamesJPQL());
//	}*/
//
//
//}
