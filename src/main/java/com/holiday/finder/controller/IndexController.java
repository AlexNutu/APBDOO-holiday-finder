package com.holiday.finder.controller;

import com.holiday.finder.model.*;
import com.holiday.finder.service.CategoryService;
import com.holiday.finder.service.DestinationService;
import com.holiday.finder.service.PlaceService;
import com.holiday.finder.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class IndexController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    DestinationService destinationService;

    @Autowired
    SeasonService seasonService;

    @Autowired
    PlaceService placeService;


    @RequestMapping("/")
    public String getIndexPage(Model model,
                               @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size,
                               @RequestParam("season") Optional<Integer> season,
                               @RequestParam("destination") Optional<Integer> destination,
                               @RequestParam("category") Optional<Integer> category) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(3);
        Page<Place> placePage = null;

        if (season.isPresent()) {
            Season seasonObj = seasonService.getById(season.get()).get();
            placePage = placeService.getPlacesBySeason(seasonObj, PageRequest.of(currentPage - 1, pageSize));
        } else if (destination.isPresent()) {
            Destination destinationObj = destinationService.getById(destination.get()).get();
            placePage = placeService.getPlacesByDestination(destinationObj, PageRequest.of(currentPage - 1, pageSize));
        } else if (category.isPresent()) {
            Category categoryObj = categoryService.getById(category.get()).get();
            placePage = placeService.getPlacesByCategories(categoryObj, PageRequest.of(currentPage - 1, pageSize));
        } else {
            placePage = placeService.getAllPlaces(PageRequest.of(currentPage - 1, pageSize));
        }

        model.addAttribute("placePage", placePage);

        int totalPages = placePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        List<Category> categories = categoryService.getAll();
        List<Destination> destinations = destinationService.getAll();
        List<Season> seasons = seasonService.getAll();
        model.addAttribute("categories", categories);
        model.addAttribute("destinations", destinations);
        model.addAttribute("seasons", seasons);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); // get logged in username

        model.addAttribute("username", name);

        return "index";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }

    @RequestMapping(value="/hello", method = RequestMethod.POST )
    @ResponseBody
    public String showHelloWord(@RequestParam(required = false) String name){
        return "Hello " + name;
    }

    @RequestMapping("/showLogInForm")
    public String showLoginForm(){
        return "login";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Hello hello) {
        return "result";
    }

    @GetMapping("/showErrorLogIn")
    public String showErrorLogIn(Model model){
        model.addAttribute("errorMessage", "Please try again ... ");
        return "login";
    }

    @RequestMapping("/contact")
    public String getContactPage(Model model) {
        List<Category> categories = categoryService.getAll();
        List<Destination> destinations = destinationService.getAll();
        List<Season> seasons = seasonService.getAll();
        model.addAttribute("categories", categories);
        model.addAttribute("destinations", destinations);
        model.addAttribute("seasons", seasons);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); // get logged in username

        model.addAttribute("username", name);

        return "contact";
    }

}
