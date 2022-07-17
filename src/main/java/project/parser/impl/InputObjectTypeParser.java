package project.parser.impl;

import static project.parser.inter.OutputParser.parse;

import graphql.schema.GraphQLInputObjectType;
import project.ParserContext;
import project.parser.inter.AbstractParser;

public class InputObjectTypeParser extends AbstractParser<GraphQLInputObjectType> {

  @Override
  public void parseOutput(GraphQLInputObjectType graphQLInputObjectType,
      ParserContext context) {
   context.getStructureBuilder().append(" {\n");

    tabIndex++;
//    appendTab(structureBuilder);

    graphQLInputObjectType.getFieldDefinitions()
        .forEach(fieldDefinition -> parse(fieldDefinition, context));

    tabIndex--;
    appendTab(context);

    context.getStructureBuilder().append("}\n");
  }
}
