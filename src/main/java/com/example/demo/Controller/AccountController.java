package com.example.demo.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.Entity.Items;
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
		session.invalidate();
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

			// ログイン画面に遷移
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
			return mv;
		}

		List<Users> userList = usersRepository.findByEmailAndPassword(email, password);

		// 不一致時
		if (userList.size() == 0) {
			mv.addObject("message", "アカウントが存在しません");

			// 登録画面を再表示
			mv.setViewName("login");

			// 入力情報がデータベースと一致時
		} else {
			List<Items> itemList = itemsRepository.findAll();
			mv.addObject("items", itemList);

			Users user = userList.get(0);
			session.setAttribute("userInfo", user);

			// 検索画面に遷移
			mv.setViewName("item");
		}
		return mv;
	}

	// マイページ表示
	@RequestMapping("/mypage")
	public ModelAndView mypage(ModelAndView mv) {

		// セッションからユーザー情報の取得
		mv.addObject("userInfo", session.getAttribute("userInfo"));
		mv.setViewName("mypage");
		return mv;
	}

	// 登録情報変更ページを表示
	@RequestMapping("/editUser")
	public ModelAndView editUser(ModelAndView mv) {

		// セッションからユーザー情報の取得
		mv.addObject("userInfo", session.getAttribute("userInfo"));
		mv.setViewName("editUser");
		return mv;
	}

	// 登録情報変更
	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public ModelAndView itemAdd(@RequestParam("name") String name, @RequestParam("address") String address,
			@RequestParam("tell") String tell, @RequestParam("email") String email,
			@RequestParam("password") String password, ModelAndView mv) {

		// 未入力チェック
		if (name == null || name.length() == 0 || address == null || address.length() == 0 || tell == null
				|| tell.length() == 0 || email == null || email.length() == 0 || password == null
				|| password.length() == 0) {
			mv.addObject("message", "入力値が正しくありません");

			// 更新画面を再表示
			mv.addObject("userInfo", session.getAttribute("userInfo"));
			mv.setViewName("editUser");
		} else {

			// セッションからid取得
			Users userInfo = (Users) session.getAttribute("userInfo");
			Integer id = userInfo.getId();
			
			// 登録の処理
			Users users = new Users(id, address, email, tell, name, password);
			usersRepository.saveAndFlush(users);

			// セッション情報をリセットし、再取得する
			session.invalidate();
			List<Users> userList = usersRepository.findByEmailAndPassword(email, password);
			Users user = userList.get(0);
			session.setAttribute("userInfo", user);
			mv.addObject("userInfo", session.getAttribute("userInfo"));
			mv.setViewName("mypage");
		}
		return mv;
	}

	@RequestMapping("/logout")
	public String logout() {

		// ログアウト独特の処理も追記可能
		return login();
	}

}
