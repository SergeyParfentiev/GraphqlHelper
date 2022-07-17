package project.parser.impl;

import static project.parser.inter.OutputParser.parse;

import graphql.schema.GraphQLList;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLScalarType;
import project.ParserContext;
import project.parser.inter.AbstractParser;

public class ListParser extends AbstractParser<GraphQLList> {

    @Override
    public void parseOutput(GraphQLList graphQLList, ParserContext context) {
        if (graphQLList.getOriginalWrappedType() instanceof GraphQLScalarType || (graphQLList.getOriginalWrappedType() instanceof GraphQLNonNull
                && ((GraphQLNonNull) graphQLList.getOriginalWrappedType()).getOriginalWrappedType() instanceof GraphQLScalarType)) {
            if (context.isArguments()) {
                context.getStructureBuilder().append(" [");

                parse(graphQLList.getOriginalWrappedType(), context);

                context.getStructureBuilder().setLength(context.getStructureBuilder().length() - 1);
                context.getStructureBuilder().append(" ]\n");
            } else {
                context.getStructureBuilder().append("\n");
            }
        } else {
            if (context.isArguments()) {
                context.getStructureBuilder().append(" [\n");
                tabIndex++;
                appendTab(context);

                parse(graphQLList.getOriginalWrappedType(), context);

                tabIndex--;
                appendTab(context);
                context.getStructureBuilder().append("]\n");
            } else {
                parse(graphQLList.getOriginalWrappedType(), context);

            }
        }
    }
}
