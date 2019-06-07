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
import org.springframework.security.core.GrantedAuthority;
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
                               @RequestParam(defaultValue = "") String q,
                               @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size,
                               @RequestParam("season") Optional<Integer> season,
                               @RequestParam("destination") Optional<Integer> destination,
                               @RequestParam("category") Optional<Integer> category) {

        // Display filters
        String categoryString = "";
        String destinationString = "";
        String seasonString = "";
        String searchString = "";
        Long seassonId = 0L;
        Long categoryId = 0L;
        Long destinationId = 0L;

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(3);
        Page<Place> placePage = null;

        if (season.isPresent()) {
            Season seasonObj = seasonService.getById(season.get()).get();
            seasonString = seasonObj.getName();
            seassonId = seasonObj.getId();
            placePage = shrinkDescription(placeService.getPlacesBySeason(seasonObj, PageRequest.of(currentPage - 1, pageSize)));
        } else if (destination.isPresent()) {
            Destination destinationObj = destinationService.getById(destination.get()).get();
            destinationString = destinationObj.getName();
            destinationId = destinationObj.getId();
            placePage = shrinkDescription(placeService.getPlacesByDestination(destinationObj, PageRequest.of(currentPage - 1, pageSize)));
        } else if (category.isPresent()) {
            Category categoryObj = categoryService.getById(category.get()).get();
            categoryId = categoryObj.getId();
            categoryString = categoryObj.getName();
            placePage = shrinkDescription(placeService.getPlacesByCategories(categoryObj, PageRequest.of(currentPage - 1, pageSize)));
        } else if (!q.trim().toLowerCase().equals("")) { // the searched string
            searchString = q;
            placePage = shrinkDescription(placeService.getPlacesByTitleLikeOrDescriptionLike("%" + q + "%", PageRequest.of(currentPage - 1, pageSize)));
        } else {
            placePage = shrinkDescription(placeService.getAllPlaces(PageRequest.of(currentPage - 1, pageSize)));
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
        String isAdmin = "no";
        for (GrantedAuthority authority : auth.getAuthorities()) {
            if (authority.getAuthority().equals("ADMIN")) {
                isAdmin = "yes";
            }
        }
        model.addAttribute("isAdmin", isAdmin);

        model.addAttribute("username", name);
        model.addAttribute("categoryString", categoryString);
        model.addAttribute("destinationString", destinationString);
        model.addAttribute("seasonString", seasonString);
        model.addAttribute("searchString", searchString);

        model.addAttribute("seassonId", seassonId);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("destinationId", destinationId);

        return "index";
    }

    private Page<Place> shrinkDescription(Page<Place> placePage) {
        for (Place p : placePage) {
            String description = p.getDescription();
            String subString = description;
            if (description.length() > 239) {
                if (description.charAt(239) == ' ') {
                    subString = description.substring(0, 240) + "...";
                } else if (description.charAt(238) == ' ') {
                    subString = description.substring(0, 239) + "...";
                } else if (description.charAt(237) == ' ') {
                    subString = description.substring(0, 238) + "...";
                } else if (description.charAt(236) == ' ') {
                    subString = description.substring(0, 237) + "...";
                } else if (description.charAt(235) == ' ') {
                    subString = description.substring(0, 236) + "...";
                } else {
                    subString = description.substring(0, 240) + " ...";
                }
            }
            p.setDescription(subString);
        }
        return placePage;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    @ResponseBody
    public String showHelloWord(@RequestParam(required = false) String name) {
        return "Hello " + name;
    }

    @RequestMapping("/showLogInForm")
    public String showLoginForm() {
        // If the user is authenticated redirect to homepage, else retrieve login page
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getPrincipal().equals("anonymousUser")) {
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Hello hello) {
        return "result";
    }

    @GetMapping("/showErrorLogIn")
    public String showErrorLogIn(Model model) {
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
