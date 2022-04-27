package com.tch.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tch.datasource.QueryDataSource;
import com.tch.dto.BankDTO;

@Service
public class QueryServiceImpl implements QueryService {

	@Autowired
	QueryDataSource datasource;

	@Override
	public List<BankDTO> queryByCriteria(BankDTO criteria) {
		if(StringUtils.isNotEmpty(criteria.getBankName())){
			System.out.println("++++ BANK NAME ++++");
			return datasource.queryByBankName(criteria.getBankName());
		}
		if(StringUtils.isNotEmpty(criteria.getCity()) && StringUtils.isEmpty(criteria.getState())){
			return datasource.queryByCity(criteria.getCity());
		}
		if(StringUtils.isNotEmpty(criteria.getState()) && StringUtils.isEmpty(criteria.getCity())){
			return datasource.queryByState(criteria.getState());
		}
		if(StringUtils.isNotEmpty(criteria.getType())){
			return datasource.queryByType(criteria.getType());
		}
		if(StringUtils.isNotEmpty(criteria.getZipcode())){
			return datasource.queryByZipcode(criteria.getZipcode());
		}
		if(StringUtils.isNotEmpty(criteria.getCity()) && StringUtils.isNotEmpty(criteria.getState())){
			return datasource.queryByCityAndState(criteria.getCity(), criteria.getState());
		}
		return null;
	}
	

}
