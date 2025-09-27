package zuhriddinscode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zuhriddinscode.entity.SmsHistoryEntity;
import zuhriddinscode.enums.SmsType;
import zuhriddinscode.repository.SmsHistoryRepository;

import java.time.LocalDateTime;

@Service
public class SmsHistoryService {

    @Autowired
    private SmsHistoryRepository smsHistoryRepository;

    public Boolean check(String phoneNumber, String code) {
        ///  find last sms by phoneNumber
        // check code
        /// check time
    return null;
    }

    public void create( String phoneNumber, String message, SmsType smsType ){
        SmsHistoryEntity entity = new SmsHistoryEntity();
        entity.setCreatedDate(LocalDateTime.now());
        entity.setPhone(phoneNumber);
        entity.setMessage(message);
        entity.setSmsType(smsType);
        smsHistoryRepository.save(entity);
    }


}
