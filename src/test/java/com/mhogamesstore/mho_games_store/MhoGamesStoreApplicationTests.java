package com.mhogamesstore.mho_games_store;

import static org.junit.Assert.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.mhogamesstore.mho_games_store.controller.StoreController;

@SpringBootTest
@AutoConfigureMockMvc
class MhoGamesStoreApplicationTests {

	@Autowired
	private StoreController controller;

	@Autowired

	private MockMvc mockMvc;

	@Test
	void contextLoads() {

		assertNotNull(controller);
		assertNotNull(mockMvc);


	}

	// simulate a mock web request

 @Test
    public void testShowStoreForm() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/?id=123");

        mockMvc.perform(requestBuilder)
            .andExpect(status().is2xxSuccessful())
            .andExpect(view().name("form"))
            .andExpect(model().attributeExists("games"));
    }


	@Test
	public void testSubmissionSuccess() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/submit")
				.param("category", "Action")
				.param("title", "Zula")
				.param("price", String.valueOf(21))  
				.param("discount", String.valueOf(2))  
				.param("date", "2020-03-01");  
	
		mockMvc.perform(request)
			   .andExpect(status().is3xxRedirection());  
}



@Test

public void testUnsuccessfulSubmit() throws Exception{

	RequestBuilder request = MockMvcRequestBuilders.post("/submit")
				.param("", "")
				.param("", "")
				.param("price", String.valueOf(21))  
				.param("discount", String.valueOf(2))  
				.param("date", "2020-03-01"); 


				mockMvc.perform(request)
				.andExpect(status().is2xxSuccessful()).andExpect(view().name("form")); 

}



@Test
public void testGetGames() throws Exception {
    RequestBuilder request = MockMvcRequestBuilders.get("/inventory");

    mockMvc.perform(request)
           .andExpect(status().is2xxSuccessful())  
           .andExpect(view().name("inventory"))  
           .andExpect(model().attributeExists("games"));  
}

}