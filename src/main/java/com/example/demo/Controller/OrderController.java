package com.example.demo.Controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Entity.Cart;
import com.example.demo.Entity.Items;
import com.example.demo.Entity.Order;
import com.example.demo.Entity.OrderDetail;
import com.example.demo.Entity.Users;
import com.example.demo.Repository.OrderDetailRepository;
import com.example.demo.Repository.OrderedRepository;
import com.example.demo.Repository.UsersRepository;

@Controller
public class OrderController {

	@Autowired
	HttpSession session;

	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	OrderedRepository orderedRepository;
	
	@Autowired
	OrderDetailRepository orderDetailRepository;

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
		
		// セッションからid取得
					Users userInfo = (Users) session.getAttribute("userInfo");
					Integer id = userInfo.getId();
		
		// オーダー情報の登録：Orderへの登録
				Order ordered = new Order(id, new Date(), cart.getTotal());
				int order_id = orderedRepository.saveAndFlush(ordered).getId();
				
				// オーダー詳細情報の登録：OrderDetailへの登録
				for (Items item : cart.getItems().values()) {
					OrderDetail orderDetail = new OrderDetail(order_id, item.getId(), item.getQuantity());
					orderDetailRepository.saveAndFlush(orderDetail);
				}

		// ページに表示したい情報を設定
		mv.addObject("items", cart.getItems());
		mv.addObject("total", cart.getTotal());
		mv.addObject("userInfo", session.getAttribute("userInfo"));

		// カートの中身を表示するページに遷移
		mv.setViewName("ordered");

		// カート内のセッション情報を削除
		session.removeAttribute("cart");
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
