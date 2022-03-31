package com.bootstrap.dao.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "chromosome")
@Data
@EqualsAndHashCode(callSuper = false)
public class Chromosome extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@Column
	private String name;

	@Column
	private String size;

	@Column
	private Integer genes;

	@Column
	private String lang;

	@Column
	private String imgurl;

	@Column(columnDefinition = "text") // not fully portable
	@Type(type = "text")
	private String description;

	@OneToMany(mappedBy = "chromosome", cascade = CascadeType.MERGE, orphanRemoval = true)
	@OrderBy("name asc")
	private Set<Locus> loci = new HashSet<>();

	public Chromosome() {
	}

	public Chromosome(String name, String size, Integer genes, String description) {
		this.name = name;
		this.size = size;
		this.genes = genes;
		this.description = description;
	}

	public void addLocus(Locus locus) {
		this.loci.add(locus);
		locus.setChromosome(this);
	}

}
