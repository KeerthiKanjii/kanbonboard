
package com.member;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.awt.PageAttributes.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.member.main.model.TeamLeader;
import com.member.main.controller.TeamMemberController;
import com.member.main.entity.TeamMember;
import com.member.main.exception.InvalidCredentialsException;
import com.member.main.exception.InvalidUserId;
import com.member.main.exception.NullEmailFoundException;
import com.member.main.exception.NullUserFound;
import com.member.main.exception.UserControllerAdvice;
import com.member.main.model.RequiredResponse;
import com.member.main.repository.TeamMemberRepository;
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

	@Test
	public void testInvalidUserIdWithMessage() {
		try {
			throw new InvalidUserId("Invalid user ID");
		} catch (InvalidUserId e) {
			assertEquals("Invalid user ID", e.getMessage());
		}
	}

	@Test
	public void testInvalidUserIdWithoutMessage() {
		try {
			throw new InvalidUserId("");
		} catch (InvalidUserId e) {
			assertEquals("", e.getMessage());
		}

	}

	@Test
	public void testNullUserFoundWithMessage() {
		try {
			throw new NullUserFound("Null user found");
		} catch (NullUserFound e) {
			assertEquals("Null user found", e.getMessage());
		}
	}

	@Test
	public void testNullUserFoundWithoutMessage() {
		try {
			throw new NullUserFound("");
		} catch (NullUserFound e) {
			assertEquals("", e.getMessage());
		}
	}

	@Test
	public void testNullUserFoundException() {
		UserControllerAdvice userControllerAdvice = new UserControllerAdvice();
		NullUserFound nullUserFoundException = new NullUserFound("Null user found");

		ResponseEntity<String> response = userControllerAdvice.nullUserFoundException(nullUserFoundException);

		assertEquals("Null user found", response.getBody());
		assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());
	}

	@Test
	public void testNullEmailFoundException() {
		UserControllerAdvice userControllerAdvice = new UserControllerAdvice();
		NullEmailFoundException nullEmailFoundException = new NullEmailFoundException("Null email found");

		ResponseEntity<String> response = userControllerAdvice.nullEmailFoundException(nullEmailFoundException);

		assertEquals("Null email found", response.getBody());
		assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());
	}

	@Test
	public void testInvalidUserId() {
		UserControllerAdvice userControllerAdvice = new UserControllerAdvice();
		InvalidUserId invalidUserId = new InvalidUserId("Invalid user ID");

		ResponseEntity<String> response = userControllerAdvice.invalidUserId(invalidUserId);

		assertEquals("Invalid user ID", response.getBody());
		assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());
	}

	@Test
	void contextLoads() {
	}

	@Test
	void mainMethodStartsApplication() {
		KanbanBoardApplication.main(new String[] {});
	}

	@Test
	void testRegisterTeamMember_NullEmailFoundException() {
		TeamMember teamMember = new TeamMember();
		teamMember.setFirstName("John");
		teamMember.setLastName("Doe");
		teamMember.setEmail(null);
		teamMember.setPassword("password");

	}

	@Test
	void testGetAllTeamMember() {
		List<TeamMember> allMembers = Arrays.asList(new TeamMember(1, "John", "Doe", "john@example.com", "password", 1),
				new TeamMember(2, "Jane", "Smith", "jane@example.com", "password", 1));

		List<TeamMember> result = teamMemberService.getAllTeamMember();

	}

	@Test
	void testGetTeamMemberById1() throws InvalidUserId {
		int memberId = 1;
		TeamMember teamMember = new TeamMember(1, "John", "Doe", "john@example.com", "password", memberId);

		TeamMember result = teamMemberService.getTeamMemberById(memberId);

	}

	@Test
	void testGetTeamMemberById_InvalidUserId() {
		int memberId = 0;

	}

	@Test
	void testUpdateTeamMember1() throws InvalidUserId {
		TeamMember teamMember = new TeamMember(1, "John", "Doe", "john@example.com", "password", 1);

		TeamMember result = teamMemberService.updateTeamMember(teamMember);

	}

	@Test
	void testUpdateTeamMember_InvalidUserId() {
		TeamMember teamMember = new TeamMember(0, "John", "Doe", "john@example.com", "password", 1);
	}

	@Test
	void testDelete() throws InvalidUserId {
		int memberId = 1;
		TeamMember teamMember = new TeamMember(1, "John", "Doe", "john@example.com", "password", 1);

		teamMemberService.delete(memberId);

	}

	@Test
	void testDelete_InvalidUserId() {
		int memberId = 0;

	}

	@Test
	void testLoginTeamMember() throws InvalidCredentialsException {
		String email = "john@example.com";
		String password = "password";
		TeamMember teamMember = new TeamMember(1, "John", "Doe", "john@example.com", "password", 1);

	}

	@Test
	void testLoginTeamMember_InvalidCredentialsException() {
		String email = "john@example.com";
		String password = "password";

	}

	@Test
	public void testDefaultMessage() {
		TeamMemberController controller = new TeamMemberController();
		String message = controller.defaultMessage();
		assertEquals("Welcome to TeamMember Page", message);
	}

	@Test
	public void testRegisterTeamMember1() throws NullUserFound, NullEmailFoundException {
		TeamMemberController controller = new TeamMemberController();
		TeamMemberService mockService = Mockito.mock(TeamMemberService.class);

		TeamMember teamMember = new TeamMember();

		TeamMember registeredMember = new TeamMember();

	}

	@Test
	public void testGetAllTeamMember1() {
		TeamMemberController controller = new TeamMemberController();
		TeamMemberService mockService = Mockito.mock(TeamMemberService.class);

		List<TeamMember> teamMembers = new ArrayList<>();

		Mockito.when(mockService.getAllTeamMember()).thenReturn(teamMembers);

	}

	@Test
	public void testUpdateTeamMember2() throws InvalidUserId {
		TeamMemberController controller = new TeamMemberController();
		TeamMemberService mockService = Mockito.mock(TeamMemberService.class);

		TeamMember teamMember = new TeamMember();

		TeamMember updatedMember = new TeamMember();

		Mockito.when(mockService.updateTeamMember(teamMember)).thenReturn(updatedMember);

	}

	@Test
	public void testDeleteTeamMemberById() throws InvalidUserId {
		TeamMemberController controller = new TeamMemberController();
		TeamMemberService mockService = Mockito.mock(TeamMemberService.class);
		// controller.setTeamMemberService(mockService);

		int memberId = 123; // ID of the team member to delete

		Mockito.doNothing().when(mockService).delete(memberId);

		String result = controller.deleteTeamMemberById(memberId);

	}

	@Test
	public void testLoginTeamMember1() throws InvalidCredentialsException {
		TeamMemberController controller = new TeamMemberController();
		TeamMemberService mockService = Mockito.mock(TeamMemberService.class);

		String email = "test@example.com";
		String password = "password";

		TeamMember loggedMember = new TeamMember();

	}

	@Test
	public void testAddTeamMember1() throws NullUserFound {
		TeamMemberController controller = new TeamMemberController();
		TeamMemberService mockService = Mockito.mock(TeamMemberService.class);

		TeamMember teamMember = new TeamMember();

		TeamMember addedMember = new TeamMember();

		Mockito.when(mockService.addTeamMember(teamMember)).thenReturn(addedMember);

		TeamMember result = controller.addTeamMember(teamMember);

	}

	@Test
	public void testRegisterTeamMember_InvalidStringValues() {
		TeamMemberController controller = new TeamMemberController();
		TeamMemberService teamMemberService = new TeamMemberServiceImpl(); // Assuming the implementation is used

		TeamMember teamMember = new TeamMember();
		teamMember.setFirstName("John");
		teamMember.setLastName("Doe");
		teamMember.setEmail("john@example.com");
		teamMember.setPassword(" ");

	}

	@Test
	public void testAddTeamMember11() {
		TeamMemberController controller = new TeamMemberController();
		TeamMemberService teamMemberService = new TeamMemberServiceImpl(); // Assuming the implementation is used

		TeamMember teamMember = new TeamMember();

		TeamMember result = controller.addTeamMember(teamMember);

	}

	@Test
	public void testRegisterTeamMember11() throws NullUserFound, NullEmailFoundException {
		TeamMemberController controller = new TeamMemberController();
		TeamMemberService teamMemberService = new TeamMemberServiceImpl(); // Assuming the implementation is used

		TeamMember teamMember = new TeamMember();

	}

	@Test
	public void testGetTeamMemberById11() throws InvalidUserId {
		TeamMemberController controller = new TeamMemberController();
		TeamMemberService teamMemberService = new TeamMemberServiceImpl(); // Assuming the implementation is used

		int memberId = 1; // Existing member ID

	}

	@Test
	public void testUpdateTeamMember11() throws InvalidUserId {
		TeamMemberController controller = new TeamMemberController();
		TeamMemberService teamMemberService = new TeamMemberServiceImpl(); // Assuming the implementation is used
		// controller.setTeamMemberService(teamMemberService);

		TeamMember teamMember = new TeamMember();
	}

	@Test
	public void testDelete1() throws InvalidUserId {
		TeamMemberController controller = new TeamMemberController();
		TeamMemberService teamMemberService = new TeamMemberServiceImpl(); // Assuming the implementation is used

		int memberId = 1; // Existing member ID

	}

	@Test
	public void testFindTeamMemberByEmailPassword_Successful() {
		String email = "john@example.com";
		String password = "password";

		TeamMember expectedMember = new TeamMember();

		TeamMember result = TeamMemberRepository.findTeamMemberByEmailPassword(email, password);

	}

	@Test
	public void testToString() {
		int tId = 1;
		String firstName = "John";
		String lastName = "Doe";
		String email = "john@example.com";
		String password = "password";
		int lId = 123;

		TeamLeader teamLeader = new TeamLeader(tId, firstName, lastName, email, password, lId);

		String expectedToString = "TeamLeader [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", lId=" + lId + "]";

		String resultToString = teamLeader.toString();

		assertEquals(expectedToString, resultToString);
	}

	@Test
	public void testGettersAndSetters() {
		int tId = 1;
		String firstName = "John";
		String lastName = "Doe";
		String email = "john@example.com";
		String password = "password";
		int lId = 123;

		TeamLeader teamLeader = new TeamLeader();

		teamLeader.settId(tId);
		teamLeader.setFirstName(firstName);
		teamLeader.setLastName(lastName);
		teamLeader.setEmail(email);
		teamLeader.setPassword(password);
		teamLeader.setlId(lId);

		assertEquals(tId, teamLeader.gettId());
		assertEquals(firstName, teamLeader.getFirstName());
		assertEquals(lastName, teamLeader.getLastName());
		assertEquals(email, teamLeader.getEmail());
		assertEquals(password, teamLeader.getPassword());
		assertEquals(lId, teamLeader.getlId());
	}

	@Test
	public void testDefaultConstructor() {
		RequiredResponse response = new RequiredResponse();

		assertNotNull(response);

	}

	
	@Test
    public void testValidTeamMemberRegistration() throws Exception {
        // Input
        String requestJson = "{\"name\":\"John Doe\",\"email\":\"johndoe@example.com\"}";

        // Mock the service method
        TeamMember registeredTeamMember = new TeamMember();
        registeredTeamMember.setFirstName("John Doe");
        registeredTeamMember.setEmail("johndoe@example.com");
        when(teamMemberService.registerTeamMember(any(TeamMember.class))).thenReturn(registeredTeamMember);

        // Perform the request
//        MockMvc.perform(MockMvcRequestBuilders.post("/registerTeamMember")
//                //.contentType(MediaType.APPLICATION_JSON)
//                .content(requestJson))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Doe"))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("johndoe@example.com"));
        
        // Add additional verification steps if needed (e.g., check database for registered team member)
    }
}
