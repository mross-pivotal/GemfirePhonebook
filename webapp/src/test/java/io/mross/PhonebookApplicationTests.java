package io.mross;


import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.client.ClientCache;
import com.gemstone.gemfire.cache.execute.Execution;
import com.gemstone.gemfire.cache.execute.FunctionService;
import com.gemstone.gemfire.cache.execute.ResultCollector;

import io.mross.phonebook.domain.User;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.UUID;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

@ImportResource("classpath:config/sdg-context.xml")
@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootConfiguration
public class PhonebookApplicationTests {


	@Autowired
	ClientCache cache;

//	@Before
//	public void setup() {
//		clearRegion();
//	}

	@Test
	public void testCacheListenerTransformation() {
		String randomId = UUID.randomUUID().toString();

		User user = new User(randomId,"Danny Brown","6163452212","Wisconson");
		System.out.println(user);
		Region <String,User> userRegion = cache.getRegion("Users");
		userRegion.put(user.getId(),user);
		User retrievedUser = userRegion.get(randomId);
		String phoneNumber = retrievedUser.getPhoneNumber();
		assertEquals("616-345-2212",phoneNumber);


	}

	@Test
	public void testSetDelimiterFunction() {
		String randomId = UUID.randomUUID().toString();


		Region<String,String> metadataRegion = cache.getRegion("Metadata");
		metadataRegion.put("delimiter","*");

		User user = new User(randomId,"Danny Brown","6163452212","Wisconson");
		System.out.println(user);
		Region <String,User> userRegion = cache.getRegion("Users");

		userRegion.put(user.getId(),user);

		Execution functionExecution = FunctionService
				.onRegion(cache.getRegion("Users"));

		ResultCollector<?, ?> results = functionExecution
				.execute("SetDelimiterFunction");
		Object resultWrapper = results.getResult();

		System.out.println(resultWrapper);


		User retrievedUser = userRegion.get(randomId);
		String phoneNumber = retrievedUser.getPhoneNumber();
		assertEquals("616*345*2212",phoneNumber);
		System.out.println(retrievedUser);



		metadataRegion.put("delimiter","/");

		results = functionExecution
				.execute("SetDelimiterFunction");
		resultWrapper = results.getResult();

		System.out.println(resultWrapper);


		retrievedUser = userRegion.get(randomId);
		phoneNumber = retrievedUser.getPhoneNumber();
		assertEquals("616/345/2212",phoneNumber);
		System.out.println(retrievedUser);




	}


//
//	@After
//	public void tearDown() {
//		clearRegion();
//	}


	private void clearRegion() {
		Execution functionExecution = FunctionService
				.onRegion(cache.getRegion("Users"));

		ResultCollector<?, ?> results = functionExecution
				.execute("ClearRegionRemoveAllFunction");
		Object resultWrapper = results.getResult();
		System.out.println(resultWrapper);
	}




	}