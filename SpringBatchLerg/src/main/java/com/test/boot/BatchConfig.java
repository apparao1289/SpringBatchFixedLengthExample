package com.test.boot;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.FixedLengthTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.test.model.Company;
import com.test.process.CompanyItemProcesser;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	@Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;
	
	@Bean
	public Job importProductsJob() throws Exception {

		return jobBuilderFactory.get("importCompanyJob").
				start(step())
				.build();
	}
	
	@Bean
	public Step step() {
		return stepBuilderFactory.get("importCompanyStep")
                .<Company, Company>chunk(100)
                .reader(companyItemReader())
                .processor(processor())
                .writer(writer())
                .allowStartIfComplete(true)
                .build();
	}
	
	@Bean
	public ItemReader<Company> companyItemReader() {
		FlatFileItemReader<Company> reader = new FlatFileItemReader<Company>();
		reader.setResource(new ClassPathResource("lerg1.dat"));
		reader.setLineMapper(companyLineMapper());
		return reader;
	}

	@Bean
    public JdbcBatchItemWriter<Company> writer() {
        JdbcBatchItemWriter<Company> writer = new JdbcBatchItemWriter<Company>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Company>());
        writer.setSql("INSERT INTO COMPANY (column1, column2,column3,column4,column5,column6,column7,column8,column9,column10,column11,column12,column13,column14,column15,column16,column17)"
        		+ " VALUES (:column1, :column2,:column3,:column4,:column5,:column6,:column7,:column8,:column9,:column10,:column11,:column12,:column13,:column14,:column15,:column16,:column17)");
        writer.setDataSource(dataSource);
        return writer;
    }
	
	@Bean
	public LineMapper<Company> companyLineMapper() {
		DefaultLineMapper<Company> mapper = new DefaultLineMapper<Company>();
		mapper.setLineTokenizer(companyLineTokenizer());
		mapper.setFieldSetMapper(new BeanWrapperFieldSetMapper<Company>() {{
            setTargetType(Company.class);
        }});
		return mapper;
	}

	@Bean
	public LineTokenizer companyLineTokenizer() {
		FixedLengthTokenizer tokenizer = new FixedLengthTokenizer();
		tokenizer.setColumns(new Range[] { new Range(1, 54), new Range(55, 74), new Range(75, 86), new Range(87, 96),new Range(97, 116),
				                           new Range(117, 127),new Range(128, 177),new Range(178, 207),new Range(208, 237),new Range(238, 267),
				                           new Range(268, 277),new Range(278, 297),new Range(298, 317),new Range(318, 328),new Range(329, 344),
				                           new Range(345, 348),new Range(349, 350)});
		tokenizer.setNames(new String[] { "column1", "column2", "column3", "column4","column5","column6","column7","column8","column9","column10","column11","column12","column13","column14","column15","column16","column17" });
		return tokenizer;
	}

	@Bean
    public CompanyItemProcesser processor() {
        return new CompanyItemProcesser();
    }
	
}
