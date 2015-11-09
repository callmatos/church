package com.church.test.member;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.church.model.applicationConf.ApplicationConfig;
import com.church.model.entity.member.Address;
import com.church.model.entity.member.Member;
import com.church.model.member.repository.MemberRepository;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class TestMemberCrud {

	@Autowired
	MemberRepository repository;
	
	//Member add in database.
	Member memberResult;
	
	private final static  String  name = "Maria Auxiliadora de Matos Araujo";
	
	@Test
	public void isRepositoryOK(){
		Assert.assertNotNull(repository);
	}
	
	@Test
	public void saveMemberTest(){
		
		//Member to add in database
		Member aux = new Member(name);
		aux.setEmail("callmatos@gmail.com");
		
		//Put date
		SimpleDateFormat dt = new SimpleDateFormat("MM/dd/yyyy");
		aux.setAddress(new Address("Av.Barcelos", "1038", "Amazonas", "Brazil"));
		
		//save the mamber
		memberResult = repository.save(aux);
		
		Assert.assertTrue(memberResult.equals(repository.findByName(memberResult.getName())));
	}
	
	@Test
	public void changeMemberDataTest(){
		
		Member resolveMemberToChange = this.repository.findByName(name);
		resolveMemberToChange.setName("Dario Nascimento vieralves");
		this.repository.save(resolveMemberToChange);
		Assert.assertNotNull(resolveMemberToChange);
	}
	
	@Test
	public void listMemberTest(){
		
		List<Member> members = repository.findAll();
		Assert.assertFalse(members.isEmpty());
	}
	
//	@Test
//	public void removeTest(){
//		
//		memberResult = repository.findByName("Dario Nascimento vieralves");
//		
//		repository.delete(memberResult);
//	}
	
}
