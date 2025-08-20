package zuhriddinscode.repository;

import org.springframework.data.repository.CrudRepository;
import zuhriddinscode.entity.SmsProviderTokenHolderEntity;
import java.util.Optional;

public interface SmsProviderTokenHolderRepository extends CrudRepository<SmsProviderTokenHolderEntity, Integer> {

   Optional < SmsProviderTokenHolderEntity> findTop1By();
}