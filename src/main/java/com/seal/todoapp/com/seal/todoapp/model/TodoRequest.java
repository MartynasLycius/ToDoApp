package com.seal.todoapp.com.seal.todoapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.seal.todoapp.com.seal.todoapp.utils.CustomLocalDateSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TodoRequest {

    private Long id;

    private String title;

    private String description;

    private Boolean complete;

    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate date;

}
