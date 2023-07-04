
package com.member;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import com.member.main.entity.TeamMember;
import com.member.main.exception.InvalidCredentialsException;
import com.member.main.exception.InvalidUserId;
import com.member.main.exception.NullEmailFoundException;
import com.member.main.service.TeamMemberService;
import com.member.main.service.TeamMemberServiceImpl;

class TeamMemberServiceImplTest {
	@Mock
	private TeamMemberService teamMemberService;

@InjectMocks
	private TeamMemberServiceImpl teamMemberServiceImpl;

	public TeamMemberServiceImplTest() {
		MockitoAnnotations.openMocks(this);
	}

@Test
void testAddTeamMember() throws Exception {
	TeamMember teamMember = new TeamMember();
	teamMember.setFirstName("John Doe");
	teamMember.setLastName("Doe");
		teamMember.setEmail("jane@example.com");
		teamMember.setPassword("js123");

	Mockito.when(teamMemberService.addTeamMember(teamMember)).thenReturn(teamMember);

	}

	@Test
	void testRegisterTeamMember() throws Exception {
		TeamMember teamMember = new TeamMember();
		teamMember.setFirstName("Jane Smith");
		teamMember.setLastName("Smith");
		teamMember.setEmail("jane@example.com");
		teamMember.setPassword("js123");

		Mockito.when(teamMemberService.registerTeamMember(teamMember)).thenReturn(teamMember);

	}

	@Test
	void testGetAllTeamMembers() {
		List<TeamMember> allMembers = Arrays.asList(
			new TeamMember(1, "keerthi", "kanji", "kanjikeerthi@gmail.com", "keerthi", 4),
				new TeamMember(1, "keerthi", "kanji", "kanjikeerthi@gmail.com", "keerthi", 4));

		Mockito.when(teamMemberService.getAllTeamMember()).thenReturn(allMembers);
}

	@Test
	void testGetTeamMemberById() throws InvalidUserId {
	int memberId = 1;
	TeamMember teamMember = new TeamMember(1, "keerthi", "keerthi", "keerthi", "keertgu", 4);

	Mockito.when(teamMemberService.getTeamMemberById(memberId)).thenReturn(teamMember);

	}

	@Test
	void testUpdateTeamMember() throws InvalidUserId {
		int memberId = 1;
		TeamMember teamMember = new TeamMember(1, "keerthi", "keerthi", "keerthi", "keertgu", 4);

	Mockito.when(teamMemberService.updateTeamMember(teamMember)).thenReturn(teamMember);

		assertEquals("keerthi", teamMember.getFirstName());
	assertEquals("keerthi", teamMember.getLastName());
		assertEquals("keerthi", teamMember.getEmail());
		assertEquals("keertgu", teamMember.getPassword());

	}
	 @Test
	    public void testInvalidCredentialsExceptionWithMessage() {
	        try {
	            throw new InvalidCredentialsException("Invalid username or password");
	        } catch (InvalidCredentialsException e) {
	            assertEquals("Invalid username or password", e.getMessage());
	        }
	    }

	    @Test
	    public void testInvalidCredentialsExceptionWithoutMessage() {
	        try {
	            throw new InvalidCredentialsException("");
	        } catch (InvalidCredentialsException e) {
	            assertEquals("", e.getMessage());
	        }
	    }
	
	    @Test
	    public void testNullEmailFoundExceptionWithMessage() {
	        try {
	            throw new NullEmailFoundException("Null email found");
	        } catch (NullEmailFoundException e) {
	            assertEquals("Null email found", e.getMessage());
	        }
	    }

	    @Test
	    public void testNullEmailFoundExceptionWithoutMessage() {
	        try {
	            throw new NullEmailFoundException("");
	        } catch (NullEmailFoundException e) {
	            assertEquals("", e.getMessage());
	        }
	    }
	}








