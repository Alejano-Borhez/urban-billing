package com.urban.billingapi.service;

import static com.urban.utils.StreamUtils.collectToSet;
import static com.urban.utils.StreamUtils.map;
import static com.urban.utils.StreamUtils.size;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.urban.billingapi.dao.ITransportRepository;
import com.urban.billingapi.dao.IVendorRepository;
import com.urban.billingapi.model.enums.TransportType;
import com.urban.billingapi.model.vendor.Transport;
import com.urban.billingapi.model.vendor.Vendor;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class VendorService {
    private final IVendorRepository vendorRepository;
    private final ITransportRepository transportRepository;

    public Vendor createVendor(Vendor vendor) {
        Set<Transport> supportedTransports = vendor.getSupportedTransports();
        if (size(supportedTransports) > 0) {
            List<Transport> allByName = transportRepository.findAllByNameIn(map(supportedTransports, Transport::getName));
            vendor.setSupportedTransports(collectToSet(allByName));
        }

        return vendorRepository.save(vendor);
    }

    public Vendor addSupportedTransport(Long vendorId, Transport transport) {
        Optional<Vendor> vendor = vendorRepository.findById(vendorId);

        if (!vendor.isPresent()) {
            return null;
        }

        Vendor foundVendor = vendor.get();

        foundVendor.getSupportedTransports().add(transport);

        return vendorRepository.save(foundVendor);
    }

    public Page<Vendor> findByTransportTypes(Collection<TransportType> transportTypes, PageRequest pageRequest) {
        return vendorRepository.findDistinctBySupportedTransports_NameIn(transportTypes, pageRequest);
    }
}
