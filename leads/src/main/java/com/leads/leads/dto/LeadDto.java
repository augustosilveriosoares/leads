package com.leads.leads.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record LeadDto(@NotBlank String name,@NotBlank String whatsapp, String email, @NotNull UUID userId ) {
}
