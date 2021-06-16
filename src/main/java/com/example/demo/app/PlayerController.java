package com.example.demo.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Player;
import com.example.demo.service.PlayerService;

@Controller
@RequestMapping("players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    public String index(Pageable pageable, Model model) {

        Page<Player> playerPage = playerService.getPlayers(pageable);

        model.addAttribute("page", playerPage);
        model.addAttribute("players", playerPage.getContent());

        return "players";
    }
}