package com.nathyarik.fittracketis.service;

import com.nathyarik.fittracketis.dto.UserRegistrationDTO;
import com.nathyarik.fittracketis.entity.User;
import com.nathyarik.fittracketis.entity.UserPreference;
import com.nathyarik.fittracketis.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(UserRegistrationDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("This email is already registered");
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword()); // to be hashed

        UserPreference preference = new UserPreference();
        preference.setFitnessGoal(dto.getFitnessGoal());
        preference.setExperienceLevel(dto.getExperienceLevel());

        preference.setUser(user);
        user.setPreference(preference);

        return userRepository.save(user);
    }
}