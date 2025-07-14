package com.app.backend.dto;

import java.util.List;

import lombok.Data;

@Data
public class EmailDTO {
    private List<String> to;
    private String subject;
    private String body;
}
