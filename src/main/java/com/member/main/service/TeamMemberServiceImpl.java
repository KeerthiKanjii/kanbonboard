package com.member.main.service;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.member.main.entity.TeamMember;
import com.member.main.exception.InvalidCredentialsException;
import com.member.main.exception.InvalidUserId;
import com.member.main.exception.NullEmailFoundException;
import com.member.main.exception.NullUserFound;
import com.member.main.repository.TeamMemberRepository;

@Service
public class TeamMemberServiceImpl implements TeamMemberService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TeamMemberServiceImpl.class);

	@Autowired
	TeamMemberRepository teamMemberRepository;

	@Override
	public TeamMember addTeamMember(TeamMember teamMember) throws Exception {
		LOGGER.info("Adding a new team member: {}", teamMember);
		return teamMemberRepository.save(teamMember);
	}

	@Override
	public TeamMember registerTeamMember(TeamMember teamMember) throws NullUserFound, NullEmailFoundException {
		LOGGER.info("Registering a new team member: {}", teamMember);
		if (teamMember.getEmail() == null || teamMember.getEmail().isEmpty()) {
			throw new NullEmailFoundException("Email id cannot be null");
		} else if (teamMember.getFirstName().isEmpty() || teamMember.getLastName().isEmpty()
				|| teamMember.getEmail().isEmpty() || teamMember.getPassword().isEmpty()) {
			throw new NullUserFound("Error: Null value is not accepted.");
		} else if (teamMember.getFirstName().equals("string") || teamMember.getLastName().equals("string")
				|| teamMember.getPassword().equals("string") || teamMember.getEmail().equals("string")) {
			throw new NullUserFound("Error: Null value is not accepted.");
		}
		return teamMemberRepository.save(teamMember);
	}

	@Override
	public List<TeamMember> getAllTeamMember() {
		LOGGER.info("Fetching all team members");
		return teamMemberRepository.findAll();
	}

	@Override
	public TeamMember getTeamMemberById(Integer id) throws InvalidUserId {
		LOGGER.info("Fetching team member by ID: {}", id);
		if (id == null || id == 0) {
			throw new InvalidUserId("Invalid user ID: " + id);
		}
		return teamMemberRepository.getTeamMemberbytId(id);
	}

	@Override
	public TeamMember updateTeamMember(TeamMember teamMember) throws InvalidUserId {
		LOGGER.info("Updating team member: {}", teamMember);
		Integer tId = teamMember.gettId();
		if (tId == null || tId == 0) {
			throw new InvalidUserId("Invalid user ID: " + tId);
		}

		TeamMember existingMember = getTeamMemberById(tId);

		if (existingMember == null) {
			throw new InvalidUserId("No TeamMember exists with the given ID: " + tId);
		}

		return teamMemberRepository.save(teamMember);
	}

	@Override
	public void delete(int tId) throws InvalidUserId {
		LOGGER.info("Deleting team member with ID: {}", tId);
		if (tId == 0) {
			throw new InvalidUserId("Invalid user ID: " + tId);
		}

		TeamMember existingMember = getTeamMemberById(tId);

		if (existingMember == null) {
			throw new InvalidUserId("No TeamMember exists with the given ID: " + tId);
		}

		teamMemberRepository.deleteById(tId);
	}

	@Override
	public TeamMember loginTeamMember(String email, String password) throws InvalidCredentialsException {
		LOGGER.info("Logging in team member with email: {}", email);
		if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
			throw new InvalidCredentialsException("Email or Password cannot be empty");
		}

		TeamMember tl = teamMemberRepository.findTeamMemberByEmailPassword(email, password);

		if (tl == null) {
			throw new InvalidCredentialsException("Invalid email or password");
		} 
		

		return tl;
	}

}
