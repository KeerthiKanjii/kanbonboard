package com.member.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.member.main.entity.TeamMember;
import com.member.main.exception.InvalidCredentialsException;
import com.member.main.exception.InvalidUserId;
import com.member.main.exception.NullEmailFoundException;
import com.member.main.exception.NullUserFound;
import com.member.main.service.TeamMemberService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/TeamMember")
public class TeamMemberController {

	@Autowired
	TeamMemberService teamMemberService;

	@GetMapping("/")
	public String defaultMessage() {
		return "Welcome to TeamMember Page";
	}

	@PostMapping("/registerTeamMember")
	public TeamMember registerTeamMember(@RequestBody TeamMember teamMember)
			throws NullUserFound, NullEmailFoundException {
		return teamMemberService.registerTeamMember(teamMember);
	}

	@GetMapping("/allTeamMember")
	public List<TeamMember> getAllTeamMember() {
		return teamMemberService.getAllTeamMember();
	}

	@PutMapping("/updateTeamMember")
	public TeamMember updateTeamMember(@RequestBody TeamMember teamMember) throws InvalidUserId {
		return teamMemberService.updateTeamMember(teamMember);
	}

	@DeleteMapping("/delete/{lId}")
	public String deleteTeamMemberById(@PathVariable int lId) throws InvalidUserId {
		try {
			teamMemberService.delete(lId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Deleted Id = " + lId + " Data";
	}

	@GetMapping("/loginTeamMember/{email}/{password}")
	public TeamMember loginTeamMember(@PathVariable String email, @PathVariable String password)
			throws InvalidCredentialsException {
		return teamMemberService.loginTeamMember(email, password);
	}

	@PostMapping("/addTeamMember")
	public TeamMember addTeamMember(@RequestBody TeamMember teamMember) {
		try {
			return teamMemberService.addTeamMember(teamMember);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; // or handle the exception accordingly
	}
}
