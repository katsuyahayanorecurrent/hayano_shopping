package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Classies.Cart;
import com.example.demo.Classies.OrderDetailHistory;
import com.example.demo.Classies.OrderHistory;
import com.example.demo.Entity.Items;
import com.example.demo.Entity.Order;
import com.example.demo.Entity.OrderDetail;
import com.example.demo.Entity.Users;
import com.example.demo.Repository.ItemsRepository;
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
	ItemsRepository itemsRepository;

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

	// 注文履歴
	@RequestMapping("/history")
	public ModelAndView history(ModelAndView mv) {
		
		// セッションからユーザー情報を取得
		Users userInfo = (Users) session.getAttribute("userInfo");
		Integer id = userInfo.getId();
		
		// Useridが一致するorder情報一覧を取得
		List<Order> orders = orderedRepository.findAllByUserId(id);
		
		// orderidのリストを生成
		Collection<Integer> orderIds = new ArrayList<>();
		for(Order order : orders) {
			orderIds.add(order.getId());
		}
		
		// orderedIdIn でorderDetail情報から、詳細情報を取得
		List<OrderDetail> orderdetails = orderDetailRepository.findByOrderedIdIn(orderIds);
		
		// itemidのリストを生成
		Collection<Integer> itemIds = new ArrayList<>();
		for(OrderDetail orderDetail : orderdetails) {
			itemIds.add(orderDetail.getItemId());
		}
		
		// codeIn でitem一覧を取得
		List<Items> items = itemsRepository.findByIdIn(itemIds);
		
		// 表示用のクラスOrderHistoryを生成して、それに当てはめる
		
		List<OrderHistory> orderHistories = new ArrayList<>();
		
		for( Order order : orders) {
			OrderHistory orderHistory = new OrderHistory();
			// Orderをセット
			orderHistory.setOrder(order);
			
			List<OrderDetailHistory> orderDetailHistories = new ArrayList<>();
			for(OrderDetail orderDetail : orderdetails) {
				if(order.getId() == orderDetail.getOrderedId()){
					OrderDetailHistory orderDetailHistory = new OrderDetailHistory();
					
					orderDetailHistory.setOrderDetail(orderDetail);
					
					for(Items item : items) {
						if(orderDetail.getItemId() == item.getId()){
							orderDetailHistory.setItem(item);
							break;
						}
					}
					orderDetailHistories.add(orderDetailHistory);		
				}
			}		
			orderHistory.setOrderDetails(orderDetailHistories);
			
			orderHistories.add(orderHistory);
		}
		mv.addObject("orderHistories", orderHistories);
				
		mv.setViewName("history");
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
