package practica2.castro.adrian.paises.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import practica2.castro.adrian.paises.entity.PaisEntity;
import practica2.castro.adrian.paises.model.Pais;
import practica2.castro.adrian.paises.repository.IPaisRepository;

@RestController
@RequestMapping("paises")
public class PaisesService {
	
	@Autowired
	private IPaisRepository repository;
	
	private static Pais mapToPais(PaisEntity paisEntity) {
		Pais pais = new Pais();
		BeanUtils.copyProperties(paisEntity, pais);
		return pais;
	}
	
	private static PaisEntity mapToPaisEntity(Pais pais) {
		PaisEntity paisEntity = new PaisEntity();
		BeanUtils.copyProperties(pais, paisEntity);
		return paisEntity;
	}
	
	@GetMapping
	public ResponseEntity<?> getAll(){
		
		List<PaisEntity> paisesBBDD = repository.findAll();
		ResponseEntity<?> response = null;
		
		if(paisesBBDD.size()>0) {
			List<Pais> paises = paisesBBDD.stream().map(PaisesService::mapToPais).collect(Collectors.toList());
			response = new ResponseEntity<List<Pais>>(paises, HttpStatus.OK);
		}
		else {
			response = new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> getPais(@PathVariable String id){
		
		PaisEntity paisEntity = repository.findById(id).orElse(null);
		ResponseEntity<?> response = null;
		
		if(paisEntity!=null) {
			Pais pais = mapToPais(paisEntity);
			response = new ResponseEntity<Pais>(pais, HttpStatus.OK);
		}
		else {
			response = new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
	@PostMapping
	public ResponseEntity<?> postPais(@PathVariable Pais pais){
		
		ResponseEntity<?> response = null;
		
		if(pais.getId()!=null && pais.getNombre()!=null) {
			PaisEntity paisEntity = mapToPaisEntity(pais);
			repository.save(paisEntity);
			response = new ResponseEntity<Void>(HttpStatus.CREATED);
		}
		else {
			response = new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@PutMapping
	public ResponseEntity<?> putPais(@PathVariable Pais pais){
		
		PaisEntity paisEntity = repository.findById(pais.getId()).orElse(null);
		ResponseEntity<?> response = null;
		
		if(paisEntity!=null) {
			paisEntity=mapToPaisEntity(pais);
			repository.save(paisEntity);
			response = new ResponseEntity<Void>(HttpStatus.OK);
		}
		else {
			response = new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
	@DeleteMapping
	public ResponseEntity<?> deletePais(@PathVariable String id){
		
		ResponseEntity<?> response = null;
		PaisEntity paisEntity = repository.findById(id).orElse(null);
		
		if(paisEntity!=null) {
			repository.delete(paisEntity);
			response = new ResponseEntity<Void>(HttpStatus.OK);
		}
		else {
			response = new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		return response;
	}

}
