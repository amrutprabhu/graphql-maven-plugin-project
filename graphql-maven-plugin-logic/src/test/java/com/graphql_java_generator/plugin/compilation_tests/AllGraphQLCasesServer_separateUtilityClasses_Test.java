package com.graphql_java_generator.plugin.compilation_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;

import graphql.mavenplugin_notscannedbyspring.AllGraphQLCases_Server_SpringConfiguration_separateUtilityClasses;

class AllGraphQLCasesServer_separateUtilityClasses_Test extends AbstractIntegrationTest {

	public AllGraphQLCasesServer_separateUtilityClasses_Test() {
		super(AllGraphQLCases_Server_SpringConfiguration_separateUtilityClasses.class);
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
