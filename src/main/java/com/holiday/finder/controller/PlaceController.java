package com.holiday.finder.controller;

import com.holiday.finder.model.Category;
import com.holiday.finder.model.Destination;
import com.holiday.finder.model.Place;
import com.holiday.finder.model.Season;
import com.holiday.finder.service.CategoryService;
import com.holiday.finder.service.DestinationService;
import com.holiday.finder.service.PlaceService;
import com.holiday.finder.service.SeasonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/add")
    public String addPlace(Model model) {

        List<Category> categories = categoryService.getAll();
        List<Destination> destinations = destinationService.getAll();
        List<Season> seasons = seasonService.getAll();

        model.addAttribute("newPlace", new Place());
        model.addAttribute("categoriesFilter", categories);
        model.addAttribute("destinationsFilter", destinations);
        model.addAttribute("seasonsFilter", seasons);

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
        return "add_place";
    }

    @RequestMapping("/see/{placeId}")
    public String seePlace(@PathVariable Long placeId, Model model) {
        Place place = placeService.getById(placeId);
        model.addAttribute("place", place);
        model.addAttribute("pcategory", place.getCategories().iterator().next().getName());
        model.addAttribute("pseason", place.getSeason().getName());
        model.addAttribute("pdestination", place.getDestination().getName());


        List<Category> categories = categoryService.getAll();
        List<Destination> destinations = destinationService.getAll();
        List<Season> seasons = seasonService.getAll();

        model.addAttribute("categories", categories);
        model.addAttribute("destinations", destinations);
        model.addAttribute("seasons", seasons);

        return "view_place";
    }

    @PostMapping("/new")
    public String saveUpdatePlace(@ModelAttribute("newPlace") @Valid Place newPlace, BindingResult result) {
        if (result.hasErrors()) {
            log.info("Form has errors!");
            return "add_place";
        } else {
            log.info("Inserting/Updating new place");
            placeService.savePlace(newPlace);
            return "redirect:/";
        }
    }

}
