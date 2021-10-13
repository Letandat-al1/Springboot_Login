package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.javaguides.springboot.model.Account;
import net.javaguides.springboot.service.AccountService;

@Controller
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	// display list of employees
	@GetMapping("/")
	public String viewHomePage(Model model) {
		return findPaginated(1, "Name", "asc", model);		
	}
	
	@GetMapping("/showNewAccountForm")
	public String showNewAccountForm(Model model) {
		// create model attribute to bind form data
		Account account = new Account();
		model.addAttribute("account", account);
		return "new_employee";
	}
	
	@PostMapping("/saveAccount")
	public String saveAccount(@ModelAttribute("account") Account account) {
		// save employee to database
		accountService.saveAccount(account);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
		// get employee from the service
		Account account = accountService.getAccountById(id);
		
		// set employee as a model attribute to pre-populate the form
		model.addAttribute("account", account);
		return "update_employee";
	}
	
	@GetMapping("/deleteAccount/{id}")
	public String deleteAccount(@PathVariable (value = "id") long id) {
		
		// call delete employee method 
		this.accountService.deleteAccountById(id);
		return "redirect:/";
	}
	
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir,
			Model model) {
		int pageSize = 5;
		
		Page<Account> page = accountService.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Account> listAccounts = page.getContent();
		
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		
		model.addAttribute("listAccounts", listAccounts);
		return "index";
	}
}
