package elhassen.spring.web;

import elhassen.spring.dto.AuthRequest;
import elhassen.spring.dto.AuthResponse;
import elhassen.spring.entity.AppUser;
import elhassen.spring.security.JwtService;
import elhassen.spring.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserServiceImpl serviceImpl;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/health")
    public ResponseEntity<String> getResponse(){
        return ResponseEntity.ok("UP");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AppUser model){
        return  ResponseEntity.ok(serviceImpl.addUser(model));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        AppUser user = serviceImpl.fidUserByUsername(request.getUsername());
        String token = jwtService.generateToken(request.getUsername());
        return ResponseEntity.ok(new AuthResponse(token, user));
    }
}