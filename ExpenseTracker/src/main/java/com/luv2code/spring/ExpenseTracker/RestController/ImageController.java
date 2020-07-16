package com.luv2code.spring.ExpenseTracker.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.luv2code.spring.ExpenseTracker.Model.ImageModel;
import com.luv2code.spring.ExpenseTracker.Service.ExpenseService;
import com.luv2code.spring.ExpenseTracker.Service.ImageService;

@Controller
public class ImageController {

	
	@Autowired
    private ImageService myService;
	
	@Autowired
	private ExpenseService expenseService;

    @GetMapping("/")
    public String test() {
        return "index";
    }

    @PostMapping("/fileupload")
    public String fileUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
        try {
           
            byte[] image = file.getBytes();
            ImageModel model = new ImageModel(name, image);
            model.setExpenses(expenseService.findById(1));
            
            int saveImage = myService.saveImage(model);
            if (saveImage == 1) {
                return "success";
            } else {
                return "error";
            }
        } catch (Exception e) {
            
            return "error";
        }
    }

    @GetMapping("/getDetail/{expenseId}")
    public String getDbDetils(@PathVariable("expenseId") int expenseId, Model model) {
        try {
            
        	ImageModel imagesObj = myService.getImages(expenseId);
        	imagesObj.setExpenses(expenseService.findById(expenseId));
            model.addAttribute("id", imagesObj.getId());
            model.addAttribute("name", imagesObj.getName());
            byte[] encode = java.util.Base64.getEncoder().encode(imagesObj.getImage());
            model.addAttribute("image", new String(encode, "UTF-8"));
            return "imagedetails";
        } catch (Exception e) {
           
            model.addAttribute("message", "Error in getting image");
            return "redirect:/";
        }
    }

}
