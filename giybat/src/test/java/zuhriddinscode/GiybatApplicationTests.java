package zuhriddinscode;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zuhriddinscode.service.sms.SmsSendService;

@SpringBootTest
class GiybatApplicationTests {

	@Autowired
	private SmsSendService smsSendService;

	@Test
	void contextLoads() {
//		smsSendService.getToken();
		smsSendService.sendSms("998936259577","This is test from Eskiz");
	}
}