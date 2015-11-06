package org.church.ws.test;

import java.util.Arrays;

import org.apache.commons.httpclient.HttpStatus;
import org.church.ws.config.ApplicationConfigWS;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.church.model.applicationConf.ApplicationConfig;
import com.church.model.applicationConf.PersistenceContext;
import com.church.model.member.entity.Address;
import com.church.model.member.entity.Member;
import com.church.model.member.repository.MemberRepository;
import com.church.model.member.service.MemberResult;
import com.jayway.restassured.RestAssured;

/**
 * Unit test for simple App.
 */
@RunWith( SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration( classes = {ApplicationConfigWS.class,ApplicationConfig.class,PersistenceContext.class})
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class AppTest
{

	@Value("${local.server.port}")   // 6
    int port;
	
	@Autowired
	private MemberRepository repositoryMember;
	
	//Member to save.
	public MemberResult memberObjectTosave;
	
	//List of member tosave
	Member one;
	Member two;
	Member three;
	
	@Before
	public void setUp(){
		
		// LIst of object to save in db.
		one = new Member("Ricardo Silva");
		one.setEmail("ricardo.silva@gmail.com");
		one.setAddress(new Address("Av. Paulista","10355","SP","Brazil"));
		
		two = new Member("Flavia Silva");
		two.setEmail("flavia.silva@gmail.com");
		two.setAddress(new Address("Av. Paulista","10355","SP","Brazil"));
		
		three = new Member("Juliana Oliveira");
		three.setEmail("juliana.ol@gmail.com");
		three.setAddress(new Address("Av. Paulista","10355","SP","Brazil"));
		
		repositoryMember.deleteAll();
		repositoryMember.save(Arrays.asList(one,two,three));
		
		RestAssured.port = port;
	}
	
	@Test
	public void canFecthRicado(){
	
		Integer ric = one.getId();

		RestAssured.when().
			get("/member/{id}",ric).
			then().statusCode(HttpStatus.SC_OK).
			body("name", Matchers.is("Ricardo Silva")).
			body("id",Matchers.is(ric));
	}
	
	@Test
	public void canDeleteRicado(){
	
		Integer ric = one.getId();

		RestAssured.when().
			delete("/member/{id}",ric);
	}
	
	@Test
	public void canDonwexist(){
	
		RestAssured.when().
			get("/member/xassp");
	}
}
