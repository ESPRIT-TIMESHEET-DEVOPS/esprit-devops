package tn.esprit.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.RecoverableDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.repository.MissionRepository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class MissionServiceImpl implements IMissionService{
    private MissionRepository missionRepository;
    @Autowired
    public MissionServiceImpl(MissionRepository missionRepository){this.missionRepository = missionRepository;}
    @Override
    public Mission Add(@Validated Mission mission) throws Exception {
        try {
            return missionRepository.save(mission);
        } catch (NonTransientDataAccessException | RecoverableDataAccessException | TransientDataAccessException e){
            throw new Exception(e);
        }
    }

    @Override
    public Mission Update(@Validated Mission mission) throws Exception{
        try {
            return missionRepository.save(mission);
        } catch (NonTransientDataAccessException | RecoverableDataAccessException | TransientDataAccessException e){
            throw new Exception(e);
        }
    }

    @Override
    public void Delete(@NotNull int missionId) {
        missionRepository.deleteById(missionId);
    }

    @Override
    public Optional<Mission> GetById(int missionId) {
        return missionRepository.findById(missionId);
    }

    @Override
    public List<Mission> GetPaginated(final int page, final int size, final String name, final String departement) {
        return missionRepository.findAllByNameLikeAndDepartementNameOrderByName(name,departement,PageRequest.of(page, size));
    }
    @Override
    public Iterable<Mission> GetAll(){
        return missionRepository.findAll();
    }
}
