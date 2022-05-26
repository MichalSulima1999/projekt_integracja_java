package com.example.projekt.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.projekt.domain.Role;
import com.example.projekt.domain.User;
import com.example.projekt.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class UserResource {
    private Gson gson = new Gson();
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>>getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/user/register")
    public ResponseEntity<User>saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role>saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?>addRoleToUser(@RequestBody RoleToUserForm form) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response, @CookieValue(name = "jwt") String jwt) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);

        if(jwt == null) {
            throw new RuntimeException("Refresh token is missing");
        }
        User user = userService.getUserByJWT(jwt);
        if(user == null) {
            throw new RuntimeException("User not found");
        }

        try {
            String refresh_token = jwt;
            Algorithm algorithm = Algorithm.HMAC256("secret".getBytes(StandardCharsets.UTF_8));
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(refresh_token);
            String username = decodedJWT.getSubject();
            String access_token = JWT.create()
                    .withSubject(user.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 10*60*1000))
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                    .sign(algorithm);

            TokenAndRoles tokenAndRoles = new TokenAndRoles(access_token, user.getRoles());
            String responseStringJson = this.gson.toJson(tokenAndRoles);
            PrintWriter out = response.getWriter();
            response.setContentType(APPLICATION_JSON_VALUE);
            out.print(responseStringJson);
            out.flush();
            //new ObjectMapper().writeValue(response.getOutputStream(), tokens);
        } catch (Exception exception) {
            response.setHeader("Error", exception.getMessage());
            response.setStatus(FORBIDDEN.value());
            Map<String, String> error = new HashMap<>();
            error.put("error_message", exception.getMessage());
            response.setContentType(APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), error);
        }
    }

    @GetMapping("/user/logout")
    public void logoutUser(HttpServletResponse response, @CookieValue(name = "jwt") String jwt) throws IOException {
        User user = userService.getUserByJWT(jwt);
        userService.updateJWT(user.getUsername(), "");

        ResponseCookie deleteSpringCookie = ResponseCookie
                .from("jwt", "")
                .httpOnly(true)
                .secure(true)
                .sameSite("None")
                .maxAge(0)
                .build();

        response.setHeader(HttpHeaders.SET_COOKIE, deleteSpringCookie.toString());
        Map<String, String> res = new HashMap<>();
        res.put("Ok", "Logout");
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), res);
    }
}

@Data
class RoleToUserForm {
    private String username;
    private String roleName;
}

class TokenAndRoles {
    private String accessToken;
    private Collection<Role> roles;

    public TokenAndRoles(String accessToken, Collection<Role> roles) {
        this.accessToken = accessToken;
        this.roles = roles;
    }
}
