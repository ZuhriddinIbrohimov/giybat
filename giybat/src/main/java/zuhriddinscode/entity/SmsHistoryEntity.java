package zuhriddinscode.entity;

import jakarta.persistence.*;
import zuhriddinscode.enums.SmsType;
import java.time.LocalDateTime;

@Table(name = "sms_history")
@Entity
public class SmsHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name="name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "message")
    private String message;

    @Column(name ="created_date")
    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)// bu yozilmasa array tipni yozib ketadi.
    @Column(name = "sms_type")
    private SmsType smsType;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public SmsType getSmsType() {
        return smsType;
    }

    public void setSmsType(SmsType smsType) {
        this.smsType = smsType;
    }
}