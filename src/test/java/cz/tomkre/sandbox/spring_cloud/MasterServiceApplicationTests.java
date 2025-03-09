package cz.tomkre.sandbox.spring_cloud;

import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(GreetingController.class)
class MasterServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@SneakyThrows
	void shouldHandle() {
		var requestBody = """ 
			{
				"lang": "de",
				"message": "Guten Tag"
			}	 
			""";
		mockMvc.perform(post("/api/greetings")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)
			)
			.andExpectAll(
				status().isCreated(),
				jsonPath("$.lang").value("de"),
				jsonPath("$.message").value("Guten Tag")
			);
	}

}
