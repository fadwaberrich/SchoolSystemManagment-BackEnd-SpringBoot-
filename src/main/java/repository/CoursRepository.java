package repository;

import model.Cours;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface CoursRepository extends JpaRepository<Cours,Integer> {




}
