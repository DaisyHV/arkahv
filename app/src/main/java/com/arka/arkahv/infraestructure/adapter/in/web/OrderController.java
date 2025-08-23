package com.arka.arkahv.infraestructure.adapter.in.web;

import com.arka.arkahv.domain.model.DetailOrder;
import com.arka.arkahv.domain.model.Order;
import com.arka.arkahv.domain.model.Product;
import com.arka.arkahv.domain.port.in.CategoryUseCase;
import com.arka.arkahv.domain.port.in.OrderUseCase;
import com.arka.arkahv.domain.port.in.ProductUseCase;
import com.arka.arkahv.infraestructure.adapter.in.web.dto.DetailOrderDTO;
import com.arka.arkahv.infraestructure.adapter.in.web.dto.OrderDTO;
import com.arka.arkahv.infraestructure.adapter.in.web.dto.ProductDTO;
import com.arka.arkahv.infraestructure.adapter.in.web.mapper.CategoryWebMapper;
import com.arka.arkahv.infraestructure.adapter.in.web.mapper.OrderWebMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("arka/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderUseCase orderUseCase;
    private final OrderWebMapper orderWebMapper;
    private final ProductUseCase productUseCase;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllProducts(){
        List<Order> orders = orderUseCase.getAllOrders();
        List <OrderDTO> orderDTOS = orders.stream()
                .map(factura -> orderWebMapper.orderToOrderDTO(factura))
                .collect(Collectors.toList());
        return new ResponseEntity<>(orderDTOS, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody OrderDTO orderDTO){
        Order order = orderWebMapper.orderDTOToOrder(orderDTO);
        List<DetailOrder> detalles = new ArrayList<>();

        for(DetailOrderDTO doDTO : orderDTO.getDetails()){
            Product product = productUseCase.getProductById(doDTO.getProduct_id());
            DetailOrder detailOrder = new DetailOrder();
            detailOrder.setIdOrder(order.getId());
            detailOrder.setIdProduct(product.getId());
            detailOrder.setNumber(doDTO.getNumberProducts());

            detalles.add(detailOrder);
        }

        order.setDetails_order(detalles);
        orderUseCase.createOrder(order);
        return new ResponseEntity<>(orderWebMapper.orderToOrderDTO(order), HttpStatus.OK);




    }

}
