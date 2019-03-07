package com.urban.billingapi.config;

import static com.urban.utils.StreamUtils.safeStream;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.generate;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
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
import com.urban.billingapi.dao.IVendorRepository;
import com.urban.billingapi.model.enums.TicketType;
import com.urban.billingapi.model.enums.TransportType;
import com.urban.billingapi.model.user.User;
import com.urban.billingapi.model.vendor.City;
import com.urban.billingapi.model.vendor.Ticket;
import com.urban.billingapi.model.vendor.Transport;
import com.urban.billingapi.model.vendor.Vendor;
import com.urban.utils.StreamUtils;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DatabasePopulator implements InitializingBean {
    private final Random random = new Random();
    private final ICityRepository cityRepository;
    private final ITransportRepository transportRepository;
    private final IVendorRepository vendorRepository;
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
                .map(city -> generate(() -> randomVendor(transports, city)).limit(3L).collect(toList()))
                .flatMap(StreamUtils::safeStream)
                .collect(toList());

        List<Ticket> tickets = safeStream(vendors)
                .map(vendor -> generate(() -> randomTicket(vendor)).limit(10L).collect(toList()))
                .flatMap(StreamUtils::safeStream)
                .collect(toList());

        List<User> users = generate(this::randomUser)
                .limit(100L)
                .map(userRepository::save)
                .collect(toList());
    }

    private Ticket randomTicket(Vendor vendor) {
        Ticket ticket = ticketRepository.save(Ticket.builder()
                .duration(Duration.of((long) random.nextInt(101), ChronoUnit.SECONDS))
                .ticketType(pickRandom(TicketType.values()))
                .build());
        ticket.setVendor(vendor);
        ticket.setTransports(prepareRandomSet(vendor.getSupportedTransports()));

        return ticketRepository.save(ticket);
    }

    private <T> T pickRandom(T[] values) {
        int i = random.nextInt(values.length);
        return safeStream(values)
                .skip(i)
                .findAny()
                .orElse(null);
    }

    private Vendor randomVendor(List<Transport> transports, City city) {
        Vendor vendor = vendorRepository.save(Vendor.builder()
                .name(faker.company().name() + "_" + city.getName())
                .build());
        vendor.setCity(city);
        vendor.setSupportedTransports(prepareRandomSet(transports));
        vendor = vendorRepository.save(vendor);
        return vendor;
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
