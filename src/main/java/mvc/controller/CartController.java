package mvc.controller;


import mvc.dto.CartDto;
import mvc.entity.OrderDetailsEntity;
import mvc.entity.OrderEntity;
import mvc.entity.ProductEntity;
import mvc.repository.OrderDetailsRepository;
import mvc.repository.OrderRepository;
import mvc.repository.ProductRepository;
import mvc.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/cart")
public class CartController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    CartService cartService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAllProduct(Model model){
        List<ProductEntity> list = (List<ProductEntity>) productRepository.findAll();
        model.addAttribute("product",list);
        return  "productList";
    }

    @RequestMapping(value = "/list")
    public String listCart(Model model ){
        //CartDto cart = (CartDto) session.getAttribute("cart");
        List<CartDto> list = cartService.getAllCart();
        model.addAttribute("cart",list);
        return "cart";
    }

    @RequestMapping(value = "/add/{id}")
    public String addCart(@PathVariable("id") int id){
        ProductEntity product = productRepository.findById(id).get();
        if (product!=null){
            CartDto cart = new CartDto();
            cart.setProduct(product);
            cart.setTotalProduct(1);
            cartService.addCart(cart);
        }

        return "redirect:/cart/list";
    }

    @RequestMapping(value = "/remove/{id}")
    public String removeCart(@PathVariable("id") int id){
        cartService.removeCart(id);
        return "redirect:/cart/list";
    }


    @RequestMapping(value = "/check",method = RequestMethod.GET)
    public String checkOrder(Model model){
        model.addAttribute("order",new OrderEntity());
        model.addAttribute("action","checkout");
        return "checkout";
    }



    @RequestMapping(value = "/checkout",method = RequestMethod.POST,produces =  "text/plain;charset=UTF-8")
    public String saveCheckOut(OrderEntity order,  HttpSession session){

            order.setOrderDate(new Date());
            orderRepository.save(order);
            session.setAttribute("order",order);
            for (CartDto c :cartService.getAllCart()
            ) {
                OrderDetailsEntity orderDetails = new OrderDetailsEntity();
                orderDetails.setOrdersEntity(order);
                orderDetails.setProductEntity(c.getProduct());
                orderDetails.setQuantity(c.getTotalProduct());

                orderDetailsRepository.save(orderDetails);
            }
            return "redirect:/cart/myOrder";

    }
    @RequestMapping(value = "/myOrder")
    public String myOrder(Model model){
        List<OrderEntity> orderList = (List<OrderEntity>) orderRepository.findAll();
        model.addAttribute("orderList",orderList);
        return "myOrder";
    }
    @RequestMapping(value = "viewDetail/{order.orderId}")
    public String viewDetail(@PathVariable("order.orderId") int orderId, Model model){
        List<OrderDetailsEntity> orderDetailsList =orderDetailsRepository.findCartDetailsByOrderId(orderId);
        List<CartDto> cartList = new ArrayList<>();
        for (OrderDetailsEntity orderDetails:orderDetailsList) {
            CartDto item= new CartDto();
            item.setProduct(orderDetails.getProductEntity());
            item.setTotalProduct(orderDetails.getQuantity());
            cartList.add(item);
        }
        model.addAttribute("cartList",cartList);
        return "viewDetails";
    }


}
