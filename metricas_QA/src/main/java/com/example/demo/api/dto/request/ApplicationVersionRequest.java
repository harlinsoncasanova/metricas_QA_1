package com.example.demo.api.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationVersionRequest {
    private String name;
    private String versionName;
}
