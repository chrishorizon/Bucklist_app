package com.horizon.accomplishment.controllers;

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
import com.horizon.accomplishment.models.User;
import com.horizon.accomplishment.services.BucketService;
import com.horizon.accomplishment.services.UserService;

@Controller
public class BucketController {
	
	@Autowired
	UserService userServ;
	
	@Autowired
	BucketService bucketServ;
	
	// ==== Display =================
	
	// Create Course Page
	@GetMapping("/bucketlist/new")
	public String newCourse(@ModelAttribute("bucket") Bucket bucket, Model model, HttpSession session) {
		Long id = (Long) session.getAttribute("user_id");
		if(id == null) {
			return "redirect:/";
		}
		User creator = userServ.getOne(id);
		model.addAttribute("user", creator);
		return "add_bucket.jsp";
	}

	// ==== Action ============
	
	// Create
	@PostMapping("/bucketlist/new")
	public String createCourse(@Valid @ModelAttribute("course") Bucket bucket, BindingResult result, HttpSession session) {
		Long id = (Long) session.getAttribute("user_id");
		if(id == null) {
			return "redirect:/";
		}
		if(result.hasErrors()) {
			return "add_bucket.jsp";
		} else {
			User user = userServ.getOne((Long) session.getAttribute("user_id"));
			bucket.setCreator(user);
			
			bucketServ.createBucket(bucket);
			return "redirect:/home";
		}
	}

}
