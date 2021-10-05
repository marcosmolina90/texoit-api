package br.com.molina.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;

import br.com.molina.controller.dto.ResultDto;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase 	
public class FilmeControllerIT {

	@Autowired
	private TestRestTemplate testRestTemplate;
	@LocalServerPort
	private int port;
	
	@Test
	@DisplayName("Teste intervalo Min")
	void testIntervalMin() {
		var result = testRestTemplate.exchange("/filmes", HttpMethod.GET, null, ResultDto.class).getBody();
	    assertNotNull(result);
	    assertThat(result.getMin().isEmpty()).isEqualTo(false);
	    assertThat(result.getMin().stream().mapToInt(a-> a.getInterval()).min().getAsInt()).isEqualTo(1);

		
	}
	
	@Test
	@DisplayName("Teste intervalo Max")
	void testIntervalMax() {
		var result = testRestTemplate.exchange("/filmes", HttpMethod.GET, null, ResultDto.class).getBody();
	    assertNotNull(result);
	    assertThat(result.getMax().isEmpty()).isEqualTo(false);

	    assertThat(result.getMax().stream().mapToInt(a-> a.getInterval()).min().getAsInt()).isEqualTo(13);
	}
}
