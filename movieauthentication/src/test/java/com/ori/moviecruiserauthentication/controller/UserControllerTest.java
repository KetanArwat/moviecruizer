package com.ori.moviecruiserauthentication.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.junit4.SpringRunner;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ori.moviecruiserauthentication.model.User;
import com.ori.moviecruiserauthentication.service.SecurityTokenGenerator;
import com.ori.moviecruiserauthentication.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private transient MockMvc mockMvc;

	@MockBean
	private transient UserService service;

	@MockBean
	private SecurityTokenGenerator securityTokenGenerator;

	private transient User user;

	@InjectMocks
	private UserController controller;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		user = new User("sonu3706", "Chandan", "Mishra", "123456", new Date());
	}

	@Test
	public void testRegisterUser() throws Exception {
		when(service.saveUser(user)).thenReturn(true);
		mockMvc.perform(post("/user/register").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(jsonToString(user))).andExpect(status()
						.isCreated()).andDo(print());
		verify(service, times(1)).saveUser(Mockito.any(User.class));
		verifyNoMoreInteractions(service);
	}

	@Test
	public void testLoginUser() throws Exception {
		when(service.saveUser(user)).thenReturn(true);
		when(service.findByUserIdAndPassword("sonu3706", "123456")).thenReturn(user);

		mockMvc.perform(post("/user/login").contentType(MediaType.APPLICATION_JSON)
				.content(jsonToString(user))).andExpect(status().isOk());

		verify(service, times(1)).findByUserIdAndPassword(user.getUserId(), user.getPassword());
		verifyNoMoreInteractions(service);
	}

	private String jsonToString(final Object object) {
		String result;
		try {
			final ObjectMapper mapper = new ObjectMapper();
			result = mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			result = "Json processing error";
		}
		return result;
	}
}
