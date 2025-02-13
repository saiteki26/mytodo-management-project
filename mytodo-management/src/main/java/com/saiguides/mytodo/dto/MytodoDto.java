package com.saiguides.mytodo.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MytodoDto {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
}
