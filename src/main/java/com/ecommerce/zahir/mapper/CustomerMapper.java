package com.ecommerce.zahir.mapper;

import com.ecommerce.zahir.dto.request.CheckoutCustomerRequest;
import com.ecommerce.zahir.dto.response.CustomerLookupResponse;
import com.ecommerce.zahir.dto.response.CustomerResponse;
import com.ecommerce.zahir.entities.Customer;

/**
 * Mapper class responsible for converting between Customer entity and DTOs.
 */
public class CustomerMapper {
    
    /**
     * Convert CheckoutCustomerRequest → Customer entity
     */
    public static Customer toEntity(CheckoutCustomerRequest request) {
        Customer customer = new Customer();

        customer.setFullName(request.getFullName());
        customer.setPrimaryPhone(request.getPrimaryPhone());
        customer.setSecondaryPhone(request.getSecondaryPhone());
        customer.setAddressLine(request.getAddressLine());
        customer.setGovernorate(request.getGovernorate());

        return customer;
    }

    /**
     * Convert Customer → CustomerResponse
     */
    public static CustomerResponse toResponse(Customer customer, long ordersCount) {
        CustomerResponse response = new CustomerResponse();

        response.setId(customer.getId());
        response.setFullName(customer.getFullName());
        response.setPrimaryPhone(customer.getPrimaryPhone());
        response.setSecondaryPhone(customer.getSecondaryPhone());
        response.setAddressLine(customer.getAddressLine());
        response.setGovernorate(customer.getGovernorate());
        response.setOrdersCount(ordersCount);
        response.setReturningCustomer(ordersCount > 1);
        response.setCreatedAt(customer.getCreatedAt());
        response.setUpdatedAt(customer.getUpdatedAt());

        return response;
    }

    /**
     * Convert Customer → CustomerLookupResponse
     */
    public static CustomerLookupResponse toLookupResponse(Customer customer) {
        CustomerLookupResponse response = new CustomerLookupResponse();

        response.setFound(true);
        response.setId(customer.getId());
        response.setFullName(customer.getFullName());
        response.setPrimaryPhone(customer.getPrimaryPhone());
        response.setSecondaryPhone(customer.getSecondaryPhone());
        response.setAddressLine(customer.getAddressLine());
        response.setGovernorate(customer.getGovernorate());

        return response;
    }

    /**
     * Build a "not found" lookup response for checkout flow.
     */
    public static CustomerLookupResponse toNotFoundLookupResponse() {
        CustomerLookupResponse response = new CustomerLookupResponse();
        response.setFound(false);
        return response;
    }
}
