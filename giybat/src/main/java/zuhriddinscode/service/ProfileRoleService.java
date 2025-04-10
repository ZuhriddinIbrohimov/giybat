package zuhriddinscode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zuhriddinscode.entity.ProfileRoleEntity;
import zuhriddinscode.enums.ProfileRoles;
import zuhriddinscode.repository.ProfileRoleRepository;
import java.time.LocalDateTime;

@Service
public class ProfileRoleService {

    @Autowired
    private ProfileRoleRepository profileRoleRepository;

    public void create (Integer profileId, ProfileRoles role) {
        ProfileRoleEntity entity = new ProfileRoleEntity();
        entity.setProfileId(profileId);
        entity.setRole(role);
        entity.setCreatedDate(LocalDateTime.now());
        profileRoleRepository.save(entity);
    }

    public void deleteRoles(Integer profileId ) {
        profileRoleRepository.deleteByProfileId(profileId);
    }
}