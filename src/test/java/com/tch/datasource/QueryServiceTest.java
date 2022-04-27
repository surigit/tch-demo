package com.tch.datasource;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.tch.dto.BankDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=QueryServiceTest.class)
public class QueryServiceTest {
	
	@MockBean
	QueryDataSource csvDatasource;

	@Before
	public void setUp() {
	}
	
	@Test
	public void testCsvDatasource(){
		BankDTO nameDTO = new BankDTO();
		nameDTO.setBankName("Amazing Bank");
		nameDTO.setType("Branch");
		nameDTO.setCity("New York");
		nameDTO.setState("NY");
		nameDTO.setZipcode("10018");
		List<BankDTO> bankNameResults = new ArrayList<>();
		bankNameResults.add(nameDTO);
		Mockito.when(csvDatasource.queryByBankName("Amazing Bank"))
	      .thenReturn(bankNameResults);

		List<BankDTO> testResults = csvDatasource.queryByBankName("Amazing Bank");
		Assert.assertNotNull("Result cannot be NULL", testResults);
		Assert.assertTrue("Results size did not match", testResults.size() ==1 );
	}
}
