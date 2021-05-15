package com.luv2code.spring.ExpenseTracker.RestController;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.luv2code.spring.ExpenseTracker.Model.ConfirmationToken;
import com.luv2code.spring.ExpenseTracker.Model.User;
import com.luv2code.spring.ExpenseTracker.Service.EmailSenderService;
import com.luv2code.spring.ExpenseTracker.Service.UserService;


@Controller
public class RegisterController {

	@Autowired
	private UserService userService;
	
//	@Autowired
//	private RoleService roleService;
	
	@Value("${server.port}")
        private int serverPort;

	 @Autowired
	    private EmailSenderService emailSenderService;

	public RegisterController(UserService userService) {
		userService=this.userService;
	}
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	
	
	 @RequestMapping(value="/register", method = RequestMethod.GET)
	    public ModelAndView displayRegistration(ModelAndView modelAndView, User user)
	    {
	        modelAndView.addObject("user", user);
	        modelAndView.setViewName("register");
	        return modelAndView;
	    }

	    @RequestMapping(value="/register", method = RequestMethod.POST)
	    public ModelAndView registerUser(ModelAndView modelAndView,@Valid  User user,BindingResult bindingResult)
	    {
          if(bindingResult.hasErrors()) {
        	  modelAndView.setViewName("register");
        	 
          }
          else {
	        List<User> existingUser = userService.findByEmailIdIgnoreCase(user.getEmailId());
	        List<User>existingUsers = userService.findByUserName(user.getUserName());
	        if(existingUser.size()>0)
	        {
	            modelAndView.addObject("message","This email already exists!");
	            modelAndView.setViewName("error");
	        }
	        
	        else if(existingUsers.size()>0) {
	        	modelAndView.addObject("message", "User Name already Exists!");
	        	modelAndView.setViewName("error");
	        }
	        else
	        {
	        	String pass=bcryptPasswordEncoder.encode(user.getPassword());
	        	user.setPassword(pass);
//	        	Collection<Roles>role=null;
//	        	role.add(roleService.findByName("USER"));
//	        	user.setRoles(role);
	            userService.save(user);

	            ConfirmationToken confirmationToken = new ConfirmationToken(user);

	            userService.saveConfirmation(confirmationToken);

	            SimpleMailMessage mailMessage = new SimpleMailMessage();
	            mailMessage.setTo(user.getEmailId());
	            mailMessage.setSubject("Complete Registration!");
	            mailMessage.setFrom("noreplyInterviewTracker@gmail.com");
	            mailMessage.setText("To confirm your account, please click here : "
	            +"http://localhost:"+serverPort+"/confirm-account?token="+confirmationToken.getConfirmationToken());

	            emailSenderService.sendEmail(mailMessage);

	            modelAndView.addObject("emailId", user.getEmailId());

	            modelAndView.setViewName("successfulRegisteration");
	        }
          }

	        return modelAndView;
	    }

	    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
	    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
	    {
	        ConfirmationToken token = userService.findByConfirmationToken(confirmationToken);

	        if(token != null)
	        {
	            User user = userService.findByEmailIdIgnoreCase(token.getUser().getEmailId()).get(0);
	            user.setEnabled(true);
//	            Collection<Roles>role=null;
//	        	role.add(roleService.findByName("Confirmed_USER"));
//	        	user.setRoles(role);
	            userService.save(user);
	            modelAndView.setViewName("fancy-login");
	        }
	        else
	        {
	            modelAndView.addObject("message","The link is invalid or broken!");
	            modelAndView.setViewName("error");
	        }

	        return modelAndView;
	    }
	    public EmailSenderService getEmailSenderService() {
			return emailSenderService;
		}

		public void setEmailSenderService(EmailSenderService emailSenderService) {
			this.emailSenderService = emailSenderService;
		}
}
