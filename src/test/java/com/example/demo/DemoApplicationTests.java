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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
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

	MockMvc mvc;

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
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		int status = result.getResponse().getStatus();
		System.out.println(status);

		// success
		String uriApi = "/exchangeRate";

		MvcResult resultApi = mvc
				.perform(MockMvcRequestBuilders.get(uriApi).param("source", "USD").param("target", "JPY")
						.param("amount", "$1,525").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		int statusApi = resultApi.getResponse().getStatus();
		String response = resultApi.getResponse().getContentAsString();

		System.out.println(statusApi);
		System.out.println(response);
		Assert.hasText(response, "response not null");

		Assert.isTrue(response.contains("success"), "error");

		// source not in currency
		resultApi = mvc
				.perform(MockMvcRequestBuilders.get(uriApi).param("source", "AAA").param("target", "JPY")
						.param("amount", "$1,525").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		statusApi = resultApi.getResponse().getStatus();
		response = resultApi.getResponse().getContentAsString();

		System.out.println(statusApi);
		System.out.println(response);

		Assert.isTrue(response.contains("error"), "error");

		// target not in currency
		resultApi = mvc
				.perform(MockMvcRequestBuilders.get(uriApi).param("source", "USD").param("target", "AAA")
						.param("amount", "$1,525").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		statusApi = resultApi.getResponse().getStatus();
		response = resultApi.getResponse().getContentAsString();

		System.out.println(statusApi);
		System.out.println(response);

		Assert.isTrue(response.contains("error"), "error");

		// amount not in $1,525
		resultApi = mvc
				.perform(MockMvcRequestBuilders.get(uriApi).param("source", "USD").param("target", "AAA")
						.param("amount", "$1525").accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		statusApi = resultApi.getResponse().getStatus();
		response = resultApi.getResponse().getContentAsString();

		System.out.println(statusApi);
		System.out.println(response);

		Assert.isTrue(response.contains("error"), "error");
	}
}
