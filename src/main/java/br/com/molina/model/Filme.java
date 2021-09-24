package br.com.molina.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class Filme {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int year;
	private String title;
	@ElementCollection(targetClass=String.class)
	private List<String> studios = new ArrayList<String>();
	@ElementCollection(targetClass=String.class)
	private List<String> producers = new ArrayList<String>();
	private boolean winner;
}
