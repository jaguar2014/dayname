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



        System.out.println(userdate);

        LocalDate localUserDate =  LocalDate.parse(userdate);
        System.out.println(localUserDate.getDayOfMonth());




        String zodiac = getZodiac(localUserDate);

        System.out.println(zodiac);

        model.addAttribute("zodiac", zodiac);

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

    private String getZodiac(LocalDate localUserDate) {

        String sign="";
//dec 22 to jan 19

        if      ((localUserDate.getMonthValue() == 12 && localUserDate.getDayOfMonth() >= 22 && localUserDate.getDayOfMonth() <= 31)
                || (localUserDate.getMonthValue() ==  1 && localUserDate.getDayOfMonth() >= 1 && localUserDate.getDayOfMonth() <= 19)) {
            System.out.println("Capricorn");
            sign="Capricorn";
        }
//january 20 to february 18
        else if ((localUserDate.getMonthValue() ==  1 && localUserDate.getDayOfMonth() >= 20 && localUserDate.getDayOfMonth() <= 31) ||
                (localUserDate.getMonthValue() ==  2 && localUserDate.getDayOfMonth() >= 1 && localUserDate.getDayOfMonth() <= 18)){
            System.out.println("Aquarius");
            sign="Aquarius";
        }
        //february 19 to march 20

        else if ((localUserDate.getMonthValue() ==  2 && localUserDate.getDayOfMonth() >= 19 && localUserDate.getDayOfMonth() <= 28)
                || (localUserDate.getMonthValue() ==  3 && localUserDate.getDayOfMonth() >= 1 && localUserDate.getDayOfMonth() <= 20)){
            System.out.println("Pisces");
            sign="Pisces";
        }
        //march 21 to april 19

        else if ((localUserDate.getMonthValue() ==  3 && localUserDate.getDayOfMonth() >= 21 && localUserDate.getDayOfMonth() <= 31)
                || (localUserDate.getMonthValue() ==  4 && localUserDate.getDayOfMonth() >= 1 && localUserDate.getDayOfMonth() <= 19)){
            System.out.println("Aries");
            sign="Aries";
        }
//april 20 to may 20
        else if ((localUserDate.getMonthValue() ==  4 && localUserDate.getDayOfMonth() >= 20 && localUserDate.getDayOfMonth() <= 30)
                || (localUserDate.getMonthValue() ==  5 && localUserDate.getDayOfMonth() >= 1 && localUserDate.getDayOfMonth() <= 20)){
            System.out.println("Taurus");
            sign="Taurus";
        }
//may 21 to june 20
        else if ((localUserDate.getMonthValue() ==  5 && localUserDate.getDayOfMonth() >= 21 && localUserDate.getDayOfMonth() <= 31)
                || (localUserDate.getMonthValue() ==  6 && localUserDate.getDayOfMonth() >= 1 && localUserDate.getDayOfMonth() <= 20)){
            System.out.println("Gemini");
            sign="Gemini";
        }
//june 21 to july 22
        else if ((localUserDate.getMonthValue() ==  6 && localUserDate.getDayOfMonth() >= 21 && localUserDate.getDayOfMonth() <= 30)
                || (localUserDate.getMonthValue() ==  7 && localUserDate.getDayOfMonth() >= 1 && localUserDate.getDayOfMonth() <= 22)){
            System.out.println("Cancer");
            sign="Cancer";
        }
//july 23 to august 22
        else if ((localUserDate.getMonthValue() ==  7 && localUserDate.getDayOfMonth() >= 23 && localUserDate.getDayOfMonth() <= 31)
                || (localUserDate.getMonthValue() ==  8 && localUserDate.getDayOfMonth() >= 1 && localUserDate.getDayOfMonth() <= 22)){
            System.out.println("Leo");
            sign="Leo";
        }
//august 23 to sept 22
        else if ((localUserDate.getMonthValue() ==  8 && localUserDate.getDayOfMonth() >= 23 && localUserDate.getDayOfMonth() <= 31)
                || (localUserDate.getMonthValue() ==  9 && localUserDate.getDayOfMonth() >= 1 && localUserDate.getDayOfMonth() <= 22)){
            System.out.println("Virgo");
            sign="Virgo";
        }
//sept 23 to oct 22
        else if ((localUserDate.getMonthValue() ==  9 && localUserDate.getDayOfMonth() >= 23 && localUserDate.getDayOfMonth() <= 30)
                || (localUserDate.getMonthValue() == 10 && localUserDate.getDayOfMonth() >= 1 && localUserDate.getDayOfMonth() <= 22)){
            System.out.println("Libra");
            sign="Libra";
        }
//oct 23 to nov 21
        else if ((localUserDate.getMonthValue() == 10 && localUserDate.getDayOfMonth() >= 23 && localUserDate.getDayOfMonth() <= 31)
                || (localUserDate.getMonthValue() == 11 && localUserDate.getDayOfMonth() >= 1 && localUserDate.getDayOfMonth() <= 21)){
            System.out.println("Scorpio");
            sign="Scorpio";
        }
//nov 22 to dec 21
        else if ((localUserDate.getMonthValue() == 11 && localUserDate.getDayOfMonth() >= 22 && localUserDate.getDayOfMonth() <= 30)
                || (localUserDate.getMonthValue() == 12 && localUserDate.getDayOfMonth() >= 1 && localUserDate.getDayOfMonth() <= 21)){
            System.out.println("Sagittarius");
            sign="Sagittarius";
        }

        else{
            sign = "Illegal date";
        }






        return sign;
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
