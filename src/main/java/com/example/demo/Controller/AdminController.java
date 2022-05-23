package com.example.demo.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Entity.Administrator;
import com.example.demo.Entity.Items;
import com.example.demo.Repository.AdministratorRepository;
import com.example.demo.Repository.ItemsRepository;

@Controller
public class AdminController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	ItemsRepository itemsRepository;
	
	@Autowired
	AdministratorRepository administratorRepository;
	
	// ログイン画面遷移
	@RequestMapping("/adminLogin")
	public String adminLogin() {
		session.invalidate();
		return "adminLogin";
	}
	
	// ログインボタン押下時
		@RequestMapping(value = "/adminLogin", method = RequestMethod.POST)
		public ModelAndView login(@RequestParam("email") String email, @RequestParam("password") String password,
				ModelAndView mv) {

			// 未入力チェック
			if (email == null || email.length() == 0 || password == null || password.length() == 0) {
				mv.addObject("message", "入力値が正しくありません");

				// 登録画面を再表示
				mv.setViewName("adminLogin");
				return mv;
			}

			List<Administrator> adminList = administratorRepository.findByEmailAndPassword(email, password);

			// 不一致時
			if (adminList.size() == 0) {
				mv.addObject("message", "アカウントが存在しません");

				// 登録画面を再表示
				mv.setViewName("adminMypage");

				// 入力情報がデータベースと一致時
			} else {
				List<Items> itemList = itemsRepository.findAll();
				mv.addObject("items", itemList);

				Administrator admin = adminList.get(0);
				session.setAttribute("adminInfo", admin);

				// 検索画面に遷移
				mv.setViewName("adminMypage");
			}
			return mv;
		}

}
