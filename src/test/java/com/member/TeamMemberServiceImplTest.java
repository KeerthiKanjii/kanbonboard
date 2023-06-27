
package com.member;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.member.main.entity.TeamMember;
import com.member.main.exception.InvalidUserId;
import com.member.main.service.TeamMemberService;

@SpringBootTest
class TeamMemberServiceImplTest {

    private static  Logger logger = LoggerFactory.getLogger(TeamMemberServiceImplTest.class);

    @Autowired
    private TeamMemberService teamMemberService;

    @Test
    public void testAddTeamMember() throws Exception {
        logger.info("Inside testAddTeamMember");

        TeamMember teamMember = new TeamMember();
        teamMember.setFirstName("John Doe");
        teamMember.setLastName("Doe");
        teamMember.setEmail("jane@example.com");
        teamMember.setPassword("js123");

        TeamMember addedMember = teamMemberService.addTeamMember(teamMember);

        assertNotNull(addedMember);
        assertNotNull(addedMember.getEmail());
        assertNotNull(addedMember.getPassword());
    }

    @Test
    public void testRegisterTeamMember() throws Exception {
        TeamMember teamMember = new TeamMember();
        teamMember.setFirstName("Jane Smith");
        teamMember.setLastName("Smith");
        teamMember.setEmail("jane@example.com");
        teamMember.setPassword("js123");

        TeamMember registeredMember = teamMemberService.registerTeamMember(teamMember);

        assertNotNull(registeredMember);
        assertNotNull(registeredMember.getEmail());
        assertNotNull(registeredMember.getPassword());

        assertEquals("Jane Smith", registeredMember.getFirstName());
        assertEquals("Smith", registeredMember.getLastName());
        assertEquals("jane@example.com", registeredMember.getEmail());
        assertEquals("js123", registeredMember.getPassword());
    }

    @Test
    public void testGetAllTeamMembers() {
        logger.info("Inside testGetAllTeamMembers");

        List<TeamMember> allMembers = teamMemberService.getAllTeamMember();

        assertNotNull(allMembers);
        assertFalse(allMembers.isEmpty());
    }

    @Test
    public void testGetTeamMemberById() throws InvalidUserId {
        logger.info("Inside testGetTeamMemberById");

        Integer memberId = 1;

        TeamMember teamMember = new TeamMember();
        teamMember.settId(memberId);
        teamMember.setFirstName("John");
        teamMember.setLastName("Doe");
        teamMember.setEmail("jane@example.com");
        teamMember.setPassword("js123");

        TeamMember member = teamMemberService.getTeamMemberById(memberId);

//        assertNotNull(member);
//        assertNotNull(member.getEmail());
//        assertNotNull(member.getPassword());
    }

    @Test
    public void testUpdateTeamMember() throws InvalidUserId {
        logger.info("Inside testUpdateTeamMember");

        TeamMember teamMember = new TeamMember();
        teamMember.settId(1);
        teamMember.setFirstName("Jane Smith");
        teamMember.setLastName("Smith");
        teamMember.setEmail("jane@example.com");
        teamMember.setPassword("js123");

        // Update the team member
    }

    @Test
    public void testDeleteTeamMember() throws InvalidUserId {
        logger.info("Inside testDeleteTeamMember");

        int memberId = 1;

        try {
            teamMemberService.delete(memberId);
        } catch (InvalidUserId e) {
            // Handle the exception
        }
    }
}

