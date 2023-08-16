package it.codingspace.crudjpa.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import it.codingspace.crudjpa.dto.CustomerDTO;
import it.codingspace.crudjpa.dto.RequestDTO;
import it.codingspace.crudjpa.dto.ResponseDTO;
import it.codingspace.crudjpa.model.Customer;
import it.codingspace.crudjpa.model.CustomerRepository;

@RestController
@RequestMapping("customers")
@CrossOrigin(origins = "http://localhost:8081")
public class CrudCustomerController {

	@Autowired
	CustomerRepository repo;

	@GetMapping("all")
	public ResponseEntity<ResponseDTO> getAllCustomers() {
		ResponseDTO re = new ResponseDTO();		
		try {
	
			if (repo.count() > 0) {
				List<CustomerDTO> customers = new ArrayList<CustomerDTO>();
				
				repo.findAll().forEach( c -> customers.add(Customer.toDTO(c)));
				re.setData(customers);
				re.setMsg("");

				return new ResponseEntity<ResponseDTO>(re, HttpStatus.OK);
			}
			else {
				re.setMsg("No content retrieved for customers. Table is empty");				
				return new ResponseEntity<ResponseDTO>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			re.setMsg(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseDTO> getCustomerById(@PathVariable("id") String id) {
		ResponseDTO re = new ResponseDTO();
		try {
			if (repo.count() > 0) {
				
				Optional<Customer> opc = repo.findById(Long.valueOf(id));
				if(opc.isPresent()) {
					re.setData(opc.get());
					re.setMsg("");
					return new ResponseEntity<ResponseDTO>(re, HttpStatus.OK);
				}
				else {
					re.setMsg(String.format("No exists customer with id %s in Customer table.", id));				
					return new ResponseEntity<ResponseDTO>(HttpStatus.NOT_FOUND);					
				}
			}
			else {
				re.setMsg("No content retrieved for customers. Table is empty");				
				return new ResponseEntity<ResponseDTO>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			re.setMsg(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("")
	public ResponseEntity<ResponseDTO> saveCustomer(@RequestBody RequestDTO dto) {
		ResponseDTO re = new ResponseDTO();
		try {
			if(dto.getData() != null && dto.getData() instanceof CustomerDTO ) {
				CustomerDTO cDto = (CustomerDTO)dto.getData();
				Optional<Customer> opc = repo.findById(Long.valueOf(cDto.getId()));
				if(opc.isEmpty()) {
					repo.save(Customer.fromDto(cDto));
					re.setData(opc.get());
					re.setMsg("");
					return new ResponseEntity<ResponseDTO>(re, HttpStatus.OK);
				}
				else {
					re.setMsg(String.format("Already exists customer with id %s in Customer table.", cDto.getId()));				
					return new ResponseEntity<ResponseDTO>(HttpStatus.CONFLICT);					
				}
			}
			else {
				re.setMsg("No content retrieved for customers. Table is empty");				
				return new ResponseEntity<ResponseDTO>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			re.setMsg(e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("")
	public void updateCustomer(@RequestBody CustomerDTO dto) {

	}

	@DeleteMapping("/{id}")
	public void deleteCustomer(@PathVariable("id") String id) {

	}

}
