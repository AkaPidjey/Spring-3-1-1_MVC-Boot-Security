//package web.spring311v1.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import web.spring311v1.model.Role;
//import web.spring311v1.model.User;
//import web.spring311v1.service.UserService;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserService userService;
//
////    @Override
////    @Transactional(readOnly = true)
////    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
////        return userDao.getUserByLogin(login).get();
////    }
////}
//
//    @Override
//    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        User user = userService.getUserByLogin(login).get();
//        if (user == null) {
//            System.out.println("User not found!" + login);
//            throw new UsernameNotFoundException("User" + login + " was not found in the database");
//        }
//
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        for (Role role : user.getRoles()) {
//            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
//        UserDetails userDetails = new org.springframework.security.core.userdetails.
//                        User(user.getLogin(), user.getPassword(), grantedAuthorities);
//        return userDetails;
//    }
//
//}
