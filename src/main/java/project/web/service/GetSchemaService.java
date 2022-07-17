package project.web.service;

import static project.web.GraphqlConstants.SCHEMA_STRUCTURE_QUERY;

import graphql.Scalars;
import graphql.introspection.IntrospectionResultToSchema;
import graphql.language.Document;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.SchemaPrinter;
import graphql.schema.idl.TypeDefinitionRegistry;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

public class GetSchemaService {

    private static final String SCALARS_FILE_NAME = "scalars.txt";

    public GraphQLSchema getSchema(String url, String authorization) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();

        // Create post request with header authorization
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-type", "application/json");
        httpPost.addHeader(HttpHeaders.AUTHORIZATION, "Basic " + authorization);

        // Set post body schema returned structure 
        StringEntity stringEntity = new StringEntity(SCHEMA_STRUCTURE_QUERY);
        httpPost.getRequestLine();
        httpPost.setEntity(stringEntity);

        // Get response schema structure
        HttpResponse response = httpClient.execute(httpPost);
        String actualResponse = IOUtils.toString(response.getEntity().getContent(),
                StandardCharsets.UTF_8.name());

        // Create document from response data
        JSONObject jsonObject = new JSONObject(actualResponse);
        Document document = new IntrospectionResultToSchema().createSchemaDefinition(
                jsonObject.getJSONObject("data").toMap());

        // Create schema reader
        String printedSchema = new SchemaPrinter().print(document);
        Reader schemaProvider = new StringReader(printedSchema);

        // Parse schema from reader
        SchemaParser parser = new SchemaParser();
        TypeDefinitionRegistry typeRegistry = parser.parse(schemaProvider);

        // Create scalars builder
        RuntimeWiring.Builder builder = RuntimeWiring.newRuntimeWiring();
        populateScalars(builder);

        // Create schema with scalars
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, builder
                .build()
        );
    }

    private void populateScalars(RuntimeWiring.Builder builder) {
        getScalars().forEach(scalar -> builder.scalar(ExtendedScalars.newAliasedScalar(scalar).aliasedScalar(
                Scalars.GraphQLString).build()));
    }

    private List<String> getScalars() {
        File myObj = new File(SCALARS_FILE_NAME);

        try ( Scanner myReader = new Scanner(myObj)) {
            List<String> scalars = new ArrayList<>();

            while (myReader.hasNextLine()) {
                String trim = myReader.nextLine().replaceAll("\\s+", "");
                scalars.addAll(List.of(trim.split(",")));

            }
            scalars.forEach(scalar -> System.out.println(scalar));
            return scalars;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File " + SCALARS_FILE_NAME + "not found", e);
        }
    }

}
