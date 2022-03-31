package com.bootstrap.dao.model;

import java.util.Set;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import lombok.Getter;

@Relation(value = "chromosome", collectionRelation = "chromosomes")
public class ChromosomeResource extends ResourceSupport {


	@Getter
	private final String name;
	@Getter
	private final String size;
	@Getter
	private final Integer genes;
	@Getter
	private final String desc;
	@Getter
	private final Set<Locus> loci;

	public ChromosomeResource() {
		this.name = null;
		this.size = null;
		this.genes = null;
		this.desc = null;
		this.loci = null;
	}

	public ChromosomeResource(Chromosome chromosome) {
		this.name = chromosome.getName();
		this.size = chromosome.getSize();
		this.genes = chromosome.getGenes();
		this.desc = chromosome.getDescription();
		this.loci = chromosome.getLoci();
	}

}
