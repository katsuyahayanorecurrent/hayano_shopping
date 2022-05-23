package com.example.demo.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Entity.Items;
import com.example.demo.Repository.ItemsRepository;

@Controller
public class ItemController {

	@Autowired
	ItemsRepository itemsRepository;

	@Autowired
	HttpSession session;

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(ModelAndView mv, @RequestParam("searchWord") String searchWord) {
		List<Items> itemList = itemsRepository.findAllByNameContaining(searchWord);
		mv.addObject("items", itemList);
		mv.setViewName("item");
		return mv;
	}

	// 検索画面に遷移
	@RequestMapping(value = "/items")
	public ModelAndView search(ModelAndView mv) {

		List<Items> itemList = itemsRepository.findAll();
		mv.addObject("items", itemList);

		mv.setViewName("item");
		return mv;
	}

	// 商品詳細表示
	@RequestMapping("/itemDetail/{id}")
	public ModelAndView itemDetail(@PathVariable(name = "id") int id, ModelAndView mv) {
		mv.addObject("items", itemsRepository.findById(id).get());
		mv.setViewName("itemDetail");
		return mv;
	}

}
