package com.church.model.entity.security;

import java.util.Collection;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.church.model.entity.Member;

@Data
@EqualsAndHashCode(callSuper=true)
public class ChurchUserSession extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5722142701567565245L;

	public ChurchUserSession(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
	}
	

	public ChurchUserSession(Member member, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<GrantedAuthority> authorities){
		super(member.getUserName(),member.getPassword(),true,accountNonExpired,credentialsNonExpired,accountNonLocked,authorities);
	}
	
}
