package bookagg.api.register.dto;

import java.util.Set;

public record RegisterRequest(
        String userName,
        String firstName,
        String lastName,
        String email,
        Set<String> favoriteBooks
) {}