package com.tch.application;

import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.tch.dto.BankDTO;
import com.tch.service.QueryService;

/**
 * 
 * @author Surendra, Myneni
 *
 */
@SpringBootApplication
@ComponentScan({"com.tch"})
public class TchRunnerMain implements CommandLineRunner{

	private static Logger logger = LoggerFactory.getLogger(TchRunnerMain.class);	
	private static CommandLineParser parser = new DefaultParser();
	private static Options options = null;
	private static CommandLine line = null;
	private static HelpFormatter formatter = new HelpFormatter();
	private static String csvFile = "";
	private static String LOG_DIR = "LOG_FILE";
	
	public static void main(String[] args) {
		SpringApplication.run(TchRunnerMain.class, args);
	}

	@Autowired
	QueryService queryService;
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	private static Options buildOptionsByType() {
		Options options;
		Option zipcode;
		Option state;
		Option city;
		Option type;
		Option bank;
		options = new Options();
		zipcode = Option.builder("zipcode").required(false).desc("75039").hasArg(true).build();
		state = Option.builder("state").required(false).desc("NC").hasArg(true).build();
		city = Option.builder("city").required(false).desc("New York").hasArg(true).build();
		type = Option.builder("type").required(false).desc("ATM or Branch").hasArg(true).build();
		bank = Option.builder("bank").required(false).desc("Amazing Bank").hasArg(true).build();
		options.addOption(zipcode);
		options.addOption(state);
		options.addOption(city);
		options.addOption(type);
		options.addOption(bank);
		return options;
	}

	
	private static void printHelp() {
		formatter.printHelp("following options required", buildOptionsByType());
	}

	@Override
	public void run(String... args) throws Exception {
		options = buildOptionsByType();
		boolean hasMinSearchData = false;
		String zipcode ="";
		String city ="";
		String state ="";
		String type ="";
		String bank ="";
		if (args == null || args.length == 0) {
			printHelp();
			System.exit(-1);
		}

		try {
			line = parser.parse(options, args);
		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage());
			printHelp();
			System.exit(-1);
		}
		BankDTO searchDTO = new BankDTO();
		if (line.hasOption("zipcode")) {
			zipcode = line.getOptionValue("zipcode");
			searchDTO.setZipcode(zipcode);
			hasMinSearchData = true;
		}
		if (line.hasOption("city")) {
			city = line.getOptionValue("city");
			searchDTO.setCity(city);
			hasMinSearchData = true;
		}
		if (line.hasOption("state")) {
			state = line.getOptionValue("state");
			searchDTO.setState(state);
			hasMinSearchData = true;
		}
		if (line.hasOption("type")) {
			type = line.getOptionValue("type");
			searchDTO.setType(type);
			hasMinSearchData = true;
		}
		if (line.hasOption("bank")) {
			bank = line.getOptionValue("bank");
			searchDTO.setBankName(bank);
			hasMinSearchData = true;
		}

		if(!hasMinSearchData){
			logger.error("Atleast one search option is required from the following [zipcode, city, state, type or bank]");
			printHelp();
			System.exit(-1);
		}
		
		logger.info("Search Criteria {}",searchDTO);
		List<BankDTO> results = queryService.queryByCriteria(searchDTO);
		if(null == results || results.isEmpty()){
			logger.info("No Results Found");
			System.exit(0);
		}
		System.out.println("============================================");
		System.out.println("  R E S U L T S ");
		System.out.println("");
		results.forEach(System.out::println);
		System.out.println("");
		System.out.println("============================================");
	}

}
