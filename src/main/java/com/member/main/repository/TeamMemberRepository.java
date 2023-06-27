package com.member.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.member.main.entity.TeamMember;


public interface TeamMemberRepository extends JpaRepository<TeamMember, Integer> {


	@Query("from TeamMember t where t.tId=:tId")
	public TeamMember getTeamMemberbytId(@Param(value="tId")Integer tId );

	
	@Query("from TeamMember tl where tl.email=:email and tl.password=:password")
	public TeamMember findTeamMemberByEmailPassword(@Param(value="email") String email, @Param(value="password")String password);

	
}
