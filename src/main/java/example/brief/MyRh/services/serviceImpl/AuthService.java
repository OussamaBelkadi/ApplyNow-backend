package example.brief.MyRh.services.serviceImpl;

import example.brief.MyRh.Util.JWTUtils;
import example.brief.MyRh.config.UserInfoConfig;
import example.brief.MyRh.config.UserInfoManagerConfig;
import example.brief.MyRh.dtos.ReqRes;
import example.brief.MyRh.dtos.societe.RequestCreateSocieteDTO;
import example.brief.MyRh.entities.UserInfoEntity;
import example.brief.MyRh.exceptions.exception.AccessOffreException;
import example.brief.MyRh.repositories.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserInfoRepo userInfoRepo;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserInfoManagerConfig userInfoManagerConfig;

    public UserInfoEntity SignUp(RequestCreateSocieteDTO userInfo){

        UserInfoEntity userInfo1 = new UserInfoEntity();
        userInfo1.setUserName(userInfo.getUserName());
        userInfo1.setEmail(userInfo.getEmail());
        userInfo1.setRoles(userInfo.getRoles());
        String pw =  userInfo.getPassword();
        String hpw = passwordEncoder.encode(pw);
        userInfo1.setPassword(hpw);
        userInfo1.setAdress(userInfo.getAdresse());
        userInfo1.setMobileNumber(userInfo.getPhone());

        if(userInfo.getRoles().equals("Role_Candidate") ){
            userInfo.setImageFile(null);
        }else{
        MultipartFile file = userInfo.getImageFile();
        if (file != null && !file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get("src/main/resources/images/" + UUID.randomUUID().toString() + file.getOriginalFilename());
                Files.write(path, bytes);
                userInfo1.setImage(path.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        }

        UserInfoEntity userInfo2 = userInfoRepo.save(userInfo1);
        if(userInfo1 == null || userInfo1.getId() < 0){
            throw new RuntimeException("user not saved");
        }else{
            return userInfo2;
        }

    }

    public ReqRes Signin(ReqRes requestLogin){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestLogin.getEmail(),requestLogin.getPassword()));
        var userInfoEntity = userInfoManagerConfig.loadUserByUsername(requestLogin.getEmail());
        System.out.println("the user " + userInfoEntity);
        String jwt =  jwtUtils.GenerateToken(userInfoEntity);
        Optional<UserInfoEntity> userInfo = userInfoRepo.findByEmail(requestLogin.getEmail());
        Long Id =  userInfo.get().getId();
        var refreshToken = jwtUtils.GenerateRefreshToken(new HashMap<>(),userInfoEntity);
        ReqRes reqRes = new ReqRes();
        reqRes.setToken(jwt);
        reqRes.setRole(userInfoEntity.getAuthorities().toString());
        reqRes.setRefreshToken(refreshToken);
        reqRes.setExpirationTime("20 minutes");
        reqRes.setId(Id);

        return reqRes;

    }

    public ReqRes RefreshToken(ReqRes RefreshTokenRequest) {

        ReqRes reqRes1 = new ReqRes();
        String Email = jwtUtils.extractUsername(RefreshTokenRequest.getToken());
        var user = userInfoManagerConfig.loadUserByUsername(Email);
        if(jwtUtils.isTokenValid(RefreshTokenRequest.getToken(),user)){

            var jwt = jwtUtils.GenerateToken(user);
            reqRes1.setToken(jwt);
            reqRes1.setRefreshToken(RefreshTokenRequest.getToken());
            reqRes1.setExpirationTime("5 minutes");

        }
        return reqRes1;

    }


}
