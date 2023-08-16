package it.codingspace.crudjpa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerDTO {

	private String id;

	private String name;

	private String surname;

	private String address;

	private String office;
}
