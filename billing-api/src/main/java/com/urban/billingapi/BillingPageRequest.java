package com.urban.billingapi;

import static com.urban.utils.StreamUtils.map;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import lombok.Data;

@Data
public class BillingPageRequest<T> {
    int page;
    int size;
    List<ResultOrder> orders;
    T entityExample;

    public PageRequest pageRequest() {
        Sort sort = Sort.by(map(orders, order -> new Sort.Order(order.direction, order.property)));
        return PageRequest.of(page, size, sort);
    }

    public static class ResultOrder {
        public Sort.Direction direction;
        public String property;
    }
}
