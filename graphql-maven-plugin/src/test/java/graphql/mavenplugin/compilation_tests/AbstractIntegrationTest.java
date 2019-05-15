package graphql.mavenplugin.compilation_tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;

import graphql.mavenplugin.CodeGenerator;
import graphql.mavenplugin.DocumentParser;
import graphql.mavenplugin.PluginMode;
import graphql.mavenplugin.test.compiler.CompilationTestHelper;
import graphql.mavenplugin.test.helper.GraphqlTestHelper;
import graphql.mavenplugin.test.helper.MavenTestHelper;

@DirtiesContext // We need to forget the previous parsing (or everything may be doubled)
abstract class AbstractIntegrationTest {

	@Autowired
	protected ApplicationContext ctx;
	@Autowired
	protected CompilationTestHelper compilationTestHelper;
	@Autowired
	protected GraphqlTestHelper graphqlTestHelper;
	@Autowired
	protected MavenTestHelper mavenTestHelper;

	@Autowired
	protected Log log;
	@Autowired
	protected PluginMode mode;
	@Autowired
	protected String packageName;
	@Autowired
	protected String encoding;
	@Autowired
	protected File targetSourceFolder;
	@Autowired
	protected File targetClassFolder;

	@javax.annotation.Resource
	protected DocumentParser documentParser;
	@javax.annotation.Resource
	protected CodeGenerator codeGenerator;

	/**
	 * This test will be executed for each concrete subclass of this class
	 * 
	 * @throws MojoExecutionException
	 * @throws IOException
	 */
	@Test
	@DirtiesContext // We need to forget the previous parsing (or everything may be doubled)
	void testGenerateCode() throws MojoExecutionException, IOException {
		// Preparation
		// documentParser = new DocumentParser();
		// documentParser.packageName = packageName;
		// documentParser.log = new SystemStreamLog();
		// Resource resource = ctx.getResource(graphqlsResourceLocation);
		// documentParser.documents = new ArrayList<>();
		// documentParser.documents.add(new Parser().parseDocument(graphqlTestHelper.readSchema(resource)));
		int i = documentParser.parseDocuments();
		//
		// codeGenerator = new CodeGenerator();
		// codeGenerator.documentParser = documentParser;
		// codeGenerator.packageName = packageName;
		// codeGenerator.log = documentParser.log;
		// codeGenerator.encoding = encoding;
		// codeGenerator.targetSourceFolder = targetSourceFolder;

		// codeGenerator = spy(codeGenerator);
		mavenTestHelper.deleteDirectoryAndContentIfExists(targetSourceFolder);
		mavenTestHelper.deleteDirectoryAndContentIfExists(targetClassFolder);

		// Go, go, go
		int verif = codeGenerator.generateCode();

		// Basic verification of the number of generated files. The samples will work only if all needed files are
		// generated
		// (checking properly the number is not that simple, and changes to often to maintain it)
		assertTrue(verif > i, "More file should be generated than what's parsed");

		compilationTestHelper.checkCompleteCompilationStatus(null);
	}

}
