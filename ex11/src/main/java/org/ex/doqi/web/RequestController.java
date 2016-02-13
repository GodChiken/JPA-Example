package org.ex.doqi.web;

import org.ex.doqi.domain.Book;
import org.ex.doqi.domain.Member;
import org.ex.doqi.domain.Product;
import org.ex.doqi.domain.RequestSearch;
import org.ex.doqi.service.MemberService;
import org.ex.doqi.service.ProductService;
import org.ex.doqi.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.awt.ModalityListener;

import java.util.List;

/**
 * Created by ohjic-donghoon on 2016-02-11.
 */
@Controller
@RequestMapping("/request")
public class RequestController {
    @Autowired
    private RequestService requestService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private ProductService productService;

    @RequestMapping(method = RequestMethod.GET)
    public String createForm(Model model){
        List<Member> members = memberService.findMembers();
        List<Product> products = productService.findProducts();

        model.addAttribute("memberList", members);
        model.addAttribute("productList", products);

        return "request/requestForm";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String request(@RequestParam("memberId") long memberId, @RequestParam("productId") long productId, @RequestParam("count") int count) {
        requestService.order(memberId, productId, count);
        return "redirect:/request";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@ModelAttribute("requestSearch") RequestSearch requestSearch, Model model){
        model.addAttribute("requestList", requestService.findRequests(requestSearch));
        System.out.println("123");
        return "request/list";
    }
}
