package com.example.notesapp.Dto;

import lombok.Data;
import java.time.LocalDateTime;
@Data
public class NoteSummaryDto {

    private String id;

    private String title;

    private LocalDateTime createdAt;
}