package com.vsu.Services;

import com.vsu.Models.User;
import com.vsu.Repository.CountryRepository;
import com.vsu.Repository.RoleRepository;
import com.vsu.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final CountryRepository countryRepository;
    private final SmtpMailSender smtpMailSender;


    public boolean addUser(User user){
        User userFromDB = userRepository.findByEmail(user.getEmail());
        if(userFromDB!=null){
            return false;
        }
        user.setEnabled(false);
        user.setRole(roleRepository.findRoleByName("user"));
        user.setRegistrationDate(LocalDate.now());
        user.setActivationCode(UUID.randomUUID().toString());
        userRepository.save(user);
        if(!StringUtils.isEmpty(user.getEmail())){
            String message = String.format(
                    "Здравствуйте, %s \n" + "Пожалуйста, чтобы активировать ваш аккаунт, перейдите по ссылке: " + "http://localhost:8090/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );
            smtpMailSender.send(user.getEmail(),"Activation code",message);
        }
        return true;
    }

    public User findById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }
    public void updateUser(User user,
                           String email,
                           String username,
                           String country,
                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                           LocalDate birthdate,
                           String vkLink,
                           String googleLink,
                           String twitterLink){
        if(email!=null){
            user.setEmail(email);
        }
        if(username!=null){
            user.setUsername(username);
        }
        if(country!=null){
            user.setCountry(countryRepository.findCountryByName(country));
        }
        if(birthdate!=null){
                user.setBirthdate(birthdate);
        }
        if(vkLink!=null){
            user.setVkLink(vkLink);
        }
        if(googleLink!=null){
            user.setGoogleLink(googleLink);
        }
        if(twitterLink!=null){
            user.setTwitterLink(twitterLink);
        }
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if(user!=null){
            return user;
        }
        else{
            throw new UsernameNotFoundException(email);
        }
    }

    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        if(user == null){
            return false;
        }
        user.setActivationCode(null);
        user.setEnabled(true);
        userRepository.save(user);
        return true;
    }
}
