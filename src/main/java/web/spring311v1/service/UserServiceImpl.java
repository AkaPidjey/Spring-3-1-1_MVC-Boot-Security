package web.spring311v1.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.spring311v1.DAO.UserDao;
import web.spring311v1.model.Role;
import web.spring311v1.model.User;

import java.util.*;

@NoArgsConstructor
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public void createNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPasswordReal()));
//        Set<Role> adminSet = new HashSet<>();
//        Set<Role> userSet = new HashSet<>();
//        adminSet.add(new Role("ROLE_ADMIN"));
//        userSet.add(new Role("ROLE_USER"));
//        if (user.getRoles().equals(adminSet)) {
//            user.getRoles().add(userDao.getRoleByName("ROLE_ADMIN").get());
//        }
//        user.getRoles().add( userDao.getRoleByName("ROLE_USER").get());

//
//        user.setRoles(new HashSet<>());
//
//        if (user.getRoles().toString().equals("ROLE_ADMIN")) {
//            user.getRoles().add(userDao.getRoleByName("ROLE_ADMIN").get());
//            user.getRoles().add(userDao.getRoleByName("ROLE_USER").get());
//        } else {
//            user.getRoles().add( userDao.getRoleByName("ROLE_USER").get());
//        }


        userDao.createNewUser(user);
    }

    @Override
    @Transactional
    public void editUser(User user) {
        user.setPasswordReal(user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPasswordReal()));
//        if (user.getRoles().size() > 1) {
//            user.getRoles().add(userDao.getRoleByName("ROLE_ADMIN").get());
//            user.getRoles().add(userDao.getRoleByName("ROLE_USER").get());
//        } else {
//            user.getRoles().add( userDao.getRoleByName("ROLE_USER").get());
//        }


//        Set<Role> roleSet = new HashSet<>();
//        if (role.equals("ROLE_ADMIN")){
//            roleSet.add(userService.getRoleByName("ROLE_ADMIN").get());
//            roleSet.add(userService.getRoleByName("ROLE_USER").get());
//        } else {
//            roleSet.add(userService.getRoleByName("ROLE_USER").get());
//        }


        userDao.editUser(user);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userDao.deleteUserById(id);
    }

    @Override
    @Transactional
    public Optional<User> getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public Optional<User> getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    @Override
    public Optional<Role> getRoleByName(String name) {
        return userDao.getRoleByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user = userDao.getUserByLogin(login).get();
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return user;
    }


//    @Override
//    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        User user = userDao.getUserByLogin(login).get();
//        if (user == null) {
//            System.out.println("User not found!" + login);
//            throw new UsernameNotFoundException("User" + login + " was not found in the database");
//        }
//
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        for (Role role : user.getRoles()) {
//            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
//        return user;
//    }
}
