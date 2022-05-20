package com.example.demo.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Entity.Cart;
import com.example.demo.Entity.Items;
import com.example.demo.Repository.ItemsRepository;

@Controller
public class CartController {

	@Autowired
	HttpSession session;

	@Autowired
	ItemsRepository itemsRepository;

	@RequestMapping("/cart")
	public ModelAndView cartList(ModelAndView mv) {
		Cart cart =getCartFromSession();
		
		// ページに表示したい情報を設定
				mv.addObject("items", cart.getItems());
				mv.addObject("total", cart.getTotal());

				// カートの中身を表示するページに遷移
				mv.setViewName("cart");
				return mv;
	}

	@RequestMapping("/cart/add/{id}")
	public ModelAndView addCart(@PathVariable("id") int id, ModelAndView mv) {
		// カート情報を取得
		Cart cart = getCart();

		// 商品コードを基にアイテム情報を取得
		Items items = itemsRepository.getById(id);

		// カート情報にアイテム情報を追加
		cart.addCart(items, 1);

		// ページに表示したい情報を設定
		mv.addObject("items", cart.getItems());
		mv.addObject("total", cart.getTotal());

		// カートの中身を表示するページに遷移
		mv.setViewName("cart");
		return mv;
	}
	
	@RequestMapping("/cart/delete/{id}")
	public ModelAndView deleteCart(@PathVariable("id") int id, ModelAndView mv) {

		// カート情報を取得
		Cart cart = getCart();

		// カートの中からcodeが一致する情報を削除
		cart.deleteCart(id);

		// ページに表示したい情報を設定
		mv.addObject("items", cart.getItems());
		mv.addObject("total", cart.getTotal());

		// カートの中身を表示するページに遷移
		mv.setViewName("cart");

		return mv;
	}
	
	public Cart getCart() {
		// セッションのカート情報を取得
		Cart cart = (Cart) session.getAttribute("cart");

		// カート情報がない場合、カート情報の初期処理
		if (cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		return cart;
	}
	
	private Cart getCartFromSession() {
		Cart cart = (Cart)session.getAttribute("cart");
		if(cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		return cart;
	}
}
