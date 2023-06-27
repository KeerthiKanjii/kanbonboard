package com.member.main.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.member.main.entity.TeamMember;
import com.member.main.exception.InvalidCredentialsException;
import com.member.main.exception.InvalidUserId;
import com.member.main.exception.NullEmailFoundException;
import com.member.main.exception.NullUserFound;

@Service
public interface TeamMemberService {

	public TeamMember addTeamMember(TeamMember teamMember) throws Exception;
	
	
	public TeamMember registerTeamMember(TeamMember teamMember) throws NullUserFound, NullEmailFoundException;

	public List<TeamMember> getAllTeamMember();
	

	public TeamMember getTeamMemberById(Integer id) throws InvalidUserId;

	public TeamMember updateTeamMember(TeamMember teamMember) throws InvalidUserId;

	
	
	public void delete(int tId) throws InvalidUserId;
	public TeamMember loginTeamMember(String email, String password) throws InvalidCredentialsException;


	
	
}
