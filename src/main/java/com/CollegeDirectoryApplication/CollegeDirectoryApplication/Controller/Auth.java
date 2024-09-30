package com.CollegeDirectoryApplication.CollegeDirectoryApplication.Controller;

import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.JwtRequest;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.Entity.JwtResponse;
import com.CollegeDirectoryApplication.CollegeDirectoryApplication.JwtUtils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class Auth {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request){

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());
            String jwtToken = jwtUtil.generateToken(userDetails.getUsername());
            System.out.println("UserName: " + request.getUserName() + " & password: " + request.getPassword());
            return new ResponseEntity<>( new JwtResponse(jwtToken , request.getUserName() , "ACCEPTED"), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(new JwtResponse("Invalid UserName or Password" , "" , "REJECTED") , HttpStatus.FORBIDDEN);
        }
    }

//    private void doAuthenticate(String email, String password) {
//
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
//        try {
//            authenticationManager.authenticate(authentication);
//
//
//        } catch (BadCredentialsException e) {
//            throw new BadCredentialsException(" Invalid Username or Password  !!");
//        }
//
//    }


}
