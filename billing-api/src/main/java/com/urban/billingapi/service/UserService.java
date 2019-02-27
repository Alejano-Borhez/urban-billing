package com.urban.billingapi.service;

import static java.util.Optional.ofNullable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.CustomerSearchRequest;
import com.braintreegateway.ResourceCollection;
import com.urban.billingapi.dao.IUserRepository;
import com.urban.billingapi.model.user.User;

@Service
public class UserService {
    @Autowired
    private BraintreeGateway gateway;
    @Autowired
    private IUserRepository userRepository;

    @Transactional
    public User createClient(User user) {

        User savedUser = userRepository.findOne(Example.of(user))
                .orElseGet(() -> userRepository.save(user));

        CustomerRequest request = new CustomerRequest()
                .email(savedUser.getEmail())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName());

        Customer customer;
        String userToken = savedUser.getUserToken();
        if (StringUtils.isEmpty(userToken)) {
            ResourceCollection<Customer> search = gateway.customer().search(new CustomerSearchRequest()
                    .firstName().contains(savedUser.getFirstName())
                    .lastName().contains(savedUser.getLastName())
                    .email().contains(savedUser.getEmail()));
            customer = ofNullable(search)
                    .filter(results -> results.iterator().hasNext())
                    .map(ResourceCollection::getFirst)
                    .orElseGet(() -> gateway.customer().create(request).getTarget());
        } else {
            customer = gateway.customer().find(userToken);
        }
        savedUser.setUserToken(customer.getId());
        userRepository.save(savedUser);

        return User.builder()
                .email(customer.getEmail())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .userToken(customer.getId())
                .build();
    }
}
