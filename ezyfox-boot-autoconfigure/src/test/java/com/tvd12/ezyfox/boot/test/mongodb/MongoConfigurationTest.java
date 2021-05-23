package com.tvd12.ezyfox.boot.test.mongodb;

import com.tvd12.ezydata.mongodb.repository.EzyMongoMaxIdRepository;
import com.tvd12.ezyfox.bean.EzySingletonFactory;
import com.tvd12.ezyfox.boot.mongodb.MongoConfiguration;
import com.tvd12.test.util.RandomUtil;
import org.testng.annotations.Test;

import java.util.Properties;
import java.util.Set;

import static org.mockito.Mockito.*;

public class MongoConfigurationTest {
	
	@Test
	public void autoConfigSuccess() {
		// given
		EzySingletonFactory singletonFactory = mock(EzySingletonFactory.class);
		
		Set<String> packagesToScan = RandomUtil.randomSet(8, String.class);
		packagesToScan.add("com.tvd12.ezyfox");
		Properties properties = new Properties();
		
		MongoConfiguration sut = new MongoConfigForTest();
		sut.setPackagesToScan(packagesToScan);
		sut.setProperties(properties);
		sut.setSingletonFactory(singletonFactory);
		sut.setDatabaseName("chattutorial");
		
		// when
		sut.autoConfig();
		
		// then
		verify(singletonFactory, times(1)).addSingleton(eq("ezyMaxIdRepository"), any(EzyMongoMaxIdRepository.class));
		verify(singletonFactory, times(1)).addSingleton(eq("aRepo"), any(ARepo.class));
	}
	
}
