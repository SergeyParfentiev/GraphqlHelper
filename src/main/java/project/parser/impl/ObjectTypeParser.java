package project.parser.impl;

import graphql.schema.GraphQLObjectType;
import project.ParserContext;
import project.parser.inter.AbstractParser;
import project.parser.inter.OutputParser;

public class ObjectTypeParser extends AbstractParser<GraphQLObjectType> {

  @Override
  public void parseOutput(GraphQLObjectType graphQLObjectType, ParserContext context) {
    context.getStructureBuilder().append(" {\n");

    tabIndex++;
//    appendTab(structureBuilder);

    graphQLObjectType.getFieldDefinitions()
        .forEach(fieldDefinition -> OutputParser.parse(fieldDefinition, context));

    tabIndex--;
    appendTab(context);

    context.getStructureBuilder().append("}\n");
  }

  private void partiallyParse(GraphQLObjectType type, ParserContext context) {
    type.getFieldDefinitions().forEach(fieldDefinition -> OutputParser.parse(fieldDefinition, context));
  }
}
