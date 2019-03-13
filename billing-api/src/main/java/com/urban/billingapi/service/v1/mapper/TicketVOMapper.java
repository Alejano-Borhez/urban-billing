package com.urban.billingapi.service.v1.mapper;

import com.google.common.collect.Sets;
import com.urban.billingapi.dao.ITransportRepository;
import com.urban.billingapi.dao.IVendorRepository;
import com.urban.billingapi.model.exceptions.UrbanServiceException;
import com.urban.billingapi.model.vendor.Ticket;
import com.urban.billingapi.model.vendor.Transport;
import com.urban.billingapi.model.vendor.Vendor;
import com.urban.billingapi.vo.TicketVO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TicketVOMapper implements EntityToVOMapper<Ticket, TicketVO>

{
    private final ModelMapper modelMapper = new ModelMapper();
    private final ITransportRepository transportRepository;
    private final IVendorRepository vendorRepository;

    public TicketVOMapper( ITransportRepository transportRepository, IVendorRepository vendorRepository )
    {
        this.transportRepository = transportRepository;
        this.vendorRepository = vendorRepository;

        modelMapper.addMappings( new PropertyMap<TicketVO, Ticket>()
        {
            @Override
            protected void configure()
            {
                skip().setVendor( null );
                skip().setTransports( null );
            }
        } );

        modelMapper.addMappings( new PropertyMap<Ticket, TicketVO>()
        {
            @Override
            protected void configure()
            {
                skip().setTransportIds( null );
            }
        } );
    }

    @Override
    public TicketVO toVO( Ticket entity )
    {
        TicketVO ticketVO = modelMapper.map( entity, TicketVO.class );

        ticketVO.setVendorId( entity.getVendor().getId() );
        Set<Transport> transports = entity.getTransports();
        if( transports != null && !transports.isEmpty() )
        {
            Set<Long> ids = transports.stream().map( Transport::getId ).collect( Collectors.toSet() );
            ticketVO.setTransportIds( ids );
        }

        return ticketVO;
    }

    @Override
    public Ticket toEntity( TicketVO vo )
    {
        Ticket ticket = modelMapper.map( vo, Ticket.class );

        Vendor vendor = vendorRepository.findById( vo.getVendorId() ).orElseThrow( () -> new UrbanServiceException( "Not found the vendor by id" ) );
        ticket.setVendor( vendor );

        Set<Long> transportIds = vo.getTransportIds();
        if( transportIds != null && !transportIds.isEmpty() )
        {
            HashSet<Transport> transports = Sets.newHashSet( transportRepository.findAllById( transportIds ) );
            ticket.setTransports( transports );
        }

        return ticket;
    }
}
