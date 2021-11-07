package tn.esprit.spring.services;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;

import java.util.List;

public interface IEntrepriseService {

	int ajouterEntreprise(Entreprise entreprise);
	int ajouterDepartement(Departement dep);
	void affecterDepartementAEntreprise(int depId, int entrepriseId);
	List<String> getAllDepartementsNamesByEntreprise(int entrepriseId);
	void deleteEntrepriseById(int entrepriseId);
	void deleteDepartementById(int depId);
	Entreprise getEntrepriseById(int entrepriseId);
}
