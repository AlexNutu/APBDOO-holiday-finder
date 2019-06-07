package com.holiday.finder.controller;

import com.holiday.finder.model.*;
import com.holiday.finder.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/place")
@Slf4j
public class PlaceController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    DestinationService destinationService;

    @Autowired
    SeasonService seasonService;

    @Autowired
    PlaceService placeService;

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @RequestMapping("/add")
    public String addPlace(Model model) {

        List<Category> categories = categoryService.getAll();
        List<Destination> destinations = destinationService.getAll();
        List<Season> seasons = seasonService.getAll();

        model.addAttribute("newPlace", new Place());
        model.addAttribute("categoriesFilter", categories);
        model.addAttribute("destinationsFilter", destinations);
        model.addAttribute("seasonsFilter", seasons);

        model.addAttribute("categories", categories);
        model.addAttribute("destinations", destinations);
        model.addAttribute("seasons", seasons);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); // get logged in username

        model.addAttribute("username", name);

        return "add_place";
    }

    @RequestMapping("/edit/{placeId}")
    public String editPlace(Model model, @PathVariable Long placeId) {
        List<Category> categories = categoryService.getAll();
        List<Destination> destinations = destinationService.getAll();
        List<Season> seasons = seasonService.getAll();

        Place newPlace = placeService.getById(placeId);

        model.addAttribute("newPlace", newPlace);
        model.addAttribute("categoriesFilter", categories);
        model.addAttribute("destinationsFilter", destinations);
        model.addAttribute("seasonsFilter", seasons);

        model.addAttribute("categories", categories);
        model.addAttribute("destinations", destinations);
        model.addAttribute("seasons", seasons);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); // get logged in username

        model.addAttribute("username", name);

        return "add_place";
    }

    @RequestMapping("/see/{placeId}")
    public String seePlace(@PathVariable Long placeId, Model model) {
        Place place = placeService.getById(placeId);
        model.addAttribute("place", place);
        model.addAttribute("longPlaceId", place.getId());
        model.addAttribute("pcategory", place.getCategories().iterator().next().getName());
        model.addAttribute("pseason", place.getSeason().getName());
        model.addAttribute("pdestination", place.getDestination().getName());


        List<Category> categories = categoryService.getAll();
        List<Destination> destinations = destinationService.getAll();
        List<Season> seasons = seasonService.getAll();

        model.addAttribute("categories", categories);
        model.addAttribute("destinations", destinations);
        model.addAttribute("seasons", seasons);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); // get logged in username

        model.addAttribute("username", name);

        Comment newComment = new Comment();
        model.addAttribute("newComment", newComment);

        return "view_place";
    }

    @PostMapping("/new")
    public String saveUpdatePlace(Model model, @ModelAttribute("newPlace") @Valid Place newPlace, BindingResult result) {
        if (result.hasErrors()) {
            log.info("Form has errors!");

            List<Category> categories = categoryService.getAll();
            List<Destination> destinations = destinationService.getAll();
            List<Season> seasons = seasonService.getAll();

            model.addAttribute("categoriesFilter", categories);
            model.addAttribute("destinationsFilter", destinations);
            model.addAttribute("seasonsFilter", seasons);

            model.addAttribute("categories", categories);
            model.addAttribute("destinations", destinations);
            model.addAttribute("seasons", seasons);

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName(); // get logged in username

            model.addAttribute("username", name);

            return "add_place";
        } else {
            log.info("Inserting/Updating new place");
            placeService.savePlace(newPlace);
            return "redirect:/";
        }
    }

    @PostMapping("/postComment/")
    public String postComment(Model model, @ModelAttribute("newComment") Comment newComment, @RequestParam("longPlaceId") Long longPlaceId, BindingResult result) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Place place = placeService.getById(longPlaceId);
        newComment.setPlace(place);

        User currentUser = userService.getByUsername(auth.getName());
        newComment.setUser(currentUser);

        commentService.saveComment(newComment);

        return "redirect:/place/see/" + longPlaceId;

    }

}
