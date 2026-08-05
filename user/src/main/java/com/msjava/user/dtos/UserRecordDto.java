package com.msjava.user.dtos;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRecordDto(
        @NotBlank @Length(min = 2, max = 255) String name,
        @NotBlank @Email String email) {
}
