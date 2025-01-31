package dev.Fabricio.runnerz.user;

import org.springframework.boot.context.properties.bind.Name;
import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.Email;

public record User(
    @Id
    Integer id,
    @Name("name")
    String name,
    @Name("username")
    String username,
    @Email
    String email,
    Address address,
    String phone,
    String website,
    Company company
) {
}