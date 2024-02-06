package example.brief.MyRh.config;

import example.brief.MyRh.repositories.UserInfoRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoManagerConfig implements UserDetailsService {

    private final UserInfoRepo userInfoRepo;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userInfoRepo.findByEmail(email)
                    .map(UserInfoConfig::new)
                    .orElseThrow(()-> new UsernameNotFoundException("UserEmail: "+email+" does not exist"));
    }

}
