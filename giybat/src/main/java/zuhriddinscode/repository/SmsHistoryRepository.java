package zuhriddinscode.repository;

import org.springframework.data.repository.CrudRepository;
import zuhriddinscode.entity.SmsHistoryEntity;


public interface SmsHistoryRepository extends CrudRepository <SmsHistoryEntity,String> {

}