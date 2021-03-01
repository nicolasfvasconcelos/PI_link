package com.GraceHopper.GraceHooper.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.GraceHopper.GraceHooper.model.Postagem;
import com.GraceHopper.GraceHooper.repository.PostagemRepository;

@RestController 				//Indica que essa classe é um Controller
@RequestMapping("/Postagem") 	//Define a URI utilizada
@CrossOrigin("*") 				//Aceita requisições de qualquer origem por causa do asterisco

public class PostagemController {
	
	@Autowired
	private PostagemRepository repository;
	
	@GetMapping("")
	private ResponseEntity<List<Postagem>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id_postagem}")
	public ResponseEntity<Postagem> GetById_postagem(@PathVariable long id_postagem){
		return repository.findById(id_postagem)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
//	@GetMapping("/data_publicacao/{data_publicacao}")
//	public  ResponseEntity<List<Postagem>> GetByData_publicacao(@PathVariable Date data_publicacao){
//		return ResponseEntity.ok(repository.findAllByData_publicacaoContainingIgnoreCase(data_publicacao));
//	}
	
	@PostMapping
	public ResponseEntity<Postagem> post (@RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
		
	}
	
	@PutMapping
	public ResponseEntity<Postagem> put (@RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}
	
	@DeleteMapping("{id_postagem}")
	public void delete(@PathVariable long id_postagem) {
		repository.deleteById(id_postagem);
	}
}
	
