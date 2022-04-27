package com.tch.datasource;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tch.dto.BankDTO;
@Component
public interface QueryDataSource {
	public List<BankDTO> queryByBankName(String bankName);
	public List<BankDTO> queryByCity(String city);
	public List<BankDTO> queryByState(String state);
	public List<BankDTO> queryByType(String type);
	public List<BankDTO> queryByZipcode(String zipcode);
	public List<BankDTO> queryByCityAndState(String city, String state);

}
