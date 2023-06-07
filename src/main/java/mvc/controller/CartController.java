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

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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



    @RequestMapping(value = "/list")
    public String listCart(Model model , HttpServletRequest request , HttpSession session){
        List<CartDto> listCart = (List<CartDto>) request.getSession().getAttribute("listCart");
        if(listCart ==null){
            listCart = new ArrayList<>();
        }
        model.addAttribute("listCart",listCart);
        return "cart";
    }

    @RequestMapping(value = "/add/{id}")
    public String addCart(@PathVariable("id") int id, HttpServletRequest request, HttpSession session, Model model){
        //getlist order tu session
        List<CartDto> listCart = (List<CartDto>) request.getSession().getAttribute("listCart");
        if(listCart == null){
            listCart = new ArrayList<>();
            request.getSession().getAttribute("listCart");
        }
        //ADD DOI TUONJG VAO LIST
        Optional<ProductEntity> product = productRepository.findById(id);
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductId(product.get().getProductId());
        productEntity.setProductName(product.get().getProductName());
        productEntity.setProductDescription(product.get().getProductDescription());
        productEntity.setUnitPrice(product.get().getUnitPrice());

       CartDto cartDto = new CartDto();
       cartDto.setProduct(productEntity);

        CartDto itemCart = listCart.stream()
                .filter(p -> id == (p.getProduct().getProductId()))
                .findAny().orElse(null);
        if (itemCart != null) {
            listCart.get(listCart.indexOf(itemCart)).setTotalProduct(itemCart.getTotalProduct() + 1);
        } else {
            CartDto newItemCart = new CartDto();
            newItemCart.setProduct(productEntity);
            newItemCart.setTotalProduct(1);
            listCart.add(newItemCart);
        }
        request.getSession().setAttribute("listCart",listCart);
        model.addAttribute("listCart",listCart);
        return "cart";
        //return "redirect:/cart/list";
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
    public String removeCart(HttpServletRequest request ,Model model, @PathVariable int id ){
        List<CartDto> listCart = (List<CartDto>) request.getSession().getAttribute("listCart");
        if (listCart == null) {
            listCart = new ArrayList<>();
            request.getSession().setAttribute("listCart", listCart);
        }
        CartDto itemCart = listCart.stream()
                .filter(p -> id == (p.getProduct().getProductId()))
                .findAny().orElse(null);
        listCart.remove(itemCart);
        request.getSession().setAttribute("listCart", listCart);
        model.addAttribute("listCart", listCart);
        //return "redirect:/cart/list";
        return "cart";
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
