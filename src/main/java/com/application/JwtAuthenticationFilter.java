//package com.application;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private final JwtUtils jwtUtils ;
//    private final JwtCustomUserDetailsService jwtCustomUserDetailsService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//      final String authHeader = request.getHeader("Authorization");
//      final String jwtToken;
//      final String useremail;
//
//      if(authHeader == null || !authHeader.startsWith("Bearer ")){
//          filterChain.doFilter(request,response);
//          return;
//      }
//      try {
//
//          jwtToken = authHeader.substring(7);
//          useremail = jwtUtils.extractUserEmail(jwtToken);
//      }
//      catch (Exception e){
//          filterChain.doFilter(request,response);
//          return;
//      }
//      if(useremail != null && (SecurityContextHolder.getContext().getAuthentication() == null
//      || !SecurityContextHolder.getContext().getAuthentication().isAuthenticated())){
//
//          UserDetails userDetails = jwtCustomUserDetailsService.loadUserByUsername(useremail);
//          if(jwtUtils.validateToken(jwtToken,userDetails.getUsername())){
//
//              UsernamePasswordAuthenticationToken authToken =
//                      new UsernamePasswordAuthenticationToken(
//                              userDetails,null,userDetails.getAuthorities()
//                      );
//              SecurityContextHolder.getContext().setAuthentication(authToken);
//          }
//
//      }
//      filterChain.doFilter(request,response);
//    }
//}
