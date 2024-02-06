package example.brief.MyRh.controller;


import example.brief.MyRh.dtos.ReqRes;
import example.brief.MyRh.dtos.societe.RequestCreateSocieteDTO;
import example.brief.MyRh.entities.UserInfoEntity;
import example.brief.MyRh.services.serviceImpl.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody ReqRes requestLogin){

       return ResponseEntity.ok(authService.Signin(requestLogin));

    }

    @PostMapping("/Signup")
    public ResponseEntity register( RequestCreateSocieteDTO requestLogin){

        return ResponseEntity.ok(authService.SignUp(requestLogin));

    }

    @PostMapping("/refreshToken")
    public ResponseEntity refreshToken(@RequestBody ReqRes requestRefresh){

        return ResponseEntity.ok(authService.RefreshToken(requestRefresh));

    }



}
