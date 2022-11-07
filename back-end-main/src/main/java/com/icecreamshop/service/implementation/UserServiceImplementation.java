package com.icecreamshop.service.implementation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.icecreamshop.exception.ConflictException;
import com.icecreamshop.exception.IncorrectCredentialsException;
import com.icecreamshop.model.domain.User;
import com.icecreamshop.model.request.LoginRequest;
import com.icecreamshop.model.request.RegisterRequest;
import com.icecreamshop.model.response.RegisterResponse;
import com.icecreamshop.repository.UserRepository;
import com.icecreamshop.service.interfaces.UserService;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private void checkEmail(String email) throws ConflictException {
        if (userRepository.existsByEmailIgnoreCase(email)) {
            throw new ConflictException(String.format("The email: '%s' was already used.", email));
        }
    }

    private void checkUsername(String username) throws ConflictException {
        if (userRepository.existsByUsernameIgnoreCase(username)) {
            throw new ConflictException(String.format("The username: '%s' was already used.", username));
        }
    }

    @Override
    public RegisterResponse register(RegisterRequest request) throws ConflictException {
        log.info("[UserService] register user: {}", request);
        checkEmail(request.getEmail());
        checkUsername(request.getUsername());
        User user = modelMapper.map(request, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        RegisterResponse response = modelMapper.map(user, RegisterResponse.class);
        log.info("[UserService] registered user: {}", response);
        return response;
    }

    @Override
    public void login(LoginRequest request) throws IncorrectCredentialsException {
        log.info("[UserService] login user: {}", request);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),
                    request.getPassword()));
        } catch (BadCredentialsException exception) {
            throw new IncorrectCredentialsException();
        }
        log.info("[UserService] logged in user: {}", request);
    }
}
