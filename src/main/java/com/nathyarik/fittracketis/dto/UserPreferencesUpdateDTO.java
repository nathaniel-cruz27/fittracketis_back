package com.nathyarik.fittracketis.dto;

public class UserPreferencesUpdateDTO {
    private String fitnessGoal;
    private String experienceLevel;
    private String intensityPreference;
    private String limitations;

    public UserPreferencesUpdateDTO() {}

    public String getFitnessGoal() { return fitnessGoal; }
    public void setFitnessGoal(String fitnessGoal) { this.fitnessGoal = fitnessGoal; }

    public String getExperienceLevel() { return experienceLevel; }
    public void setExperienceLevel(String experienceLevel) { this.experienceLevel = experienceLevel; }

    public String getIntensityPreference() { return intensityPreference; }
    public void setIntensityPreference(String intensityPreference) { this.intensityPreference = intensityPreference; }

    public String getLimitations() { return limitations; }
    public void setLimitations(String limitations) { this.limitations = limitations; }
}