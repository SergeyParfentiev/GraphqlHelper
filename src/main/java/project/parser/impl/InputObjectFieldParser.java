package project.parser.impl;

import graphql.schema.GraphQLInputObjectField;
import project.ParserContext;
import project.parser.inter.AbstractParser;
import static project.parser.inter.OutputParser.parse;

public class InputObjectFieldParser extends AbstractParser<GraphQLInputObjectField> {

    @Override
    public void parseOutput(GraphQLInputObjectField graphQLInputObjectField, ParserContext context) {
        appendTab(context);
//    context.getStructureBuilder().append(graphQLInputObjectField.getName()).append(": ");
//
//    // TODO: 10.07.2022 check
//    context.getStructureBuilder().append(graphQLInputObjectField.getType().toString());
////    parse(graphQLInputObjectField.getType(), structureBuilder);
//    System.out.println();

//    if (context.isArguments()) {
    context.getStructureBuilder().append(graphQLInputObjectField.getName()).append(":");
    parse(graphQLInputObjectField.getType(), context);

//      if (notNull) {
//        context.getStructureBuilder().append("!");
//      }
//    }

//    context.getStructureBuilder().append("\n");
    }
}
