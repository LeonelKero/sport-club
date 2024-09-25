package com.workbeattalent.sport_club.user;

public record User(
        Long id,
        String name,
        String username,
        String email,
        Address address,
        String phone,
        String website,
        Company company
) {
}
