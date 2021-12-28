package io.github.scorpionsik.learn.LearnSpringInAction.controllers;

import io.github.scorpionsik.learn.LearnSpringInAction.models.ShawarmaOrder;
import io.github.scorpionsik.learn.LearnSpringInAction.repositiries.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm(Model model){
        model.addAttribute("shawarmaOrder", new ShawarmaOrder());
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid ShawarmaOrder order, Errors errors){
        if(errors.hasErrors()) return "orderForm";

        //save
        order.setPlacedAt(new Date());
        orderRepository.save(order);
        log.info("Order submitted: " + order);
        return "redirect:/";
    }
}
