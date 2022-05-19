package com.example.demo.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Entity.Users;
import com.example.demo.Repository.ItemsRepository;
import com.example.demo.Repository.UsersRepository;

@Controller
public class AccountController {

	@Autowired
	HttpSession session;

	@Autowired
	ItemsRepository itemsRepository;

	@Autowired
	UsersRepository usersRepository;

	// ログイン画面遷移
	@RequestMapping("/")
	public String login() {
		return "login";
	}

	// 新規登録画面遷移
	@RequestMapping("/signup")
	public String signup() {
		return "signup";
	}

	// 登録ボタン押下時
	@RequestMapping(value = "/signup", method = RequestMethod.POST)

	public ModelAndView signup(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("address") String address, @RequestParam("tell") String tell,
			@RequestParam("password") String password, ModelAndView mv) {

		// 未入力チェック
		if (name == null || name.length() == 0 || email == null || email.length() == 0 || address == null
				|| address.length() == 0 || tell == null || tell.length() == 0 || password == null
				|| password.length() == 0) {
			mv.addObject("message", "入力値が正しくありません");

			// 登録画面を再表示
			mv.setViewName("signup");

		} else {
			Users user = new Users(address, email, tell, name, password);
			usersRepository.saveAndFlush(user);

			mv.setViewName("login");
		}
		return mv;
	}

	// ログインボタン押下時
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam("email") String email, @RequestParam("password") String password,
			ModelAndView mv) {

		// 未入力チェック
		if (email == null || email.length() == 0 || password == null || password.length() == 0) {
			mv.addObject("message", "入力値が正しくありません");

			// 登録画面を再表示
			mv.setViewName("login");
		} else {
			mv.setViewName("item");
		}
		return mv;
	}

}