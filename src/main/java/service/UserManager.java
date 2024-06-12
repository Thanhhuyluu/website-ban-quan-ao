package service;

import java.util.ArrayList;
import java.util.List;

import dao.UserDAO;
import model.User;

public class UserManager {
	private static UserManager userManager;
	
	public static UserManager getInstance() {
		if(userManager == null) {
			userManager = new UserManager();
		}
		return userManager;
	}
	public List<String> IsValidAccount(User u) {
		List<User> list = new ArrayList<User>();
		list =  UserDAO.getInstance().selectAll();
		List<String> errors = new ArrayList<String>();
		
		
		for(User user : list) {
			if( user.getEmail().equals(u.getEmail()) && user.getPhoneNumber().equals( u.getPhoneNumber())  && user.getPassword().equals(u.getPassword()) ) {
				errors.add("register_account_exist");
				return errors;	
			}
			
			if(user.getEmail().equals(u.getEmail())) {
				errors.add("register_email_error");
			}
			if(user.getPhoneNumber().equals(u.getPhoneNumber())) {
				errors.add("register_phonenumber_error");
			}
			if(user.getPassword().equals(u.getPassword())) {
				errors.add("register_password_error");
			}
		}
		return errors;
		
				
	}
	
}
