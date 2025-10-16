package sports_betting;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SportsBettingApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		// This test ensures the application context loads successfully
	}

	@Test
	void testGetNbaTeams() throws Exception {
		mockMvc.perform(get("/api/teams"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(5))));
	}

	@Test
	void testTeamsHaveRequiredFields() throws Exception {
		mockMvc.perform(get("/api/teams"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").exists())
				.andExpect(jsonPath("$[0].name").exists())
				.andExpect(jsonPath("$[0].nickname").exists())
				.andExpect(jsonPath("$[0].nbaFranchise").value(true));
	}

	@Test
	void testLakersInDatabase() throws Exception {
		mockMvc.perform(get("/api/teams"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[*].name", hasItem(containsString("Lakers"))));
	}

	@Test
	void testWarriorsInDatabase() throws Exception {
		mockMvc.perform(get("/api/teams"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[*].name", hasItem(containsString("Warriors"))));
	}

}
