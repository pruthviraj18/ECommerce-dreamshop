package com.application.Ecommerce.service.order;

import java.util.List;

import com.application.Ecommerce.dto.OrderDto;
import com.application.Ecommerce.model.Order;

public interface IOrderService {
    Order placeOrder(Long userId);
    OrderDto getOrder(Long orderId);
    List<OrderDto> getUserOrders(Long userId);
}
