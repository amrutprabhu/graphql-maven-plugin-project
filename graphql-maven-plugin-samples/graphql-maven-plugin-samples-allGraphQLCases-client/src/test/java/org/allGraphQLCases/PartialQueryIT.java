/**
 * 
 */
package org.allGraphQLCases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.allGraphQLCases.client.AllFieldCases;
import org.allGraphQLCases.client.util.AnotherMutationTypeExecutor;
import org.allGraphQLCases.client.util.GraphQLRequest;
import org.allGraphQLCases.client.util.MyQueryTypeExecutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import com.graphql_java_generator.exception.GraphQLRequestExecutionException;
import com.graphql_java_generator.exception.GraphQLRequestPreparationException;

/**
 * @author etienne-sf
 */
@Execution(ExecutionMode.CONCURRENT)
public class PartialQueryIT {
	MyQueryTypeExecutor queryType;
	AnotherMutationTypeExecutor mutation;

	@BeforeEach
	void setUp() throws Exception {
		// For some tests, we need to execute additional partialQueries
		queryType = new MyQueryTypeExecutor("http://localhost:8180/graphql");
		mutation = new AnotherMutationTypeExecutor("http://localhost:8180/graphql");
	}

	/**
	 * Test of list that contain list, when sending request and receiving response
	 * 
	 * @throws GraphQLRequestPreparationException
	 * @throws GraphQLRequestExecutionException
	 */
	@Test
	@Execution(ExecutionMode.CONCURRENT)
	void test_ListOfList() throws GraphQLRequestPreparationException, GraphQLRequestExecutionException {
		// Preparation
		GraphQLRequest graphQLRequest = queryType.getWithListOfListGraphQLRequest("{matrix}");
		//
		List<List<Double>> matrixSrc = new ArrayList<>();
		for (int i = 0; i <= 2; i += 1) {
			List<Double> sublist = new ArrayList<>();
			for (int j = 0; j <= 3; j += 1) {
				sublist.add((double) (i + j));
			}
			matrixSrc.add(sublist);
		} // for

		// Go, go, go
		AllFieldCases allFieldCases = queryType.withListOfList(graphQLRequest, matrixSrc);

		// Verification
		assertNotNull(allFieldCases);
		List<List<Double>> matrixVerif = allFieldCases.getMatrix();
		assertNotNull(matrixVerif);
		assertEquals(3, matrixVerif.size());
		for (int i = 0; i <= 2; i += 1) {
			List<Double> sublist = matrixVerif.get(i);
			assertEquals(4, sublist.size());
			for (int j = 0; j <= 3; j += 1) {
				assertEquals(i + j, sublist.get(j));
			}
		} // for
	}

	@Test
	@Execution(ExecutionMode.CONCURRENT)
	void test_Issue51_ListID() throws GraphQLRequestPreparationException, GraphQLRequestExecutionException {
		// Preparation
		List<String> ids = new ArrayList<String>();
		ids.add("11111111-1111-1111-1111-111111111111");
		ids.add("22222222-2222-2222-2222-222222222222");
		ids.add("33333333-3333-3333-3333-333333333333");
		//
		GraphQLRequest graphQLRequest = mutation.getDeleteSnacksGraphQLRequest("");

		// Go, go, go
		Boolean ret = mutation.deleteSnacks(graphQLRequest, ids);

		// Verification
		assertTrue(ret);
	}
}
