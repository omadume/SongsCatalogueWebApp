//package com.example.demo;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import jakarta.validation.Valid;
//
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//@CrossOrigin(origins = "*", maxAge = 3600) // preflight response is cached for max 30 mins
//@RestController
//@RequestMapping("/auth")
//public class AuthController {
//    AuthenticationManager authenticationManager;
//
//    UserRepository userRepository;
//
////    RoleRepository roleRepository;
//
//    PasswordEncoder encoder;
//
//    JwtUtils jwtUtils;
//
//    //TODO: Add error throws to the mappings in this file maybe
//    @PostMapping("/login")
//    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            String jwt = jwtUtils.generateJwtToken(authentication);
//
//            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
////        List<String> roles = userDetails.getAuthorities().stream()
////                .map(item -> item.getAuthority())
////                .collect(Collectors.toList());
//
//            return ResponseEntity.ok(new JwtResponse(jwt,
////                    userDetails.getId(),
//                userDetails.getUsername()
////                    userDetails.getEmail(),
////                , roles
//            ));
//        } catch (RuntimeException e) {
//            throw new RuntimeException("Login failed.");
//        }
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
//        try {
//            if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//                return ResponseEntity
//                        .badRequest()
//                        .body(new MessageResponse("Error: Username is already in use!"));
//            }
//
//            // Create new user's account
//            User user = new User(signUpRequest.getUsername(),
////                signUpRequest.getEmail(),
//                    encoder.encode(signUpRequest.getPassword()),
//                    new Catalogue());
//
////        Set<String> strRoles = signUpRequest.getRole();
////        Set<Role> roles = new HashSet<>();
//
////        if (strRoles == null) {
////            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
////                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
////            roles.add(userRole);
////        } else {
////            strRoles.forEach(role -> {
////                switch (role) {
////                    case "admin":
////                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
////                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
////                        roles.add(adminRole);
////
////                        break;
////                    case "mod":
////                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
////                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
////                        roles.add(modRole);
////
////                        break;
////                    default:
////                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
////                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
////                        roles.add(userRole);
////                }
////            });
////        }
////
////        user.setRoles(roles);
//            userRepository.save(user);
//
//            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
//        } catch (RuntimeException e) {
//            throw new RuntimeException("Registration failed.");
//        }
//    }
//}
