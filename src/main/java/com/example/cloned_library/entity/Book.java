package com.example.cloned_library.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Book extends Auditable {
    @NotBlank(message = "title_null")
    private String title;
    @NotBlank(message = "description_null")
    private String description;

//    @NotBlank(message = "file_null")
//    @NotNull(message = "file_null")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Upload file;

    @Builder(builderMethodName = "childBuilder")
    public Book(String id, LocalDateTime createdAt, LocalDateTime updatedAt, String title, String description, @NotNull Upload file) {
        super(id, createdAt, updatedAt);
        this.title = title;
        this.description = description;
        this.file = file;
    }
}
