-- liquibase formatted sql
-- changeset nathaniel:1

CREATE TABLE users (
                       id UUID PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL
);

CREATE TABLE user_preferences (
                                  id UUID PRIMARY KEY,
                                  fitness_goal VARCHAR(255),
                                  intensity_preference VARCHAR(255),
                                  experience_level VARCHAR(255),
                                  limitations VARCHAR(255),
                                  user_id UUID NOT NULL UNIQUE,
                                  CONSTRAINT fk_user
                                      FOREIGN KEY (user_id)
                                          REFERENCES users (id)
                                          ON DELETE CASCADE
);