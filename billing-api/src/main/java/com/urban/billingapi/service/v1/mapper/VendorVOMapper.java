package com.urban.billingapi.service.v1.mapper;

import com.urban.billingapi.dao.ICityRepository;
import com.urban.billingapi.model.exceptions.UrbanServiceException;
import com.urban.billingapi.model.vendor.City;
import com.urban.billingapi.model.vendor.Vendor;
import com.urban.billingapi.vo.VendorVO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class VendorVOMapper implements EntityToVOMapper<Vendor, VendorVO>
{

    private final ICityRepository cityRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public VendorVOMapper( ICityRepository cityRepository )
    {
        this.cityRepository = cityRepository;
        modelMapper.addMappings( new PropertyMap<VendorVO, Vendor>()
        {
            @Override
            protected void configure()
            {
                skip().setCity( null );
            }
        } );

        modelMapper.addMappings( new PropertyMap<Vendor, VendorVO>()
        {
            @Override
            protected void configure()
            {
                skip().setCityName( null );
            }
        } );
    }

    @Override
    public VendorVO toVO( Vendor entity )
    {
        VendorVO vendorVO = modelMapper.map( entity, VendorVO.class );
        vendorVO.setCityName( entity.getCity().getName() );

        return vendorVO;
    }

    @Override
    public Vendor toEntity( VendorVO vo )
    {
        Vendor vendor = modelMapper.map( vo, Vendor.class );
        City city = cityRepository.findByName( vo.getCityName() ).orElseThrow( () -> new UrbanServiceException( "Not found this city name: " ) );
        vendor.setCity( city );

        return vendor;
    }
}
