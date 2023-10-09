package repository;

import model.Cours;
import model.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

public interface ExamenRepository extends JpaRepository<Examen,Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Examen e SET e.date = :newDate, e.nom = :newNom WHERE e.id = :id")
    void updateExamen(@Param("id") int id, @Param("newDate") Date newDate, @Param("newNom") String newNom);

}
