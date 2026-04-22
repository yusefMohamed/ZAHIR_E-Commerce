package com.ecommerce.zahir.mapper;

import com.ecommerce.zahir.dto.response.OrderConfirmationItemResponse;
import com.ecommerce.zahir.dto.response.OrderConfirmationWhatsappResponse;
import com.ecommerce.zahir.dto.response.OrderItemResponse;
import com.ecommerce.zahir.dto.response.OrderResponse;
import com.ecommerce.zahir.dto.response.OrderSummaryResponse;
import com.ecommerce.zahir.entities.Order;
import com.ecommerce.zahir.entities.OrderItem;

/**
 * Mapper class responsible for converting Order and OrderItem entities into response DTOs.
 */
public class OrderMapper {

    /**
     * Convert OrderItem → OrderItemResponse
     */
    public static OrderItemResponse toItemResponse(OrderItem item) {
        OrderItemResponse response = new OrderItemResponse();

        response.setId(item.getId());
        response.setPerfumeId(item.getPerfume().getId());
        response.setPerfumeName(item.getPerfumeNameSnapshot());
        response.setUnitPrice(item.getUnitPrice());
        response.setQuantity(item.getQuantity());
        response.setLineTotal(item.getLineTotal());

        return response;
    }

    /**
     * Convert Order → OrderResponse (full details)
     */
    public static OrderResponse toResponse(Order order) {
        OrderResponse response = new OrderResponse();

        response.setId(order.getId());
        response.setStatus(order.getOrderStatus());
        response.setPaymentMethod(order.getPaymentMethod());

        response.setCustomerId(order.getCustomer().getId());
        response.setCustomerFullName(order.getCustomerName());
        response.setPrimaryPhone(order.getPrimaryPhone());
        response.setAddressLine(order.getAddressLine());
        response.setGovernorate(order.getGovernorate());

        response.setSubtotal(order.getSubtotal());
        response.setDiscountAmount(order.getDiscountAmount());
        response.setTotalAmount(order.getTotalAmount());

        response.setVoucherCode(order.getVoucher() != null ? order.getVoucher().getCode() : null);

        response.setItems(
                order.getOrderItems()
                        .stream()
                        .map(OrderMapper::toItemResponse)
                        .toList()
        );

        response.setCreatedAt(order.getCreatedAt());
        response.setUpdatedAt(order.getUpdatedAt());

        return response;
    }

    /**
     * Convert Order → OrderSummaryResponse (lightweight)
     */
    public static OrderSummaryResponse toSummaryResponse(Order order) {
        OrderSummaryResponse response = new OrderSummaryResponse();

        response.setId(order.getId());
        response.setCustomerFullName(order.getCustomerName());
        response.setPrimaryPhone(order.getPrimaryPhone());
        response.setTotalAmount(order.getTotalAmount());
        response.setStatus(order.getOrderStatus());
        response.setCreatedAt(order.getCreatedAt());

        return response;
    }

    /**
     * Convert OrderItem → OrderConfirmationItemResponse
     */
    public static OrderConfirmationItemResponse toConfirmationItemResponse(OrderItem item) {
        OrderConfirmationItemResponse response = new OrderConfirmationItemResponse();

        response.setPerfumeName(item.getPerfumeNameSnapshot());
        response.setQuantity(item.getQuantity());
        response.setUnitPrice(item.getUnitPrice());
        response.setLineTotal(item.getLineTotal());

        return response;
    }

    /**
     * Convert Order → OrderConfirmationWhatsappResponse
     */
    public static OrderConfirmationWhatsappResponse toWhatsappResponse(Order order, String estimatedDeliveryMessage) {
        OrderConfirmationWhatsappResponse response = new OrderConfirmationWhatsappResponse();

        response.setCustomerFullName(order.getCustomerName());
        response.setPrimaryPhone(order.getPrimaryPhone());
        response.setAddressLine(order.getAddressLine());
        response.setGovernorate(order.getGovernorate());
        response.setOrderNumber(order.getOrderNumber());

        response.setItems(
                order.getOrderItems()
                        .stream()
                        .map(OrderMapper::toConfirmationItemResponse)
                        .toList()
        );

        response.setTotalAmount(order.getTotalAmount());
        response.setEstimatedDeliveryMessage(estimatedDeliveryMessage);

        return response;
    }
    
}
