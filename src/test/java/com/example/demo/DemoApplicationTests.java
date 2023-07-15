package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import com.example.demo.Service.RateService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@WebAppConfiguration
class DemoApplicationTests {

	@Autowired
	private RateService rateService;

	@Autowired
	private WebApplicationContext webApplicationContext;
	MockMvc mvc; // 創建MockMvc類的物件

	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	void contextLoads() {
	}

	@Test
	void rateTest() throws Exception {

		System.out.println("start rate test");
		Assert.notEmpty(rateService.currency, "currenies is not null");
		Assert.hasText(rateService.exchangeRate("USD", "JPY", "$1,525"), "exchange rate not null");

	}

	@Test
	void rateApiTest() throws Exception {

		System.out.println("start rate api test");
		String uri = "/";
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
		int status = result.getResponse().getStatus();
		System.out.println(status);

		Assert.state(status == 200, "success");

		// success
		String uriApi = "/exchangeRate?source=USD&target=JPY&amount=$1,525";
		MvcResult resultApi = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON))
				.andReturn();
		int statusApi = resultApi.getResponse().getStatus();
		String response = resultApi.getResponse().getContentAsString();
		System.out.println(statusApi);

		Assert.state(status == 200, "success");
		Assert.hasText(response, "response not null");
	}
}
