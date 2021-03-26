package net.javaguide.springboot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import net.javaguide.springboot.model.user;
import net.javaguide.springboot.repository.UserRepository;

@Controller
public class homeController {
	@Autowired
	private UserRepository userRepository;

	@GetMapping("")
	public String index() {
		return "index";
	}

	@GetMapping("/index.html")
	public String index2() {
		return "index";
	}

	@GetMapping("/register.html")
	public String register() {
		return "register";
	}

	@GetMapping("/login.html")
	public String login() {
		return "login";
	}

	@PostMapping("/signup")
	public String signup(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String confirmPassword = request.getParameter("confirmPassword");
		if (password.equals(confirmPassword)) {
			user user = new user(username, email, MD5(password));
			userRepository.save(user);
			return "index";
		} else {
			return "register";
		}

	}

	@PostMapping("/login")
	public String login(HttpServletRequest request) {
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		List<user> list = userRepository.findAll();
		for (user user : list) {
			if (user.getEmail().equals(email) && user.getPassword().equals(MD5(password))) {
				return "index";
			}
		}
		return "login";

	}

	public String MD5(String md5) {
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
		}
		return null;
	}
}
