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

	// 検索処理
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView search(ModelAndView mv, @RequestParam("searchWord") String searchWord) {
		List<Items> itemList = itemsRepository.findAllByNameContaining(searchWord);
		mv.addObject("items", itemList);
		mv.setViewName("item");
		return mv;
	}

	// 管理者用検索
	@RequestMapping(value = "/searchAdmin", method = RequestMethod.POST)
	public ModelAndView searchAdmin(ModelAndView mv, @RequestParam("searchWord") String searchWord) {
		List<Items> itemList = itemsRepository.findAllByNameContaining(searchWord);
		mv.addObject("items", itemList);
		mv.setViewName("adminItem");
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

	// 出品処理
	@RequestMapping(value = "/addItem/do", method = RequestMethod.POST)
	public ModelAndView addItem(@RequestParam("name") String name,
			@RequestParam(name = "price", defaultValue = "0") int price, @RequestParam("image") String image,
			@RequestParam(name = "stock", defaultValue = "0") int stock, ModelAndView mv) {
		// 未入力チェック
		if (name == null || name.length() == 0 || image == null || image.length() == 0 || price <= 0 || stock <= 0) {
			mv.addObject("message", "入力値が正しくありません");

			// 出品画面を再表示
			mv.setViewName("addItem");
		} else {
			// 登録の処理
			Items item = new Items(price, stock, image, name);
			itemsRepository.saveAndFlush(item);

			// アイテム・カテゴリ一覧を取得して表示
			mv.addObject("items", itemsRepository.findAll());
			mv.setViewName("adminItem");
		}
		return mv;
	}

	// 商品削除
	@RequestMapping("/{id}/delete")
	public ModelAndView itemDelete(@PathVariable("id") int id, ModelAndView mv) {

		// データを削除する
		itemsRepository.deleteById(id);

		// 削除を確定する
		itemsRepository.flush();

		// アイテム・カテゴリ一覧を取得して表示
		mv.addObject("items", itemsRepository.findAll());
		mv.setViewName("adminItem");
		return mv;
	}
}
