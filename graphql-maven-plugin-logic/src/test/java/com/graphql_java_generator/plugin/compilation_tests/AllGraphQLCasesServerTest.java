package com.graphql_java_generator.plugin.compilation_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;

import graphql.mavenplugin_notscannedbyspring.AllGraphQLCases_Server_SpringConfiguration;

class AllGraphQLCasesServerTest extends AbstractIntegrationTest {

	public AllGraphQLCasesServerTest() {
		super(AllGraphQLCases_Server_SpringConfiguration.class);
	}

	@BeforeEach
	public void setUp() {
		graphqlTestHelper.checkSchemaStringProvider("allGraphQLCases*.graphqls");
	}

	@Override
	protected void checkNbGeneratedClasses(int nbGeneratedClasses) {
		assertEquals(52, nbGeneratedClasses);
	}
}
