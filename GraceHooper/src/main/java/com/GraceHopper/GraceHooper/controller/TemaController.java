package com.GraceHopper.GraceHooper.controller;

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
import com.GraceHopper.GraceHooper.model.Tema;
import com.GraceHopper.GraceHooper.repository.TemaRepository;

@RestController
@RequestMapping("/Tema")
@CrossOrigin

public class TemaController {
	@Autowired
	private TemaRepository repository;
	
	@GetMapping("")
	private ResponseEntity<List<Tema>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id_postagem}")
	public ResponseEntity<Tema> GetById_tema(@PathVariable long id_tema){
		return repository.findById(id_tema)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/categoria/{categoria}")
	public ResponseEntity<List<Tema>> GetByTitulo(@PathVariable String categoria){
		return ResponseEntity.ok(repository.findAllByCategoriaContainingIgnoreCase(categoria));
	}
	
	@PostMapping
	public ResponseEntity<Tema> post (@RequestBody Tema tema){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(tema));
		
	}
	
	@PutMapping
	public ResponseEntity<Tema> put (@RequestBody Tema tema) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(tema));
	}
	
	@DeleteMapping("{id_tema}")
	public void delete(@PathVariable long id_tema) {
		repository.deleteById(id_tema);
	}
	
	
	
	
}
