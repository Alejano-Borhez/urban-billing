package com.urban.billingapi.config;

import static com.urban.utils.StreamUtils.safeStream;
import static java.math.BigDecimal.valueOf;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.generate;

import java.math.RoundingMode;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Currency;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;
import com.urban.billingapi.dao.ICityRepository;
import com.urban.billingapi.dao.ITicketRepository;
import com.urban.billingapi.dao.ITransportRepository;
import com.urban.billingapi.dao.IUserRepository;
import com.urban.billingapi.model.enums.TicketType;
import com.urban.billingapi.model.enums.TransportType;
import com.urban.billingapi.model.user.User;
import com.urban.billingapi.model.vendor.City;
import com.urban.billingapi.model.vendor.Ticket;
import com.urban.billingapi.model.vendor.Transport;
import com.urban.billingapi.model.vendor.Vendor;
import com.urban.billingapi.service.VendorService;
import com.urban.utils.StreamUtils;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DatabasePopulator implements InitializingBean {
    private final Random random = new Random();
    private final ICityRepository cityRepository;
    private final ITransportRepository transportRepository;
    private final VendorService vendorService;
    private final ITicketRepository ticketRepository;
    private final IUserRepository userRepository;
    private final Faker faker;

    @Override
    public void afterPropertiesSet() throws Exception {
        List<Transport> transports = safeStream(TransportType.values())
                .map(name -> Transport.builder().name(name).build())
                .map(transportRepository::save)
                .collect(toList());

        List<City> cities = safeStream("Warsaw", "Lodz", "Krakow", "Gdansk", "Poznan")
                .map(name -> City.builder().name(name).build())
                .map(cityRepository::save)
                .collect(toList());

        List<Vendor> vendors = safeStream(cities)
                .map(city -> generate(() -> randomVendor(city)).limit(3L).collect(toList()))
                .flatMap(StreamUtils::safeStream)
                .collect(toList());

        List<Ticket> tickets = safeStream(vendors)
                .map(vendor -> generate(() -> randomTicket(vendor, transports)).limit(10L).collect(toList()))
                .flatMap(StreamUtils::safeStream)
                .collect(toList());

        List<User> users = generate(this::randomUser)
                .limit(100L)
                .map(userRepository::save)
                .collect(toList());
    }

    private Ticket randomTicket(Vendor vendor, List<Transport> transports) {
        Ticket ticket = ticketRepository.save(Ticket.builder()
                .duration(Duration.of((long) random.nextInt(101), ChronoUnit.SECONDS))
                .ticketType(pickRandom(TicketType.values()))
                .currency(Currency.getInstance("PLN"))
                .price(valueOf(random.nextInt(20)).divide(valueOf(10), 2, RoundingMode.UP))
                .build());
        ticket.setVendor(vendor);
        ticket.setTransports(prepareRandomSet(transports));

        return ticketRepository.save(ticket);
    }

    private <T> T pickRandom(T[] values) {
        int i = random.nextInt(values.length);
        return safeStream(values)
                .skip(i)
                .findAny()
                .orElse(null);
    }

    private Vendor randomVendor(City city) {
        return vendorService.createNew(Vendor.builder()
                .name(faker.company().name() + "_" + city.getName())
                .city(city)
                .currency(Currency.getInstance("PLN"))
                .build());
    }

    private User randomUser() {
        return User.builder()
                .userName(faker.name().username())
                .userToken(faker.idNumber().ssnValid())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.bothify("??????_##@gmail.com"))
                .build();
    }

    private <T> Set<T> prepareRandomSet(Collection<T> elements) {
        int i = random.nextInt(elements.size());
        return safeStream(elements)
                .skip((long) i)
                .collect(Collectors.toSet());
    }
}
