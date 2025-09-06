package zuhriddinscode.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import zuhriddinscode.entity.SmsProviderTokenHolderEntity;
import java.util.Optional;

@Repository
public interface SmsProviderTokenHolderRepository extends CrudRepository<SmsProviderTokenHolderEntity, Integer> {

   Optional < SmsProviderTokenHolderEntity> findTop1By();
}