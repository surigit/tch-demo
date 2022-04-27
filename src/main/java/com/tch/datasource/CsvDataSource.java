package com.tch.datasource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.tch.dto.BankDTO;

@Service
public class CsvDataSource implements QueryDataSource, InitializingBean{
	private static Logger logger = LoggerFactory.getLogger(CsvDataSource.class);	
	private List<BankDTO> bankList = null; 
	
	@Override
	public void afterPropertiesSet() throws Exception {
		InputStream ins = Thread.currentThread().getContextClassLoader().getResourceAsStream("/data.csv");
		logger.info("Loading dataset from file [{}]","");
		try (Reader in = new BufferedReader(new InputStreamReader(ins))){
			Iterable<CSVRecord> records = CSVFormat.DEFAULT
			  .parse(in);
			bankList = new ArrayList<>();
			for (CSVRecord record : records) {
				BankDTO dto = new BankDTO();
				dto.setBankName(record.get(1));
				dto.setType(record.get(2));
				dto.setCity(record.get(3));
				dto.setState(record.get(4));
				dto.setZipcode(record.get(5));
				bankList.add(dto);
			}
			logger.info("Data set size [{}]", bankList.size());
		} catch (FileNotFoundException e) {
			logger.error("File Not Found {}","");
			throw e;
		} catch (IOException e) {
			throw e;
		}
	}


	@Override
	public List<BankDTO> queryByBankName(String bankName) {
		Predicate<BankDTO> predicate = x -> x.getBankName().equals(bankName);
		logger.info("Searching for Bank Name [{}]",bankName);
		return bankList.stream().filter(predicate).collect(Collectors.toList());
	}


	@Override
	public List<BankDTO> queryByCity(String city) {
		Predicate<BankDTO> predicate = x -> x.getCity().equals(city);
		logger.info("Searching for City [{}]",city);
		return bankList.stream().filter(predicate).collect(Collectors.toList());
	}


	@Override
	public List<BankDTO> queryByState(String state) {
		Predicate<BankDTO> predicate = x -> x.getState().equals(state);
		logger.info("Searching for State [{}]",state);
		return bankList.stream().filter(predicate).collect(Collectors.toList());
	}


	@Override
	public List<BankDTO> queryByType(String type) {
		Predicate<BankDTO> predicate = x -> x.getType().equals(type);
		logger.info("Searching for Type [{}]",type);
		return bankList.stream().filter(predicate).collect(Collectors.toList());
	}


	@Override
	public List<BankDTO> queryByZipcode(String zipcode) {
		Predicate<BankDTO> predicate = x -> x.getZipcode().equals(zipcode);
		logger.info("Searching for zipcode` [{}]",zipcode);
		return bankList.stream().filter(predicate).collect(Collectors.toList());
	}


	@Override
	public List<BankDTO> queryByCityAndState(String city, String state) {
		Predicate<BankDTO> pcity = x -> x.getCity().equals(city);
		Predicate<BankDTO> pstate = x -> x.getState().equals(state);
		logger.info("Searching for city [{}] and state [{}]",city, state);
		return bankList.stream().filter(pcity.and(pstate)).collect(Collectors.toList());
	}

}
