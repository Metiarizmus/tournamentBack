package com.scorina.competition.controllers.auth;

import com.scorina.competition.controllers.auth.payload.JwtResponse;
import com.scorina.competition.controllers.auth.payload.LoginPayload;
import com.scorina.competition.payload.ResponseModel;
import com.scorina.competition.security.CustomUserDetails;
import com.scorina.competition.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody @Valid LoginPayload loginRequest) {
        System.out.println(loginRequest);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword()));
        System.out.println("32 : " + authentication);

       if (authentication != null) {
           SecurityContextHolder.getContext().setAuthentication(authentication);
           String jwt = jwtUtils.generateJwtToken(authentication);
           System.out.println(jwt);
           CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
           List<String> roles = userDetails.getAuthorities().stream()
                   .map(GrantedAuthority::getAuthority)
                   .collect(Collectors.toList());
           System.out.println(roles);
           return ResponseEntity.ok(new JwtResponse(jwt, loginRequest.getLogin(), roles));
       }

        return ResponseEntity.ok(new ResponseModel<>(null, "User doesn't log in"));

    }
}
