package com.church.test.member;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.church.model.applicationConf.ApplicationConfig;
import com.church.model.entity.Address;
import com.church.model.entity.Member;
import com.church.model.service.member.MemberResult;
import com.church.model.service.member.MemberService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
public class TestMemberService {

	@Autowired
	private MemberService serviceMember;
	
	public MemberResult memberTosave;
	
	@Before
	public void init(){
		
		memberTosave = new MemberResult();
		
		Assert.assertNotNull(memberTosave);
	}
	
	@Test
	public void saveMember(){
		
		Member me = new Member("Rodrigo de Matos Araujo");
		me.setAddress(new Address("Av.Barcelos","1038","Amazonas","Brazil"));
		
		// Put member in MemberResult to save.
		memberTosave.setMember(me);
		
		MemberResult aux = null; 
		
		try {
			aux = this.serviceMember.createMember(memberTosave);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(aux != null)
			Assert.assertTrue(aux.getMember().getId() > 2);
		else
			Assert.fail();
		
	}
	
	@After
	public void removeMember(){
		MemberResult result =  serviceMember.findAll();
		
		for (Member mm : result.getMembers()) {
			System.out.println("Name: "+mm.getName());
		}
		
		Assert.assertFalse(result.getMembers().isEmpty());
	}

}
