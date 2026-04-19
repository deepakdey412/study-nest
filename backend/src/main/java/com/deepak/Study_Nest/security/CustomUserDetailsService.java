package com.deepak.Study_Nest.security;

import com.deepak.Study_Nest.dao.StudentRepository;
import com.deepak.Study_Nest.dao.SuperAdminRepository;
import com.deepak.Study_Nest.dao.TutorRepository;
import com.deepak.Study_Nest.entity.Student;
import com.deepak.Study_Nest.entity.SuperAdmin;
import com.deepak.Study_Nest.entity.Tutor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final StudentRepository studentRepository;
    private final TutorRepository tutorRepository;
    private final SuperAdminRepository superAdminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Try to find super admin first (by username)
        Optional<SuperAdmin> superAdminOpt = superAdminRepository.findByUsername(username);
        if (superAdminOpt.isPresent()) {
            SuperAdmin superAdmin = superAdminOpt.get();
            return User.builder()
                    .username(superAdmin.getUsername())
                    .password(superAdmin.getPassword())
                    .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_SUPER_ADMIN")))
                    .build();
        }

        // Try to find student by email
        Optional<Student> studentOpt = studentRepository.findByEmail(username);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            return User.builder()
                    .username(student.getEmail())
                    .password(student.getPassword())
                    .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_STUDENT")))
                    .build();
        }

        // Try to find tutor by email
        Optional<Tutor> tutorOpt = tutorRepository.findByEmail(username);
        if (tutorOpt.isPresent()) {
            Tutor tutor = tutorOpt.get();
            return User.builder()
                    .username(tutor.getEmail())
                    .password(tutor.getPassword())
                    .authorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_TUTOR")))
                    .build();
        }

        throw new UsernameNotFoundException("User not found with username/email: " + username);
    }
}
