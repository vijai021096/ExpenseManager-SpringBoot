package com.luv2code.spring.ExpenseTracker.Service;

import java.util.Optional;
import java.util.logging.Logger;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.spring.ExpenseTracker.Model.ImageModel;
import com.luv2code.spring.ExpenseTracker.Repository.ImageRepository;

@Service
public class ImageService {

	
    @Autowired
    private ImageRepository dao;

    public int saveImage(ImageModel model) {
        try {
            dao.save(model);
            return 1;
        } catch (Exception e) {
           
            return 0;
        }
    }

    public ImageModel getImages(int id) {
        ImageModel findById = dao.findByExpensesExpenseId(id);
        
        ImageModel getImageDetails = findById;
           
            return getImageDetails;
        
    }
}

