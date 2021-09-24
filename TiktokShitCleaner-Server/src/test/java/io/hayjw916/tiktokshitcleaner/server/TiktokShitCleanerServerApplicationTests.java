package io.hayjw916.tiktokshitcleaner.server;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TiktokShitCleanerServerApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	void checkDeleteMethodFailed() throws Exception {
		mvc.perform(delete("http://localhost:8080/song/"))
				.andExpect(status().is(405));
	}

	@Test
	void checkDeleteMethodPassed() throws Exception {
		mvc.perform(delete("http://localhost:8080/song/"))
				.andExpect(status().isOk());
	}

}
