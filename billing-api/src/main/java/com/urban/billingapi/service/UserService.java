package com.urban.billingapi.service;

import static java.util.Optional.ofNullable;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Customer;
import com.braintreegateway.CustomerRequest;
import com.braintreegateway.CustomerSearchRequest;
import com.braintreegateway.ResourceCollection;
import com.urban.billingapi.dao.IUserRepository;
import com.urban.billingapi.model.user.User;
import com.urban.billingapi.service.mapper.UserVOMapper;
import com.urban.billingapi.vo.UserVO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService extends CrudService<Long, User, UserVO> {
    private final BraintreeGateway gateway;
    @Getter
    private final IUserRepository repository;
    @Getter
    private final UserVOMapper mapper;

    public UserVO createClient(User user) {
        validate(user, true);
        User savedUser = getRepository().findOne(Example.of(user))
                .orElseGet(() -> getRepository().save(user));

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
        getRepository().save(savedUser);

        return UserVO.builder()
                .email(customer.getEmail())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .userToken(customer.getId())
                .build();
    }
}
