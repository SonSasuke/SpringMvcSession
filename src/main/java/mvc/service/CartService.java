package mvc.service;


import mvc.dto.CartDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartService {

    Map<Integer,CartDto> mapCart = new HashMap<>();
//    public void addCart(CartDto cartDto){
//        CartDto cart = mapCart.get(cartDto.getProduct().getProductId());
//        if (cart==null){
//            mapCart.put(cartDto.getProduct().getProductId(),cartDto);
////            cart.setTotalPrice(cart.getProduct().getUnitPrice());
//        } else {
//            cart.setTotalProduct(cart.getTotalProduct()+1);
//            cart.setTotalPrice(cart.getProduct().getUnitPrice()*cart.getTotalProduct());
//        }
//
//    }
    public void removeCart(int id){
        mapCart.remove(id);
    }

    public List<CartDto> getAllCart(){
        return new ArrayList<>(mapCart.values());
    }
}
