package com.test.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.test.model.Company;

public class CompanyItemProcesser implements ItemProcessor<Company, Company> {

	private static final Logger log = LoggerFactory.getLogger(CompanyItemProcesser.class);

	public Company process(final Company company) throws Exception {

		log.info("company::" + company.toString());

		return company;
	}
}
