package com.example.demo.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AplicationRequest {
	
    @NotBlank(message = "El nombre de la aplicacion es requerido")
    private String name;

	
}
