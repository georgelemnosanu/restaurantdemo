//package com.restaurant.restaurantdemo.controller;
//
//import com.restaurant.restaurantdemo.model.Menu;
//import com.restaurant.restaurantdemo.repository.MenuRespository;
//import com.restaurant.restaurantdemo.service.ServiceInterface.MenuService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;
//
//@RestController
//@RequestMapping("/menu")
//@CrossOrigin
//public class MenuController {
//    @Autowired
//    private MenuService menuService;
//    @Autowired
//    private MenuRespository menuRespository;
//
//
//    @GetMapping("/menu/{menuId}")
//    public String getMenuDetails(@PathVariable Integer menuId, Model model) {
//        Menu menu = menuService.findById(menuId);
//        model.addAttribute("menu", menu);
//        return "menu/viewMenu";
//    }
//    @GetMapping("/specialities/{menuId}")
//    public Menu getSpecialities(@PathVariable Integer menuId) {
//        return menuService.findById(menuId);
//    }
//    @GetMapping("/allMenus")
//    public String allMenus(Model model){
//        model.addAttribute("viewAllMenus",menuService.findAllMenu());
//        return "menu/listMenus";
//    }
//
//
//    @GetMapping("/createMenu")
//    public String createMenu(String name,Model model) {
//        model.addAttribute("menu", new Menu());
//        return "menu/createMenu";
//    }
//
//    @PostMapping ("/submitCreateMenu")
//    public String createMenu(String name){
//       try{
//           menuService.createMenu(name);
//           return "redirect:/viewAllmenus";
//       }catch (ResponseStatusException e){
//           return "entityExistError";
//       }
//    }
//
//
//
//}
