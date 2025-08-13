package zuhriddinscode.dto.sms;

import jakarta.validation.constraints.NotBlank;

public class SmsVerificationDTO {

    @NotBlank(message = "Phone required")
    private String phone;
    @NotBlank(message = "Code required")
    private String code;



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
