package com.member.main.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static  String teamMember = null;

	@Autowired
	TeamMemberService teamMemberService;

	private final Logger mylogs = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/")
	public String defaultMessage() {
		return "welcome to TeamMember Page";
	}
	
 
	@PostMapping("/registerTeamv")
	public TeamMember registerTeamMember(@RequestBody TeamMember teamMember)
			throws NullUserFound, NullEmailFoundException {

		mylogs.info("Inside registerTeamMember: {}", teamMember);

		return teamMemberService.registerTeamMember(teamMember);

	}

	@GetMapping("/allTeamMember") // localhost:7001/TeamMemberr/allTeamMember
	public List<TeamMember> getAllTeamMember() {
		mylogs.info("Inside getAllTeamMember");

		return teamMemberService.getAllTeamMember();
	}

	@PutMapping("/updateTeamMember") // localhost:7001/TeamMember/updateTeamMember
	public TeamMember updateTeamMember(@RequestBody TeamMember teamMember) throws InvalidUserId {
		mylogs.info("Inside updateTeamMember: {}", teamMember);

		return teamMemberService.updateTeamMember(teamMember);
	}

	@DeleteMapping("/delete/{tId}") // localhost:7001/TeamMember/delete/2
	public String deleteTeamMemberById(@PathVariable int tId) throws InvalidUserId {

		mylogs.info("Inside deleteTeamMemberById: {}", tId);
		try {
			teamMemberService.delete(tId);


		} catch (Exception e) {

			mylogs.error("Error occurred while deleting team member with ID: {}", tId, e);

			e.printStackTrace();
		}
		return "Deleted Id = " + tId + " Data";
	}

	@GetMapping("/loginTeamLeader/{email}/{password}") // localhost:7001/TeamLeader/loginTeamLeader/anu@gmail.com/vijju
	public TeamMember loginTeamMember(@PathVariable String email, @PathVariable String password)
			throws InvalidCredentialsException {
		mylogs.info("Inside loginTeamMember: email={}, password={}", email, password);

		return teamMemberService.loginTeamMember(email, password);
	}

	@PostMapping("/registerTeamMember")
	public TeamMember addTeamMember(@RequestBody TeamMember teamMember) {
		mylogs.info("Inside addTeamMember: {}", teamMember);

		try {
			return teamMemberService.addTeamMember(teamMember);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			mylogs.error("Error occurred while adding team member: {}", teamMember, e);

			e.printStackTrace();
		}
		return teamMember;
	}
}
