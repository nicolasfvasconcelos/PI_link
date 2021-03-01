package com.GraceHopper.GraceHooper.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.GraceHopper.GraceHooper.model.Postagem;

public interface PostagemRepository extends JpaRepository<Postagem, Long>{

	public List<Postagem> findAllByTituloContainingIgnoreCase (String titulo);
	//public List<Postagem> findAllByData_publicacaoContainingIgnoreCase (Date data_publicacao);
}
