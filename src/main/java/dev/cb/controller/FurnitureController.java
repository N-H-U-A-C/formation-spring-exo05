package dev.cb.controller;

import dev.cb.business.domain.Furniture;
import dev.cb.business.service.FurnitureService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/furnitures")
public class FurnitureController {

    private final FurnitureService furnitureService;

    public FurnitureController(FurnitureService furnitureService) {
        this.furnitureService = furnitureService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("furnitures", furnitureService.getAll());
        return "/furnitures/list";
    }

    @GetMapping("/create")
    public String getSaveForm(Model model) {
        model.addAttribute("furniture", new Furniture());
        return "furnitures/form";
    }

    @PostMapping("/create")
    public String save(@Valid @ModelAttribute Furniture furniture, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            furnitureService.createOrUpdate(furniture);
            return "redirect:/furnitures";
        } else {
            return "furnitures/form";
        }
    }

    @GetMapping("/update/{id}")
    public String getUpdateForm(@PathVariable UUID id, Model model) {
        Optional<Furniture> optionalFurniture = furnitureService.getById(id);
        //TODO handle if optional is empty
        optionalFurniture.ifPresent(furniture -> model.addAttribute("furniture", furniture));
        return "furnitures/form";
    }

    @PostMapping("/update/{id}")
    public String upgrade(@Valid @ModelAttribute Furniture furniture, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            furnitureService.createOrUpdate(furniture);
            return "redirect:/furnitures";
        } else {
            return "furnitures/form";
        }
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable UUID id) {
        furnitureService.deleteById(id);
        return "redirect:/furnitures";
    }
}
