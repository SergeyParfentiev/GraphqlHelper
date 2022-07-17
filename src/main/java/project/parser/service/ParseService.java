package project.parser.service;

import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLSchema;
import java.util.List;
import project.ParserContext;
import project.gui.Window;
import project.parser.inter.OutputParser;
import project.web.RequestDefinition;

public class ParseService {
    
    private static final String QUERY = "query";
    private static final String MUTATION = "mutation";

    public String getAllParsed(GraphQLSchema schema) {
        StringBuilder allStructureBuilder = new StringBuilder();

        allStructureBuilder.append(getQueryParsed(schema.getQueryType().getFieldDefinitions()));
        allStructureBuilder.append(getMutationParsed(schema.getMutationType().getFieldDefinitions()));

        return allStructureBuilder.toString();
    }

    public String getAllParsed(List<RequestDefinition> definitions) {
        StringBuilder structure = new StringBuilder();

        definitions.forEach(definition -> {
            if (definition.isQuery()) {
                structure.append(getQueryParsed(definition.getFieldDefinition()));
            } else {
                structure.append(getMutationParsed(definition.getFieldDefinition()));
            }
        });

        return structure.toString();
    }

    public String getQueryParsed(List<GraphQLFieldDefinition> fieldDefinitions) {
        return getRequestParsed(fieldDefinitions, QUERY);
    }

    public String getMutationParsed(List<GraphQLFieldDefinition> fieldDefinitions) {
        return getRequestParsed(fieldDefinitions, MUTATION);
    }

    public String getQueryParsed(GraphQLFieldDefinition fieldDefinition) {
        return getRequestParsed(fieldDefinition, QUERY);
    }

    public String getMutationParsed(GraphQLFieldDefinition fieldDefinition) {
        return getRequestParsed(fieldDefinition, MUTATION);
    }

    public String getRequestParsed(List<GraphQLFieldDefinition> fieldDefinitions, String requestType) {
        StringBuilder allStructureBuilder = new StringBuilder();

        fieldDefinitions.forEach(fieldDefinition -> {
            getRequestParsed(fieldDefinition, requestType);
        });
        return allStructureBuilder.toString();
    }

    public String getRequestParsed(GraphQLFieldDefinition fieldDefinition, String requestType) {
        StringBuilder queryBuilder = new StringBuilder(requestType + " {\n");
        queryBuilder.append("\t").append(fieldDefinition.getName());

        if (!fieldDefinition.getArguments().isEmpty()) {
            queryBuilder.append("(\n");

            ParserContext parserContext = new ParserContext(true, queryBuilder);
            fieldDefinition.getArguments().forEach(argument -> {
                OutputParser.parse(argument, parserContext);
            });

            queryBuilder.append("\t)");
        }

        StringBuilder outputStructureBuilder = new StringBuilder();

        if (fieldDefinition.getType() != null) {
            OutputParser.parse(fieldDefinition.getType(),
                    new ParserContext(false, outputStructureBuilder));

            queryBuilder.append(outputStructureBuilder);
        }

        queryBuilder.append("}");

        return queryBuilder.append("\n\n").toString();
    }
}
