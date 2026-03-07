package com.example.TrainerEffort.effortDAO;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;

import java.util.List;
import java.util.Objects;

@Entity
public class TrainerInfo {

    @Id
    private String id;

    private String name;
    private int experience;  // ✅ Newly added field
    private String phoneNumber;
    private String gender;
    private String email;
    private String mappedType; // internal / external
    private String role;       // trainer / mentor / sme
    private String password;

    @ElementCollection
    @CollectionTable(
        name = "trainer_skills",
        joinColumns = @JoinColumn(name = "trainer_id")
    )
    @Column(name = "skill_name")
    private List<String> skills;

    // Default constructor required by JPA
    public TrainerInfo() {
    }

    // All-args constructor
    public TrainerInfo(String id, String name, int experience, String phoneNumber, String gender, String email,
                       String mappedType, String role, String password, List<String> skills) {
        this.id = id;
        this.name = name;
        this.experience = experience;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.email = email;
        this.mappedType = mappedType;
        this.role = role;
        this.password = password;
        this.skills = skills;
    }

    // Getters and Setters

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMappedType() { return mappedType; }
    public void setMappedType(String mappedType) { this.mappedType = mappedType; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) { this.skills = skills; }

    @Override
    public String toString() {
        return "TrainerInfo [" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", experience=" + experience +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", mappedType='" + mappedType + '\'' +
                ", role='" + role + '\'' +
                ", password='" + password + '\'' +
                ", skills=" + skills +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainerInfo that = (TrainerInfo) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}