package com.urban.billingapi.rest;

import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urban.billingapi.dao.IUserRepository;
import com.urban.billingapi.model.user.User;
import com.urban.billingapi.service.UserService;

import io.swagger.annotations.Api;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@RestController
@RequestMapping("/api/user")
@Api
public class ClientRESTService implements BillingRESTService<User, Long> {
    private final UserService userService;
    private final RepositoryEntityLinks repositoryEntityLinks;
    private final IUserRepository repository;

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @PostMapping("")
    public Resource<User> createNewClient(@RequestBody User user) {
        return new Resource<>(userService.createClient(user), repositoryEntityLinks.linksToSearchResources(User.class));
    }

}
