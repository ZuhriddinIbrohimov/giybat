package zuhriddinscode.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import zuhriddinscode.dto.SmsAuthDTO;
import zuhriddinscode.dto.sms.SmsAuthResponseDTO;
import zuhriddinscode.dto.sms.SmsRequestDTO;
import zuhriddinscode.entity.SmsProviderTokenHolderEntity;
import zuhriddinscode.repository.SmsProviderTokenHolderRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SmsSendService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${eskiz.url}")
    private String SmsUrl;

    @Value("${eskiz.email}")
    private String email;

    @Value("${eskiz.password}")
    private String password;

    @Autowired
    private SmsProviderTokenHolderRepository smsProviderTokenHolderRepository;

    public String sendSms(String phoneNumber, String message) {
        //check
        String token = getToken();
        //header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content_Type", "application/json");
        headers.set("Authorization", "Bearer " + token);
        //body
        SmsRequestDTO body = new SmsRequestDTO();
        body.setMobile_phone(phoneNumber);
        body.setMessage(message);
        body.setFrom("4546");
        //send request
        HttpEntity<String> entity = new HttpEntity<>(message, headers);
        //login-> token
        //send sms
        ResponseEntity<String> response = restTemplate.exchange(
                SmsUrl +"/message/sms/send",
                HttpMethod.POST,
                entity,
                String.class);
        //check response
        if (response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Sms not send");
        }
//        System.out.println(response.getStatusCode());
        System.out.println(response.toString());

        return null;
    }



    public String getToken() {
        Optional<SmsProviderTokenHolderEntity> optional = smsProviderTokenHolderRepository.findTop1();
        if (optional.isEmpty()) {
            String token = getTokenFromProvider();
            SmsProviderTokenHolderEntity entity = new SmsProviderTokenHolderEntity();
            entity.setToken(token);
            entity.setCreatedDate(LocalDateTime.now());
            smsProviderTokenHolderRepository.save(entity);
            return token;
        }
        SmsProviderTokenHolderEntity entity = optional.get();
        LocalDateTime expDate = entity.getCreatedDate().plusMonths(1);
        if (LocalDateTime.now().isAfter(expDate)) {
            return entity.getToken();
        }
        String token = getTokenFromProvider();
        entity.setToken(token);
        entity.setCreatedDate(LocalDateTime.now());
        smsProviderTokenHolderRepository.save(entity);
        return null;
    }

    public String getTokenFromProvider() {
        SmsAuthDTO smsAuthDTO = new SmsAuthDTO();
        smsAuthDTO.setEmail(email);
        smsAuthDTO.setPassword(password);
        String response = restTemplate.postForObject(SmsUrl + "/auth/login", smsAuthDTO, String.class);

        try {
            JsonNode parent = new ObjectMapper().readTree(response);
            JsonNode data = parent.get("data");
            String token = data.get("token").asText();
            System.out.println("token:"+token);
            System.out.println(response);
            return response;
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}