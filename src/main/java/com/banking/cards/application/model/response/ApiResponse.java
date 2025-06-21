package com.banking.cards.application.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.Map;

@Builder
@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private T data;
    private Map<String, String> errors;
}
