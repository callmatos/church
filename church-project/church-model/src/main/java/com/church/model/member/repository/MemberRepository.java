package com.church.model.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.church.model.entity.member.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {

	/**
	* Find the Member with the given name. This method will be translated into a query using the
	* {@link javax.persistence.NamedQuery} annotation at the {@link Member} class.
	*
	* @param name
	* @return
	*/
	Member findByName(String name);
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	@Query("SELECT m from Member m WHERE LOWER(m.name) = LOWER(:name)")
	Member retrieveByName(@Param("name") String name);
	
}
