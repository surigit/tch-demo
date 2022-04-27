package com.tch.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.tch.dto.BankDTO;
@Component
public interface QueryService {
	public List<BankDTO> queryByCriteria(BankDTO criteria);
}
