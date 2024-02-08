package example.brief.MyRh.services.serviceImpl;

import example.brief.MyRh.dtos.societe.SpecialityDto;
import example.brief.MyRh.entities.Speciality;
import example.brief.MyRh.repositories.SpecialityRepository;
import example.brief.MyRh.services.SpecialityService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SpecialityServiceImpl implements SpecialityService {
    final SpecialityRepository specialityRepository;
    @Override
    public void addSpeciality() {
        Stream.of("Java", "Javascript").forEach(specialityName->{
            Speciality speciality = Speciality.builder().name(specialityName).build();
            this.specialityRepository.save(speciality);
        });
    }
}
