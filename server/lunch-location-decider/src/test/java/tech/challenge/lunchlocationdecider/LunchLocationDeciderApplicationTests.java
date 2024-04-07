package tech.challenge.lunchlocationdecider;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tech.challenge.lunchlocationdecider.controller.LunchSessionController;

@SpringBootTest
class LunchLocationDeciderApplicationTests {

	@Autowired
	private LunchSessionController lunchSessionController;

	@Test
	void contextLoads() throws Exception {
		assertThat(lunchSessionController).isNotNull();
	}

}
