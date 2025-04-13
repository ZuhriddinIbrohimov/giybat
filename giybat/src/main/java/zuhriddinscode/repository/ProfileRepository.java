package zuhriddinscode.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import zuhriddinscode.entity.ProfileEntity;
import zuhriddinscode.types.GeneralStatus;
import java.util.Optional;

//@Repository
public interface ProfileRepository extends CrudRepository<ProfileEntity, Integer> {

    Optional<ProfileEntity> findByUsernameAndVisibleTrue (String username );
    Optional<ProfileEntity> findByIdAndVisibleTrue(Integer id );

    @Modifying
    @Transactional
    @Query("update ProfileEntity set status =?2 where id = ?1")
    void changeStatus(Integer profileId, GeneralStatus status);

}