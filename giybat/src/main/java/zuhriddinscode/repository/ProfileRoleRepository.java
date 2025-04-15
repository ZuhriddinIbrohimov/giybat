package zuhriddinscode.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import zuhriddinscode.entity.ProfileEntity;
import zuhriddinscode.entity.ProfileRoleEntity;
import zuhriddinscode.enums.ProfileRoles;
import java.util.List;

public interface ProfileRoleRepository extends CrudRepository<ProfileRoleEntity, Integer> {

    @Transactional
    @Modifying
    void deleteByProfileId(Integer profileId);

    @Query("select p.roles From ProfileRoleEntity p where p.profileId =?1")
    List<ProfileRoles> getAllRolesListByProfileId(Integer profileId);

}