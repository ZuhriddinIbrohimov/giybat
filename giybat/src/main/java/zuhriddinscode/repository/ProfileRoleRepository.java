package zuhriddinscode.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import zuhriddinscode.entity.ProfileRoleEntity;

public interface ProfileRoleRepository extends CrudRepository<ProfileRoleEntity, Integer> {

    @Transactional
    @Modifying
    void deleteByProfileId(Integer profileId);

}