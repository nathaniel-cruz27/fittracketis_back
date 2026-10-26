package com.nathyarik.fittracketis.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user_preferences")
public class UserPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String fitnessGoal;
    private String intensityPreference;
    private String experienceLevel;
    private String limitations;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Constructors
    public UserPreference() {}

    // Getters i Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getFitnessGoal() { return fitnessGoal; }
    public void setFitnessGoal(String fitnessGoal) { this.fitnessGoal = fitnessGoal; }

    public String getIntensityPreference() { return intensityPreference; }
    public void setIntensityPreference(String intensityPreference) { this.intensityPreference = intensityPreference; }

    public String getExperienceLevel() { return experienceLevel; }
    public void setExperienceLevel(String experienceLevel) { this.experienceLevel = experienceLevel; }

    public String getLimitations() { return limitations; }
    public void setLimitations(String limitations) { this.limitations = limitations; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
