package practica2.castro.adrian.paises.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import practica2.castro.adrian.paises.entity.PaisEntity;

public interface IPaisRepository extends JpaRepository<PaisEntity, String>{

}
