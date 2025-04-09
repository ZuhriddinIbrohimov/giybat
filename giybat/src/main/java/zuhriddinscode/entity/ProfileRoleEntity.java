package zuhriddinscode.entity;

import jakarta.persistence.*;
import zuhriddinscode.enums.ProfileRoles;
import java.time.LocalDateTime;

@Table(name = "profile_role")
@Entity
public class ProfileRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "profile_id")
    private Integer profileId;

    @ManyToOne ( fetch = FetchType.LAZY )
    @JoinColumn (name = "profile_id", insertable = false, updatable = false )
    private ProfileEntity profile;

    @Enumerated(EnumType.STRING)
    @Column(name = "roles")
    private ProfileRoles role;

    @Column(name = "created_date")
    private LocalDateTime createdDate;





    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }

    public ProfileEntity getProfile() {
        return profile;
    }

    public void setProfile(ProfileEntity profile) {
        this.profile = profile;
    }

    public ProfileRoles getRole() {
        return role;
    }

    public void setRole(ProfileRoles role) {
        this.role = role;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}