package tn.esprit.spring.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.entity.Bloc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlocRepository extends JpaRepository<Bloc, Long> {
List<Bloc> findByCapaciteBlocLike(Long capaciteBloc);
@Query("SELECT b FROM Bloc b JOIN b.chambres bc WHERE bc.idChambre = :idChambre")
Bloc findByIdChambre(@Param("idChambre")Long idChambre);
}
