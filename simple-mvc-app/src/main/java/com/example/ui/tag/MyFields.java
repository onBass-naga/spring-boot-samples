package com.example.ui.tag;

import org.thymeleaf.Configuration;
import org.thymeleaf.context.IProcessingContext;
import org.thymeleaf.spring4.util.FieldUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by naga on 15/04/02.
 */
public class MyFields {

    private final Configuration configuration;
    private final IProcessingContext processingContext;

    public boolean hasErrors(final String... fields) {

        return Stream.of(fields)
                .anyMatch(field ->
                        FieldUtils.hasErrors(
                                configuration, processingContext, field));
    }

    public List<String> errors(final String... fields) {

        return Stream.of(fields)
                .flatMap(field ->
                        FieldUtils.errors(
                                configuration, processingContext, field)
                                .stream())
                .collect(Collectors.toList());
    }

    public MyFields(final Configuration configuration,
                    final IProcessingContext processingContext) {
        super();
        this.configuration = configuration;
        this.processingContext = processingContext;
    }
}
