package tn.esprit.spring.services;

import tn.esprit.spring.entities.Mission;

import java.util.List;
import java.util.Optional;

public interface IMissionService {
    Mission Add(Mission mission) throws Exception;
    Mission Update(Mission mission) throws Exception;
    void Delete(int missionId);
    Optional<Mission> GetById(int missionId);
    List<Mission> GetPaginated(final int page, final int size, final String name, final String departement);
    Iterable<Mission> GetAll();
}
