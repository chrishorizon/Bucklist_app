package com.horizon.accomplishment.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.horizon.accomplishment.models.Bucket;
import com.horizon.accomplishment.models.LoginUser;
import com.horizon.accomplishment.models.User;
import com.horizon.accomplishment.services.BucketService;
import com.horizon.accomplishment.services.UserService;

@Controller
public class HomeController {
	
	private final UserService userServ;
	public HomeController(UserService u) {
		this.userServ = u;
	}
	
	@Autowired
	private BucketService bucketServ;
	
	
	// ==== Display =================
	
	// Display login or Register route
	@GetMapping("/")
	public String index(Model model, HttpSession session) {
		if (session.getAttribute("user_id") != null) {
			return "redirect:/home";
		}
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
	}
	
	// Home page / Show all courses
	@GetMapping("/home")
	public String home(Model model, HttpSession session) {
		Long id = (Long) session.getAttribute("user_id");
		if (session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		User user = userServ.getOne(id);
		model.addAttribute("loggedInUser", user);
		
		List<Bucket> allBucket= bucketServ.allBuckets();
		model.addAttribute("allBuckets", allBucket);
		
		return "dashboard.jsp";
	}

	// ==== Action ============
	
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, 
			BindingResult result, Model model, HttpSession session) {
		userServ.register(newUser, result);
		if(result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			return "index.jsp";
		}
		session.setAttribute("user_id", newUser.getId());
		return "redirect:/home";
	}

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        User user = userServ.login(newLogin, result);
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
        session.setAttribute("user_id", user.getId());
        return "redirect:/home";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/";
    }

}
