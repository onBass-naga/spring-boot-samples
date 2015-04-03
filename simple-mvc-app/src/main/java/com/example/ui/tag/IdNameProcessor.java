package com.example.ui.tag;

import org.thymeleaf.Arguments;
import org.thymeleaf.Configuration;
import org.thymeleaf.dom.Element;
import org.thymeleaf.processor.attr.AbstractAttributeModifierAttrProcessor;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;

import java.util.*;

/**
 * Created by naga on 15/04/02.
 */
public class IdNameProcessor extends AbstractAttributeModifierAttrProcessor {

    public static final int ATTR_PRECEDENCE = 10000;
    public static final String ATTR_NAME = "idname";

    public IdNameProcessor() {
        super(ATTR_NAME);
    }

    @Override
    public int getPrecedence() {
        return ATTR_PRECEDENCE;
    }

    @Override
    protected Map<String, String> getModifiedAttributeValues(
            Arguments arguments, Element element, String attributeName) {
        final String attributeValue = element.getAttributeValue(attributeName);

        final Configuration configuration = arguments.getConfiguration();
        final IStandardExpressionParser expressionParser =
                StandardExpressions.getExpressionParser(configuration);

        final IStandardExpression expression =
                expressionParser.parseExpression(configuration, arguments, attributeValue);

        final Set<String> newAttributeNames =
                new HashSet<String>(Arrays.asList("id", "name"));

        final Object valueForAttributes = expression.execute(configuration, arguments);

        final Map<String,String> result =
                new HashMap<String,String>(newAttributeNames.size() + 1, 1.0f);
        for (final String newAttributeName : newAttributeNames) {
            result.put(newAttributeName,
                    (valueForAttributes == null? "" : valueForAttributes.toString()));
        }

        return result;
    }

    @Override
    protected ModificationType getModificationType(
            final Arguments arguments, final Element element,
            final String attributeName, final String newAttributeName) {
        return ModificationType.SUBSTITUTION;
    }

    @Override
    protected boolean removeAttributeIfEmpty(
            final Arguments arguments, final Element element,
            final String attributeName, final String newAttributeName) {
        return false;
    }

    @Override
    protected boolean recomputeProcessorsAfterExecution(
            Arguments arguments, Element element, String attributeName) {
        return false;
    }
}
