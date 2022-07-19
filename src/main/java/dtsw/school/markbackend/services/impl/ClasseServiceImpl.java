package dtsw.school.markbackend.services.impl;

import dtsw.school.markbackend.exceptions.ResourceNotFoundException;
import dtsw.school.markbackend.models.Classe;
import dtsw.school.markbackend.payload.request.ClasseRequest;
import dtsw.school.markbackend.repository.ClasseDAO;
import dtsw.school.markbackend.services.ClasseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClasseServiceImpl implements ClasseService {
    private ClasseDAO classeDAO;

    public ClasseServiceImpl(ClasseDAO classeDAO) {
        this.classeDAO = classeDAO;
    }

    @Override
    public Classe createClasse(ClasseRequest classeRequest) {
        Classe classe = new Classe(classeRequest.getName());
        classe = classeDAO.save(classe);
        return classe;
    }

    @Override
    public Classe updateClasse(ClasseRequest classeRequest, Long id) {
        Classe classe = classeDAO.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Classe","Id",id));
        classe.setName(classeRequest.getName());
        classe = classeDAO.save(classe);
        return classe;
    }

    @Override
    public void deleteClasse(Long id) {
        Classe classe = classeDAO.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Classe","Id",id));
        classeDAO.deleteById(id);
    }

    @Override
    public Classe getClasseById(Long id) {
        Classe classe = classeDAO.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Classe","Id",id));
        return classe;
    }

    @Override
    public List<Classe> getAllClasses() {
        List<Classe> classeList = classeDAO.findAll();
        return classeList;
    }
}
