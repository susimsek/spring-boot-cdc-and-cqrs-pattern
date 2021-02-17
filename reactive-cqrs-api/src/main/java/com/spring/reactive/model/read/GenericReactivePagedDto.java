package com.spring.reactive.model.read;

import org.springframework.data.elasticsearch.core.SearchHit;

import java.util.List;
import java.util.Map;

public class GenericReactivePagedDto<T> extends SearchHit<T> {

    public GenericReactivePagedDto(String index, String id, float score, Object[] sortValues, Map<String, List<String>> highlightFields, T content) {
        super(index, id, score, sortValues, highlightFields, content);
    }
}


