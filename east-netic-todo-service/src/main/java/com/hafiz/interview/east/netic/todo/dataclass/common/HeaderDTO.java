package com.hafiz.interview.east.netic.todo.dataclass.common;

import lombok.Data;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.annotation.RequestScope;

import java.util.UUID;

@Data
@RequestScope(proxyMode= ScopedProxyMode.TARGET_CLASS)
public class HeaderDTO {
    private UUID userId;
}
