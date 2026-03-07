package com.example.TrainerEffort.Specification;

import com.example.TrainerEffort.effortDAO.TrainerInfo;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Join;
import java.util.List;

public class TrainerSpecification {
	public static Specification<TrainerInfo> hasName(String name) {
        return (root, query, cb) -> {
            if (name == null || name.isEmpty()) return null;
            return cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }

    public static Specification<TrainerInfo> hasExperienceGreaterThan(Integer minExp) {
        return (root, query, cb) -> {
            if (minExp == null) return null;
            return cb.greaterThanOrEqualTo(root.get("experience"), minExp);
        };
    }

    public static Specification<TrainerInfo> hasExperienceLessThan(Integer maxExp) {
        return (root, query, cb) -> {
            if (maxExp == null) return null;
            return cb.lessThanOrEqualTo(root.get("experience"), maxExp);
        };
    }

    public static Specification<TrainerInfo> hasMappedType(String mappedType) {
        return (root, query, cb) -> {
            if (mappedType == null || mappedType.isEmpty()) return null;
            return cb.equal(cb.lower(root.get("mappedType")), mappedType.toLowerCase());
        };
    }

    public static Specification<TrainerInfo> hasRole(String role) {
        return (root, query, cb) -> {
            if (role == null || role.isEmpty()) return null;
            return cb.equal(cb.lower(root.get("role")), role.toLowerCase());
        };
    }

    public static Specification<TrainerInfo> hasGender(String gender) {
        return (root, query, cb) -> {
            if (gender == null || gender.isEmpty()) return null;
            return cb.equal(cb.lower(root.get("gender")), gender.toLowerCase());
        };
    }

    public static Specification<TrainerInfo> hasSkills(List<String> skills) {
        return (root, query, cb) -> {
            if (skills == null || skills.isEmpty()) return null;
            query.distinct(true); // avoid duplicates when joining
            Join<TrainerInfo, String> skillJoin = root.join("skills");
            return skillJoin.in(skills);
        };
    }
}
