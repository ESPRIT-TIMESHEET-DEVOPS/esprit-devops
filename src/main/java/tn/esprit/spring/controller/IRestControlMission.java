package tn.esprit.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.entities.Mission;

import java.util.List;
import java.util.Optional;

public interface IRestControlMission {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Optional<Mission> GetById(@RequestParam int id);
    @GetMapping("/paginated")
    @ResponseStatus(HttpStatus.OK)
    List<Mission> Paginated(final int page, final int size, final String name, final String departement);
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Mission Add(@Validated @RequestBody Mission user) throws Exception;
    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    Mission Update(@Validated @RequestBody Mission user) throws Exception;
    @DeleteMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    void Delete(@RequestParam int missionId);
}
