package com.example.livecode_ecommerce.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class TransactionRequest {
    @NotEmpty(message = "Tanggal lahir wajib diisi")
    @Pattern(regexp = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$", message = "Format tanggal harus yyyy-mm-dd")
    private String date;
}
