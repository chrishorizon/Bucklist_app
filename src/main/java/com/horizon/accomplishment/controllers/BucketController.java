package com.horizon.accomplishment.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

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
	
	// Read One Page
	@GetMapping("/bucketlist/{id}")
	public String showCourse(@PathVariable("id") Long id, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("user_id");
		if(userId == null) {
			return "redirect:/";
		}
		
		// This calls the user data from the model
		User u = userServ.getOne(userId);
		model.addAttribute("loggedInUser", u);
		
		Bucket buck = bucketServ.oneBucket(id);
		model.addAttribute("bucket", buck);
		// model.addAttribute("userId", userId);
		return "show_bucket.jsp";
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
	public String createBucket(@Valid @ModelAttribute("bucket") Bucket bucket, BindingResult result, HttpSession session) {
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
	
	// Update
	@PutMapping("/bucketlist/{id}/edit")
	public String updateBucket(@Valid @ModelAttribute("bucket") Bucket bucketFromForm, BindingResult result, HttpSession session, @PathVariable("id") Long id, Model model) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		if(result.hasErrors()) {
			return "edit_bucket.jsp";
		} else {
			// Allows update if logged in user is the creator
			Bucket bucketFromDb = bucketServ.oneBucket(id);
			if(!session.getAttribute("user_id").equals(bucketFromDb.getCreator().getId())) {
				return "redirect:/home";
			}
			// we update the book object from the DB with the data from the form
			bucketFromDb.setName(bucketFromForm.getName());
			bucketFromDb.setDescription(bucketFromForm.getDescription());
			
			// we save the book object with the updated data to the DB
			bucketServ.createBucket(bucketFromDb);
			return "redirect:/home";
		}
	}
	
	// Delete
	@DeleteMapping("/bucketlist/{id}/delete")
	public String deleteBucket(@PathVariable("id") Long id, HttpSession session) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		bucketServ.deleteBucket(id);
		return "redirect:/home";
	}

}
