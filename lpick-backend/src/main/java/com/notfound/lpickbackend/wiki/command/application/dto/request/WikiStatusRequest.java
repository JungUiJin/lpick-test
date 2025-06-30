package com.notfound.lpickbackend.wiki.command.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class WikiStatusRequest {
    @NotBlank
    private String status;
}
