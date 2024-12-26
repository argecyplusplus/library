package com.project.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Object authenticate(String login, String password) {
        Reader reader = readerRepository.findByLogin(login);
        if (reader != null && passwordEncoder.matches(password, reader.getPassword())) {
            return reader;
        }

        Staff staff = staffRepository.findByLogin(login);
        if (staff != null && passwordEncoder.matches(password, staff.getPassword())) {
            return staff;
        }

        throw new RuntimeException("Invalid credentials");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
