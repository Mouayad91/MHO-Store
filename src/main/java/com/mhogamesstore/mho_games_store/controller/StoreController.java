package com.mhogamesstore.mho_games_store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mhogamesstore.mho_games_store.Games;
import com.mhogamesstore.mho_games_store.service.StoreService;

import jakarta.validation.Valid;

@Controller
public class StoreController {


    StoreService storeService = new StoreService();

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false) String id) {
       
        model.addAttribute("games", storeService.getGameFromId(id) );
        return "form";
    }

    @GetMapping("/inventory")
    public String getInventory(Model model) {
        model.addAttribute("games", storeService.getGames());
        return "inventory";
    }

    @PostMapping("/submit")
    public String submitHandle(@Valid Games game, BindingResult result  ,RedirectAttributes redirectAttributes) {
       
        Double price = game.getPrice();
        Double discount = game.getDiscount();
        if (price == null) {
            result.rejectValue("price","","");
        } else if (discount == null) {
            result.rejectValue("discount", "","");
        } else if (price < discount) {
            result.rejectValue("price", "", "Price cannot be less than the discount");
        }
    
            if(result.hasErrors()) return "form";
            
        String status = storeService.handelSubmit(game);
        redirectAttributes.addFlashAttribute("status", status);
        return "redirect:/inventory";
    }




}
