package it.codingspace.crudjpa.model;

import it.codingspace.crudjpa.dto.CustomerDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Customers")
@AllArgsConstructor
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String surname;
	
	private String address;
	
	@Column(name="office_address")
	private String officeAddress;

	public static CustomerDTO toDTO(Customer c) {
		return new CustomerDTO(c.id.toString(), c.name, c.surname, c.address, c.officeAddress);
	}

	public static Customer fromDto(CustomerDTO cDto) {
		return new Customer(Long.valueOf(cDto.getId()), cDto.getName(),cDto.getSurname(),cDto.getAddress(),cDto.getOffice());
	}
}
