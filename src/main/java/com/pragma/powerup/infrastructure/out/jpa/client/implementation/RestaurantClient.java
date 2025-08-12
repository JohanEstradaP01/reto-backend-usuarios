package com.pragma.powerup.infrastructure.out.jpa.client.implementation;

import com.pragma.powerup.domain.spi.IRestaurantConsultPort;
import com.pragma.powerup.infrastructure.out.jpa.client.FeignClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantClient implements IRestaurantConsultPort {

    private final FeignClient feignClient;

    @Override
    public Long consultRestaurantOwner(Long restaurantId) {
        System.out.println(restaurantId);
        return feignClient.getRestaurantOwner(restaurantId);
    }

}
