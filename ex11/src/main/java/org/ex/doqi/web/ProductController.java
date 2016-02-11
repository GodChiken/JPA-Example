package org.ex.doqi.web;

import org.ex.doqi.domain.Book;
import org.ex.doqi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ohjic-donghoon on 2016-02-11.
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String createForm() {
        return "product/createProductForm";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String create(Book book) {
        productService.saveProduct(book);
        return "redirect:/product";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model){
        model.addAttribute("productList", productService.findProducts());
        return "product/productList";
    }

    @RequestMapping(value = "/{productId}/edit", method = RequestMethod.GET)
    public String updateForm(@PathVariable("productId") long productId, Model model){
        model.addAttribute("product", productService.findOne(productId));
        return "product/updateForm";
    }

    @RequestMapping(value = "/{productId}/edit", method = RequestMethod.POST)
    public String updateProduct(@ModelAttribute("product") Book book){
        productService.saveProduct(book);
        return "redirect:/product";
    }
}
