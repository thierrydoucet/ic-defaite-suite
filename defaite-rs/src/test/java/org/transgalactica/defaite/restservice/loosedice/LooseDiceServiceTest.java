package org.transgalactica.defaite.restservice.loosedice;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.transgalactica.defaite.restservice.DefaiteApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DefaiteApplication.class)
@WebAppConfiguration
public class LooseDiceServiceTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void castLooseDice() throws Exception {
		mockMvc.perform(get("/loosedice/cast")).andExpect(status().isOk())//
				.andExpect(content().contentType("application/json;charset=UTF-8"))//
				.andExpect(jsonPath("$", Matchers.containsString("LOOSE")));
	}

	@Test
	public void getAllSides() throws Exception {
		mockMvc.perform(get("/loosedice/sides")).andExpect(status().isOk())//
				.andExpect(content().contentType("application/json;charset=UTF-8"))//
				//["LOOSE_RELOAD","NO_LOOSE","LOW_LOOSE","MID_LOOSE","MEGA_LOOSE","ULTIMATE_LOOSE"]
				.andExpect(jsonPath("$", hasSize(6)))//
				.andExpect(jsonPath("$[0]").value("LOOSE_RELOAD"))//
				.andExpect(jsonPath("$[1]").value("NO_LOOSE"))//
				.andExpect(jsonPath("$[2]").value("LOW_LOOSE"))//
				.andExpect(jsonPath("$[3]").value("MID_LOOSE"))//
				.andExpect(jsonPath("$[4]").value("MEGA_LOOSE"))//
				.andExpect(jsonPath("$[5]").value("ULTIMATE_LOOSE"));
	}
}
