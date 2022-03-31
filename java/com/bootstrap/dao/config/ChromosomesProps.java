package com.bootstrap.dao.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "chromosomes.list")
@Data
public class ChromosomesProps {

	private int pageSize;

}
