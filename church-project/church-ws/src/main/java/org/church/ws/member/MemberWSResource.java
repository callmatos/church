package org.church.ws.member;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.church.core.exception.DefaultException;
import org.church.core.exception.common.ErrorHandler;
import org.church.core.service.GenericService;
import org.church.ws.common.CommonResource;
import org.church.ws.event.PaginatedRetrievedEvent;
import org.church.ws.event.ResourceCreatedEvent;
import org.church.ws.event.SingleResourceRetrievedEvent;
import org.church.ws.exception.MyResourceNotFoundException;
import org.church.ws.member.model.MemberDsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.church.model.entity.member.Member;
import com.church.model.member.service.MemberResult;
import com.church.model.member.service.MemberService;


@RestController
@RequestMapping(value = "/rest/member")
public class MemberWSResource extends CommonResource {

	// To Logger.
	private static final Logger LOGGER = LoggerFactory.getLogger(MemberWSResource.class);
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberWSValidator validator;
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public MemberDsResponse findAll() throws DefaultException{
		
		LOGGER.info("List all members inside db.");
		
		MemberDsResponse memberResponse = new MemberDsResponse();
		
		try{
			
			// Get all member present in DB.
			memberResponse.setMembers(this.memberService.findAll().getMembers());
			
			//Set the result OK.
			memberResponse.setResultCode(HttpStatus.OK.value());
			
			//Set the result message.
			memberResponse.setResultMessage(HttpStatus.OK.name());
			
		}catch(Exception e){
			
			LOGGER.error(e.getMessage(),e);
			
			//Set the Exception identified
			DefaultException exception = ErrorHandler.processExcpetion(e);
			memberResponse.setResultCode(exception.getErrorCode());
			memberResponse.setResultMessage(exception.getMessage());
		}
		
		return memberResponse;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Integer id){
		this.memberService.delete(id);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@ResponseBody
	public Member findOne(@PathVariable("id") Integer id,final HttpServletResponse response){
		
		//If necessary send some event enable this code.
		this.eventPublisher.publishEvent(new SingleResourceRetrievedEvent(this, response));
		return this.memberService.findById(id).getMember();
	}
	
	@RequestMapping(params = {"page","size"}, method = RequestMethod.GET)
	@ResponseBody
	public List<Member> findPaginated(	@RequestParam("page") final int page, 
										@RequestParam("size")final int size, final UriComponentsBuilder uriBuilder, 
										final HttpServletResponse response){
		
		final Page<Member> resultPage = this.memberService.findPaginated(page, size);
		
		if(page > resultPage.getTotalPages()){
			throw new MyResourceNotFoundException();
		}
		
		//If necessary send some event enable this code.
		this.eventPublisher.publishEvent(new PaginatedRetrievedEvent<Member>(Member.class, uriBuilder, response, page, resultPage.getTotalPages(), size));
		
		return resultPage.getContent();
	}
	
	@RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody final Member resource, final HttpServletResponse response) {
		
		MemberResult toCreate = new MemberResult(resource);
		
        try {
			toCreate = this.memberService.createMember(toCreate);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyResourceNotFoundException();
		}

        eventPublisher.publishEvent(new ResourceCreatedEvent<Member>(this, toCreate.getMember(),response));
    }

	@Override
	public GenericService<?, ?, Member> getService() {
		
		return this.memberService;
	}
	
}
