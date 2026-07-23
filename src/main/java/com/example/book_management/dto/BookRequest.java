package com.example.book_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class BookRequest {
    @NotBlank(message = "Judul buku wajib diisi")
    private String title;

    @NotBlank(message = "Nama author wajib diisi")
    private String author;

    private String isbn;

    @PositiveOrZero(message = "Stock tidak boleh negatif")
    private Integer stock;
}
