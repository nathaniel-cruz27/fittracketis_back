package com.nathyarik.fittracketis.service;

import com.nathyarik.fittracketis.dto.UserRegistrationDTO;
import com.nathyarik.fittracketis.dto.UserUpdateDTO;
import com.nathyarik.fittracketis.entity.User;
import com.nathyarik.fittracketis.entity.UserPreference;
import com.nathyarik.fittracketis.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public User updateUser(UUID id, UserUpdateDTO dto) {
        User user = getUserById(id);

        if (dto.getName() != null) {
            user.setName(dto.getName());
        }

        UserPreference pref = user.getPreference();
        if (pref == null) {
            pref = new UserPreference();
            pref.setUser(user);
            user.setPreference(pref);
        }

        if (dto.getFitnessGoal() != null) pref.setFitnessGoal(dto.getFitnessGoal());
        if (dto.getExperienceLevel() != null) pref.setExperienceLevel(dto.getExperienceLevel());
        if (dto.getIntensityPreference() != null) pref.setIntensityPreference(dto.getIntensityPreference());
        if (dto.getLimitations() != null) pref.setLimitations(dto.getLimitations());

        return userRepository.save(user);
    }

    public void deleteUser(UUID id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }
}