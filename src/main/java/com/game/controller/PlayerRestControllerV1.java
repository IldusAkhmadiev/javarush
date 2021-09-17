package com.game.controller;


import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.exception_handling.CheckFieldPlayer;
import com.game.exception_handling.CheckValidId;
import com.game.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rest/players")
public class PlayerRestControllerV1 {

    @Autowired
    private PlayerService playerService;

    @GetMapping("")
    public ResponseEntity<List<Player>> getAllPlayer(@RequestParam(value = "name", required = false) String name,
                                                     @RequestParam(value = "title", required = false) String title,
                                                     @RequestParam(value = "race", required = false) Race race,
                                                     @RequestParam(value = "profession", required = false) Profession profession,
                                                     @RequestParam(value = "after", required = false) Long after,
                                                     @RequestParam(value = "before", required = false) Long before,
                                                     @RequestParam(value = "banned", required = false) Boolean banned,
                                                     @RequestParam(value = "minExperience", required = false) Integer minExperience,
                                                     @RequestParam(value = "maxExperience", required = false) Integer maxExperience,
                                                     @RequestParam(value = "minLevel", required = false) Integer minLevel,
                                                     @RequestParam(value = "maxLevel", required = false) Integer maxLevel,
                                                     @RequestParam(value = "order", required = false) PlayerOrder order,
                                                     @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                     @RequestParam(value = "pageSize", required = false) Integer pageSize) {

        List<Player> all = playerService.getAllAll(name,title,race,profession,after,before,banned,
                minExperience,maxExperience,minLevel,maxLevel,order,pageNumber,pageSize);
        return new ResponseEntity<>(all,HttpStatus.OK);
    }

    /** 2 Create player  **/
    @PostMapping("/")
    public ResponseEntity<Player> createPlayer(@RequestBody Player player ) {
        if(!CheckFieldPlayer.canCreate(player)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(!CheckFieldPlayer.checkCorrentPlayer(player)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        playerService.add(player);


        return new ResponseEntity<>(player,HttpStatus.OK);
    }

    /** 3 Update player **/
    @PostMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player,@PathVariable("id")  String stringId  ) {
        Long id = 0L;
        if(CheckValidId.checkId(stringId)) {
            id = Long.parseLong(stringId);
        } else  {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Player player1 = playerService.getById(id);
        if(player.getName() == null && player.getTitle() == null && player.getRace() == null &&
                player.getProfession() == null && player.getBirthday() == null && player.getBanned() == null
        && player.getExperience() == null) {
            player = null;
            return new ResponseEntity<>(player1,HttpStatus.OK);
        }

        if(player1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(!CheckFieldPlayer.checkExp(player.getExperience())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(!CheckFieldPlayer.checkBirtDay(player.getBirthday())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(!(player.getName() == null )) {
            player1.setName(player.getName());
        }
        if(!(player.getTitle() == null)) {
            player1.setTitle(player.getTitle());
        }
        if(!(player.getRace() == null)) {
            player1.setRace(player.getRace());
        }
        if(!(player.getProfession() == null)) {
            player1.setProfession(player.getProfession());
        }
        if(!(player.getBirthday() == null)) {
            player1.setBirthday(player.getBirthday());
        }
        if(!(player.getBanned() == null)) {
            player1.setBanned(player.getBanned());
        }
        if(!(player.getExperience() == null)) {
            player1.setExperience(player.getExperience());
        }
        // обновить игрока и перечиститать лвл и опыт

            playerService.add(player1);


        return new ResponseEntity<>(player1,HttpStatus.OK);
    }





    /** 4 Delete player  тесты пройдены **/
    @DeleteMapping("/{id}")
    public ResponseEntity<Player> deletePlayerById(@PathVariable("id") String stringId) {
        Long id = 0L;
        if(CheckValidId.checkId(stringId)) {
             id = Long.parseLong(stringId);
        } else  {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if(id == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Player player = playerService.getById(id);
        if(player == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        playerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**  5 get by id  тесты пройдены **/
    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable("id") String stringId) {

        Long id = 0L;
        if(CheckValidId.checkId(stringId)) {
            id = Long.parseLong(stringId);
        } else  {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(id == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Player player = playerService.getById(id);
        if(player == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(player,HttpStatus.OK);
    }



    /** 7 get player count **/





    @GetMapping("/count")
    public ResponseEntity<Integer> getPlayersCount(@RequestParam(value = "name",required = false) String name,
                                   @RequestParam(value = "title",required = false) String title,
                                   @RequestParam(value = "race",required = false) Race race,
                                   @RequestParam(value = "profession",required = false) Profession profession,
                                   @RequestParam(value = "after",required = false) Long after,
                                   @RequestParam(value = "before",required = false) Long before,
                                   @RequestParam(value = "banned",required = false) Boolean banned,
                                   @RequestParam(value = "minExperience",required = false) Integer minExperience,
                                   @RequestParam(value = "maxExperience",required = false) Integer maxExperience,
                                   @RequestParam(value = "minLevel",required = false) Integer minLevel,
                                   @RequestParam(value = "maxLevel",required = false) Integer maxLevel
                                                   ) {
        List<Player> all = playerService.getAll(name,title,race,profession,after,before,banned,minExperience,maxExperience,minLevel,maxLevel);
//        System.out.println(all);
//        for (Player player : all) {
//            System.out.println("************");
//            System.out.println(player);
//            System.out.println("************");
//        }


        return new ResponseEntity<>(all.size(),HttpStatus.OK);
    }




}