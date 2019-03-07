package com.urban.billingapi;

import static com.urban.utils.StreamUtils.map;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.urban.billingapi.model.EntityExample;

import lombok.Data;

@Data
public class BillingPageRequest<T extends EntityExample<T>> {
    int page;
    int size;
    List<ResultOrder> orders;
    T entityExample;

    public PageRequest getPageRequest() {
        Sort sort = Sort.by(map(orders, order -> new Sort.Order(order.direction, order.property)));
        return PageRequest.of(page, size, sort);
    }

    public Example<T> getExample() {
        return entityExample.createExample(entityExample);
    }

    public static class ResultOrder {
        public Sort.Direction direction;
        public String property;
    }
}
