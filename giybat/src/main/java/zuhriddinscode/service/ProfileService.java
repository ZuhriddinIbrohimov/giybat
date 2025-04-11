package zuhriddinscode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zuhriddinscode.entity.ProfileEntity;
import zuhriddinscode.exps.AppBadException;
import zuhriddinscode.repository.ProfileRepository;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public ProfileEntity getById (int id) {
        return profileRepository.findByIdAndVisibleTrue(id).orElseThrow(() -> {
            throw new AppBadException("Profile not found");
        });
    }

}