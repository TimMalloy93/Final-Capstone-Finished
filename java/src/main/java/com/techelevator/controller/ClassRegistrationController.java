package com.techelevator.controller;

import com.techelevator.dao.ClassRegistrationDao;
import com.techelevator.model.ClassDto;
import com.techelevator.model.ClassRegistration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/class/register")
@PreAuthorize("isAuthenticated()")
public class ClassRegistrationController {


    private final ClassRegistrationDao classRegistrationDao;

    public ClassRegistrationController (ClassRegistrationDao classRegistrationDao){
        this.classRegistrationDao = classRegistrationDao;
    }

    @GetMapping("/{date}/{className}")
    public ClassRegistration displayRegistration (@PathVariable ("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, @PathVariable("className") String className){
        return classRegistrationDao.displayRegistration(date, className);
    }

    @PostMapping("/{date}")
    public void registerForClass(@PathVariable ("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, @RequestBody ClassRegistration classRegistration, Principal principal){
        classRegistrationDao.registerForClass(date, classRegistration, principal);
    }

    @GetMapping("/myclasses")
    public List<ClassRegistration> myClasses(Principal principal){
        return classRegistrationDao.myClasses(principal);
    }

}
