package com.pragma.powerup.infrastructure.out.jpa.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@org.springframework.cloud.openfeign.FeignClient(name = "restaurantClient", url = "http://localhost:8080/api/v1/restaurant")
public interface FeignClient {

    @GetMapping("/owner/{restaurantId}")
    public Long getRestaurantOwner(@PathVariable Long restaurantId);

}
