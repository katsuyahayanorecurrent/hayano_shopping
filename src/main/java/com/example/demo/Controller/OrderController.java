package com.example.demo.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Entity.Cart;
import com.example.demo.Repository.UsersRepository;

@Controller
public class OrderController {

	@Autowired
	HttpSession session;

	@Autowired
	UsersRepository usersRepository;

	@RequestMapping("/order")
	public ModelAndView order(ModelAndView mv) {
		Cart cart = getCartFromSession();

		// ページに表示したい情報を設定
		mv.addObject("items", cart.getItems());
		mv.addObject("total", cart.getTotal());
		mv.addObject("userInfo", session.getAttribute("userInfo"));

		// カートの中身を表示するページに遷移
		mv.setViewName("order");
		return mv;
	}

	@RequestMapping("/ordered")
	public ModelAndView ordered(ModelAndView mv) {
		Cart cart = getCartFromSession();
		
		// ページに表示したい情報を設定
		mv.addObject("items", cart.getItems());
		mv.addObject("total", cart.getTotal());
		mv.addObject("userInfo", session.getAttribute("userInfo"));
		
		// カートの中身を表示するページに遷移
		mv.setViewName("ordered");
		return mv;
	}
	
	private Cart getCartFromSession() {
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		return cart;
	}


}
