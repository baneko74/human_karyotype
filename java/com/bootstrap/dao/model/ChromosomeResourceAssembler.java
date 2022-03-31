package com.bootstrap.dao.model;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.bootstrap.controllers.ChromosomeController;

public class ChromosomeResourceAssembler extends ResourceAssemblerSupport<Chromosome, ChromosomeResource> {

	public ChromosomeResourceAssembler() {
		super(ChromosomeController.class, ChromosomeResource.class);
	}

	@Override
	protected ChromosomeResource instantiateResource(Chromosome chrom) {
		return new ChromosomeResource(chrom);
	}

	@Override
	public ChromosomeResource toResource(Chromosome chrom) {
		return createResourceWithId(chrom.getId(), chrom);
	}

}
