package com.urban.billingapi.service.v1.mapper;

import com.urban.billingapi.dao.ICityRepository;
import com.urban.billingapi.dao.IVendorRepository;
import com.urban.billingapi.model.vendor.City;
import com.urban.billingapi.model.vendor.Vendor;
import com.urban.billingapi.vo.VendorVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Currency;

@SpringBootTest
@RunWith( SpringRunner.class )
public class VendorVOMapperTest
{

    @Autowired
    VendorVOMapper mapper;

    @Autowired
    ICityRepository cityRepository;

    @Autowired
    IVendorRepository vendorRepository;

    @Test
    public void mapping()
    {
        String cityName="Brest";
        City city = City.builder().name( cityName ).build();
        City savedCity = cityRepository.save( city );
        Vendor vendor = Vendor.builder().city( city ).currency( Currency.getInstance( "USD" ) ).name( "vendor1" ).build();
        Vendor savedVendor=vendorRepository.save( vendor );
        VendorVO mappedVendorVO = mapper.toVO( vendor );
        Vendor mappedVendor = mapper.toEntity( mappedVendorVO );
        System.out.println(mappedVendor);
        Currency.getAvailableCurrencies();
        System.out.println(mappedVendorVO);

    }

}