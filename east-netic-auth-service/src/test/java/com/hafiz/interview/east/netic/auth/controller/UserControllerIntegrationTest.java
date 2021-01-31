package com.hafiz.interview.east.netic.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hafiz.interview.east.netic.auth.core.constants.EndPoint;
import com.hafiz.interview.east.netic.auth.dataclass.UserCreateDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.AssertFalse;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//TODO Test with proper authentication and authorization
// and restrict access by enable csrf and other security related things

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;

  private static String endPoint = "/" + EndPoint.USER_URL;
  private final String userName = "hafiz";

  @Test
  public void Get_User_List_Returns_Single_Entry_List() throws Exception {
    //TODO Add Authorization support and test it passing authorization bearer token
    mvc
            .perform(get(endPoint)
                    .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].username", isA(String.class)))
            .andExpect(jsonPath("$[0].fullName", isA(String.class)))
            .andExpect(status().isOk())
            .andReturn();
  }

  @Test
  @AssertFalse
  public void Get_User_List_Without_Authorization_Token_Returns_Single_Error() throws Exception {
    //TODO Add Authorization support and test it passing authorization bearer token
    mvc
            .perform(get(endPoint)
                    .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();
  }

  @Test
  public void Get_User_By_UserName_Returns_Single_Entry() throws Exception {
    //TODO Add Authorization support and test it passing authorization bearer token
    mvc
            .perform(get(endPoint + "/" + userName)
                    .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(jsonPath("$.username", isA(String.class)))
            .andExpect(jsonPath("$.fullName", isA(String.class)))
            .andExpect(status().isOk())
            .andReturn();
  }


  @Test
  @Transactional
  public void Create_User_Returns_Create_Status() throws Exception {
    //Arrange, act and assert
    UserCreateDTO requestDTO = this.buildUserDTO();
    String requestBody = objectMapper.valueToTree(requestDTO).toString();
    //TODO Add Authorization support and test it passing authorization basic code with client id and secret
    mvc
            .perform(post(endPoint)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
            .andDo(print())
            .andExpect(status().isCreated())
            .andReturn();
  }

  @Test
  @Transactional
  public void Create_User_With_Null_UserName_Returns_Error_Status() throws Exception {
    //Arrange, act and assert
    UserCreateDTO requestDTO = this.buildUserDTO();
    requestDTO.setUsername(null);
    String requestBody = objectMapper.valueToTree(requestDTO).toString();
    //TODO Add Authorization support and test it passing authorization basic code with client id and secret
    mvc
            .perform(post(endPoint)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andReturn();
  }

  @Test
  @Transactional
  public void Create_User_With_Null_Password_Returns_Error_Status() throws Exception {
    //Arrange, act and assert
    UserCreateDTO requestDTO = this.buildUserDTO();
    requestDTO.setPassword(null);
    String requestBody = objectMapper.valueToTree(requestDTO).toString();

    //TODO Add Authorization support and test it passing authorization basic code with client id and secret
    mvc
            .perform(post(endPoint)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andReturn();
  }


  private UserCreateDTO buildUserDTO() {
    UserCreateDTO dto = new UserCreateDTO();
    dto.setFullName("Shaikh Hafiz Ahamed");
    dto.setUsername("shaikh");
    dto.setPassword(new StringBuilder("12345"));
    return dto;
  }

}
