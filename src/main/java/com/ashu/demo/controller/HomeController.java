package com.ashu.demo.controller;

import com.ashu.demo.Service.FemaleDayName;
import com.ashu.demo.Service.MaleDayName;
import com.ashu.demo.model.AppUser;
import com.ashu.demo.model.DayName;
import com.ashu.demo.repository.AppRoleRepository;
import com.ashu.demo.repository.AppUserRepository;
import com.ashu.demo.repository.DayNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class HomeController {


    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    AppRoleRepository appRoleRepository;

    @Autowired
    DayNameRepository dayNameRepository;



    @GetMapping("/")
    public String showIndex(Model model){


        return "index";



    }

    @GetMapping("/dayname")
    public String displayDayName(HttpServletRequest request, Model model){
//
//       String userdate =  request.getParameter("userdate");
//
//       LocalDate localUserDate =  LocalDate.parse(userdate);
//
//        System.out.println(localUserDate.getDayOfWeek());


//
         model.addAttribute("newdayname", new DayName());




           return "index";



    }



    @PostMapping("/dayname")
    public String processDayName( @Valid DayName dayName , BindingResult result, HttpServletRequest request,Model model){

        if (result.hasErrors()) {
            return "index";
        }


        dayNameRepository.save(dayName);



        String userdate =  request.getParameter("userdate");

        LocalDate localUserDate =  LocalDate.parse(userdate);

       DayOfWeek dayOfWeek = localUserDate.getDayOfWeek();

       if(dayOfWeek.getValue()==1){

           model.addAttribute("mondaymale",MaleDayName.Monday);
           model.addAttribute("mondayfemale", FemaleDayName.Monday);

           return "displaydayname";


       }else if (dayOfWeek.getValue()==2){
           model.addAttribute("tuesdaymale",MaleDayName.Tuesday);
           model.addAttribute("tuesdayfemale", FemaleDayName.Tuesday);

           return "displaydayname";

       } else if (dayOfWeek.getValue() == 3) {
           model.addAttribute("wednesdaymale",MaleDayName.Wednesday);
           model.addAttribute("wednesdayfemale", FemaleDayName.Wednesday);

           return "displaydayname";
       }else if(dayOfWeek.getValue()==4){
           model.addAttribute("thursdaymale",MaleDayName.Thursday);
           model.addAttribute("thursdayfemale", FemaleDayName.Thursday);

           return "displaydayname";
       }else if(dayOfWeek.getValue()==5){
           model.addAttribute("fridaymale",MaleDayName.Friday);
           model.addAttribute("fridayfemale", FemaleDayName.Friday);

           return "displaydayname";
       }else if(dayOfWeek.getValue()==6){
           model.addAttribute("saturdaymale",MaleDayName.Saturday);
           model.addAttribute("saturdayfemale", FemaleDayName.Saturday);

           return "displaydayname";
       }else if (dayOfWeek.getValue()==7) {
           model.addAttribute("sundaymale", MaleDayName.Sunday);
           model.addAttribute("sundayfemale", FemaleDayName.Sunday);

           return "displaydayname";

       }


        return "displaydayname";












    }

    @GetMapping("/login")

    public String login(){

        return "login";

    }


    @GetMapping("/regsiter")
    public String showSignUpForm(Model model)
    {
        model.addAttribute("newuser",new AppUser());
        return "register";
    }

    @PostMapping("/register")
    public String signUp(@Valid @ModelAttribute("newuser") AppUser thisUser, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "register";
        }

        appUserRepository.save(thisUser);

        return "redirect:/";
    }




}
