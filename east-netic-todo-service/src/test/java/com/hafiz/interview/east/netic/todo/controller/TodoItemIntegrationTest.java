package com.hafiz.interview.east.netic.todo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hafiz.interview.east.netic.todo.HelperService;
import com.hafiz.interview.east.netic.todo.core.constants.EndPoint;
import com.hafiz.interview.east.netic.todo.dataclass.todo.TodoItemDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TodoItemIntegrationTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ObjectMapper objectMapper;

  private static String todoItemId = "f6153439-5c22-4821-992c-ef026c023d52";
  private static String endPoint = "/" + EndPoint.TODO_ITEM_URL;
  //TODO Get this token from user service
  private static String token = HelperService.getToken();
  private final String userId = "a45d7a3a-5ab8-4b6f-a6ce-502ea39dfc73";

  @Test
  public void Get_TodoItem_Page_Returns_Page_With_Single_Entry() throws Exception {
    mvc
            .perform(get(endPoint)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", token)
                    .param("page", "0")
                    .param("size", "2"))
            .andDo(print())
            .andExpect(jsonPath("$.content", hasSize(1)))
            .andExpect(jsonPath("$.content[0].user.username", isA(String.class)))
            .andExpect(jsonPath("$.content[0].user.fullName", isA(String.class)))
            .andExpect(status().isOk())
            .andReturn();
  }

  @Test
  public void Get_TodoItem_Page_By_No_Params_Returns_Page_With_Single_Entry() throws Exception {
    mvc
            .perform(get(endPoint)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", token))
            .andDo(print())
            .andExpect(jsonPath("$.content", hasSize(1)))
            .andExpect(jsonPath("$.content[0].user.username", isA(String.class)))
            .andExpect(jsonPath("$.content[0].user.fullName", isA(String.class)))
            .andExpect(status().isOk())
            .andReturn();
  }

  @Test
  public void Get_TodoItem_Page_By_User_Id_Returns_Page_With_Single_Entry() throws Exception {
    mvc
            .perform(get(endPoint + "/by-user-id")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", token)
                    .header("user_id",userId)
                    .param("page", "0")
                    .param("size", "2"))
            .andDo(print())
            .andExpect(jsonPath("$.content", hasSize(1)))
            .andExpect(jsonPath("$.content[0].user.username", isA(String.class)))
            .andExpect(jsonPath("$.content[0].user.fullName", isA(String.class)))
            .andExpect(status().isOk())
            .andReturn();
  }

  @Test
  public void Get_TodoItem_By_Id_Return_Single_Entry() throws Exception {
    mvc
            .perform(get(endPoint + "/" + todoItemId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", token))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();
  }

  @Test
  public void Get_TodoItem_By_Wrong_Id_Return_NotFound_Status() throws Exception {
    mvc
            .perform(get(endPoint + "/" + UUID.randomUUID())
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", token))
            .andDo(print())
            .andExpect(status().isNotFound())
            .andReturn();
  }

  @Test
  @Transactional
  public void Create_TodoItem_Returns_Create_Status() throws Exception {
    //Arrange, act and assert
    TodoItemDTO requestDTO = this.buildTodoItemDTO();
    String requestBody = objectMapper.valueToTree(requestDTO).toString();
    mvc
            .perform(post(endPoint)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody)
                    .header("Authorization", token))
            .andDo(print())
            .andExpect(status().isCreated())
            .andReturn();
  }

  @Test
  @Transactional
  public void Create_TodoItem_With_Null_Title_Returns_Error_Status() throws Exception {
    //Arrange, act and assert
    TodoItemDTO requestDTO = this.buildTodoItemWithNullTitleDTO();
    String requestBody = objectMapper.valueToTree(requestDTO).toString();

    mvc
            .perform(post(endPoint)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody)
                    .header("Authorization", token))
            .andDo(print())
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.code", is(400)))
            .andReturn();
  }

  @Test
  @Transactional
  public void Update_TodoItem_Returns_Update_Status() throws Exception {
    //Arrange, act and assert
    TodoItemDTO requestDTO = this.buildTodoItemDTO();
    String requestBody = objectMapper.valueToTree(requestDTO).toString();
    mvc
            .perform(put(endPoint + "/" + todoItemId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody)
                    .header("Authorization", token))
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();
  }

  @Test
  @Transactional
  public void Delete_TodoItem_By_Id_Return_Success_Status() throws Exception {
    //Arrange, act and assert
    mvc
            .perform(delete(endPoint + "/" + todoItemId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", token))
            .andDo(print())
            .andExpect(status().isNoContent())
            .andReturn();
  }

  private LocalDateTime getCurrentDate() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    LocalDateTime dateTime = LocalDateTime.parse(LocalDateTime.now().toString().substring(0,16), formatter);
    return dateTime;
  }

  private TodoItemDTO buildTodoItemDTO() {
    TodoItemDTO dto = new TodoItemDTO();
    dto.setDate(this.getCurrentDate());
    dto.setDescription("Have to go Khulna");
    dto.setTitle("Travel khulna");
    dto.setUserId(UUID.fromString("401601fe-214c-4106-82b0-ccf0d3c79fe8"));
    return dto;
  }

  private TodoItemDTO buildTodoItemWithNullTitleDTO() {
    TodoItemDTO dto = new TodoItemDTO();
    dto.setDate(this.getCurrentDate());
    dto.setDescription("Have to go Khulna");
    dto.setUserId(UUID.fromString("401601fe-214c-4106-82b0-ccf0d3c79fe8"));
    return dto;
  }
}
