package zuhriddinscode.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import zuhriddinscode.entity.ProfileEntity;
import java.util.Optional;

@Repository
public interface ProfileRepository extends CrudRepository<ProfileEntity, Integer> {

    Optional<ProfileEntity> findByUsernameAndVisibleTrue (String username );

}