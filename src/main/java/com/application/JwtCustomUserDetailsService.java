//package com.application;
//
//import com.application.entities.UserEntity;
//import com.application.repositories.UserRespository;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class JwtCustomUserDetailsService implements UserDetailsService {
//
//
//    private final UserRespository userRespository;
//
//    public JwtCustomUserDetailsService(UserRespository userRespository){
//        this.userRespository = userRespository;
//    }
//
//    DaoAuthenticationProvider daoAuthenticationProvider;
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//       UserEntity user = userRespository.findByEmail(email).orElseThrow(
//               ()-> new RuntimeException("No User Exists"));
//        return new JwtCustomUserDetails(user);
//    }
//}
