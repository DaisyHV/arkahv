package com.arka.arkahv.infraestructure.adapter.out.persistence.mapper;

import com.arka.arkahv.domain.model.Customer;
import com.arka.arkahv.infraestructure.adapter.out.persistence.entity.CustomerEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "orders", ignore = true)
    CustomerEntity customerToCustomerEntity(Customer customer);

    List<CustomerEntity> customersToCustomersEntity(List<Customer> customers);
    List<Customer> customersEntityToCustomers(List<CustomerEntity> customers);

    @InheritInverseConfiguration
    Customer customerEntityToCustomer(CustomerEntity customerEntity);
}
