package org.church.ws.event;

import javax.servlet.http.HttpServletResponse;

import org.church.core.dao.GenericDaoObject;
import org.springframework.context.ApplicationEvent;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * This class represent the Event of Paginated for some type Entity.
 * 
 * @author charles.ma
 *
 * @param <DOMAIN>
 */
public final class PaginatedRetrievedEvent<DOMAIN extends GenericDaoObject> extends ApplicationEvent {


	private static final long serialVersionUID = -5836942009640629886L;

	private final UriComponentsBuilder uriBuilder;
	
	private final HttpServletResponse response;
	
	private final int page;
	
	private final int totalPages;
	
	private int pageSize;
	

	public PaginatedRetrievedEvent(final Class<DOMAIN> clazz, final UriComponentsBuilder uriBuilderToSet, final HttpServletResponse responseToSet, final int pageToSet, final int totalPagesToSet, final int pageSizeToSet) {
		super(clazz);
		
		this.uriBuilder = uriBuilderToSet;
		this.response = responseToSet;
		this.page = pageSizeToSet;
		this.totalPages = totalPagesToSet;
		this.pageSize = pageSizeToSet;
	}
	
	// API
	public final UriComponentsBuilder getUriBuilder(){
		return this.uriBuilder;
	}
	
	public final HttpServletResponse getResponse(){
		return this.response;
	}
	
	public final int getPage(){
		return this.page;
	}
	
	public final int getTotalPages(){
		return this.totalPages;
	}
	
	public final int getPageSize(){
		return this.pageSize;
	}
	
	/**
     * The object on which the Event initially occurred.
     * 
     * @return The object on which the Event initially occurred.
     */
    @SuppressWarnings("unchecked")
    public final Class<DOMAIN> getClazz() {
        return (Class<DOMAIN>) getSource();
    }
    
	@Override
	public String toString() {
		return super.toString();
	}

}
