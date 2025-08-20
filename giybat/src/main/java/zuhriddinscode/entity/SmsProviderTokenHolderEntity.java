package zuhriddinscode.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table (name = "sms_provider_token_holder)")
public class SmsProviderTokenHolderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column (name = "token",columnDefinition = "text")
    private String token;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "expired_date")
    private LocalDateTime expiredDate;

    public LocalDateTime getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDateTime expiredDate) {
        this.expiredDate = expiredDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}