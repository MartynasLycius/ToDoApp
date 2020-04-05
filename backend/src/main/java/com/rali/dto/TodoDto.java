package com.rali.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class TodoDto {
    private String id;
    private Date startDate;
    private String itemName;
    private String description;
    private Boolean isDone;
}
