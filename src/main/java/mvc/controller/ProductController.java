package mvc.controller;


import mvc.entity.ProductEntity;
import mvc.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(method = RequestMethod.GET)
   public String getAllProduct(Model model){
     List<ProductEntity> list = (List<ProductEntity>) productRepository.findAll();
     model.addAttribute("product",list);
     return  "productList";
   }
}
