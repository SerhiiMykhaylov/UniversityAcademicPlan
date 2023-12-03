package ua.foxminded.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import ua.foxminded.dto.UsersDto;
import ua.foxminded.entity.UserType;
import ua.foxminded.service.UsersService;

@WebMvcTest(value = UserPageController.class)
class UserPageControllerTest {
	
	@Autowired
	private MockMvc mvc;
	@MockBean
	private UsersService service;

	@WithMockUser(value="admin")
	@Test
	void testStart() throws Exception {
		mvc.perform(get("/").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(view().name("index"));
	}

	@WithMockUser(username = "admin")
	@Test
	void testShowUserInfo() throws Exception {
		UsersDto dto = new UsersDto("admin", "password", UserType.ROLE_ADMIN);
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		UsersDetails details = (UsersDetails) authentication.getPrincipal();
		List<UsersDto> dtoList = new ArrayList<>(Arrays.asList(new UsersDto("nickname", "password", UserType.ROLE_ADMIN), new UsersDto("nickname2", "password", UserType.ROLE_STUDENT)));
//		when(mock.SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(new UsersDetails(dto));
		when(service.getByNickName(Mockito.anyString())).thenReturn(dto);
		when(service.getAll()).thenReturn(dtoList);
		mvc.perform(get("/showUserPage")).andExpect(status().isOk());
	}

}
