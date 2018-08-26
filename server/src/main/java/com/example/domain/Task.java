package com.example.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

public class Task {
    @Getter
    @Setter
    private String id, title, detail;

    @Getter
    @Setter
    private LocalDateTime completed_at;
}
