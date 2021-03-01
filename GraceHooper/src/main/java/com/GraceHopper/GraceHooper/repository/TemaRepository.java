package com.GraceHopper.GraceHooper.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.GraceHopper.GraceHooper.model.Tema;

public interface TemaRepository extends JpaRepository<Tema, Long>{
	public List<Tema> findAllByCategoriaContainingIgnoreCase (String categoria);

}
