package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.services.MissionServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
public class RestControlMission implements IRestControlMission{
    private MissionServiceImpl service;
    @Autowired
    public RestControlMission(MissionServiceImpl service){
        this.service = service;
    }
    @Override
    public Optional<Mission> GetById(int id) {return this.service.GetById(id);}

    @Override
    public List<Mission> Paginated(int page, int size, String name, String departement) {
        return this.service.GetPaginated(page,size,name,departement);
    }

    @Override
    public Mission Add(@Validated  Mission user) throws Exception {return this.service.Add(user);}

    @Override
    public Mission Update(@Validated Mission user) throws Exception {return this.service.Update(user);}

    @Override
    public void Delete(int missionId) {this.service.Delete(missionId);}
}
