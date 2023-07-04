package com.member.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.member.main.entity.TeamMember;


public interface TeamMemberRepository extends JpaRepository<TeamMember, Integer> {


	@Query("from TeamMember t where t.lId=:lId")
	public TeamMember getTeamMemberbylId(@Param(value="lId")Integer lId );

	
	@Query("from TeamMember tl where tl.email=:email and tl.password=:password")
	public TeamMember findTeamMemberByEmailPassword(@Param(value="email") String email, @Param(value="password")String password);

	
}
