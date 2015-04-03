package com.example.ui.tag;

import org.thymeleaf.Configuration;
import org.thymeleaf.context.IProcessingContext;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionEnhancingDialect;
import org.thymeleaf.processor.IProcessor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by naga on 15/04/02.
 */
public class MyDialect extends AbstractDialect
        implements IExpressionEnhancingDialect {

    private final Configuration configuration;

    @Override
    public Map<String, Object> getAdditionalExpressionObjects(
            IProcessingContext processingContext) {

        Map<String, Object> map = new HashMap<>();
        map.put("myFields",
                new MyFields(configuration, processingContext));
        return map;
    }

    public MyDialect(Configuration configuration) {
        super();
        this.configuration = configuration;
    }

    public String getPrefix() {
        return "my";
    }

    @Override
    public Set<IProcessor> getProcessors() {
        final Set<IProcessor> processors = new HashSet<>();
        processors.add(new IdNameProcessor());
        return processors;
    }
}
