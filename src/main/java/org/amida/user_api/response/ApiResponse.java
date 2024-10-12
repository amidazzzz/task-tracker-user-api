package org.amida.user_api.response;

public record AuthResponse(
        String message,
        boolean success
) {
}
