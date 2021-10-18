package com.horizon.accomplishment.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String newBucket(@ModelAttribute("bucket") Bucket bucket, Model model, HttpSession session) {
		Long id = (Long) session.getAttribute("user_id");
		if(id == null) {
			return "redirect:/";
		}
		User creator = userServ.getOne(id);
		model.addAttribute("user", creator);
		return "add_bucket.jsp";
	}
	
	// Edit Course
	@GetMapping("/bucketlist/{id}/edit")
	public String editBucket(@PathVariable("id") Long id, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("user_id");
		if(userId == null) {
			return "redirect:/";
		}
		Bucket bucket = bucketServ.oneBucket(id);
		model.addAttribute("bucket", bucket);
		
		// This calls the user data from the model
		// User u = userServ.getOne(userId);
		// model.addAttribute("user", u);
		return "edit_bucket.jsp";
	}

	// ==== Action ============
	
	// Create
	@PostMapping("/bucketlist/new")
	public String createBucket(@Valid @ModelAttribute("course") Bucket bucket, BindingResult result, HttpSession session) {
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
