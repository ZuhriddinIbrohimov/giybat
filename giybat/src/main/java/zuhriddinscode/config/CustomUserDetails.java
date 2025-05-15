package zuhriddinscode.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import zuhriddinscode.entity.ProfileEntity;
import zuhriddinscode.enums.ProfileRole;
import zuhriddinscode.types.GeneralStatus;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private Integer id;
    private String name;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private GeneralStatus status;

    public CustomUserDetails(ProfileEntity profile,
                             List<ProfileRole> roleList) {
        this.id = profile.getId();
        this.name = profile.getName();
        this.username = profile.getUsername();
        this.password = profile.getPassword();
        this.status = profile.getStatus() ;

//        List<SimpleGrantedAuthority> roles = new ArrayList<>();
//        for (ProfileRole role : roleList){
//            roles.add(new SimpleGrantedAuthority(role.name()));
//        }
//        this.authorities = roles;
        this.authorities = roleList.stream().map(item -> new SimpleGrantedAuthority(item.name())).toList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status.equals(GeneralStatus.ACTIVE);
    }

    @Override
    public boolean isCredentialsNonExpired() {   // paroli yoki roli expired bo'lmaganmi
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}