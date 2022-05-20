package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Entity.Items;
import com.example.demo.Repository.ItemsRepository;

@Controller
public class ItemController {

	@Autowired
	ItemsRepository itemsRepository;
	
	@RequestMapping("/searchItem")
	public ModelAndView searchItem(ModelAndView mv) {
		List<Items> itemList = itemsRepository.findAll();
		mv.addObject("items", itemList);
		return mv;
	}
	
}
