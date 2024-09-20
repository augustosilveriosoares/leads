package com.leads.users.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDto(@NotBlank String name, @NotBlank String email, @NotBlank String password) {
}
