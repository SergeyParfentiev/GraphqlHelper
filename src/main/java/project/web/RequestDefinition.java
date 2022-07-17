/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.web;

import graphql.schema.GraphQLFieldDefinition;
import lombok.Getter;

/**
 *
 * @author sparf
 */
@Getter
public class RequestDefinition {

    private final boolean query;
    private final GraphQLFieldDefinition fieldDefinition;

    public RequestDefinition(boolean query, GraphQLFieldDefinition fieldDefinition) {
        this.query = query;
        this.fieldDefinition = fieldDefinition;
    }

    @Override
    public String toString() {
        return fieldDefinition.getName();
    }
}
