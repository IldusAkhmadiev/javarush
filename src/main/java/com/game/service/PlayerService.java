package com.game.service;

import com.game.controller.PlayerOrder;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface PlayerService {
    List<Player> getAll(String name, String title, Race race, Profession profession, Long before, Long after,
                        Boolean banned, Integer minExperience, Integer maxExperience, Integer minLevel, Integer maxLevel
                        );

    void add(Player player);

    void update(Player player);

    void delete(Long id);

    Player getById(Long id);

    List<Player> getList(HashMap<String,Object> Optional);

    Integer getCount(HashMap<String,Object> Optional);

    Player saveAndUpdate(Player player);

    Specification<Player> nameSelect(Player player);

    List<Player> getAllAll(String name,String title,Race race,Profession profession,Long before,Long after,
                           Boolean banned,Integer minExperience,Integer maxExperience,
                           Integer minLevel,Integer maxLevel,PlayerOrder order,Integer pageNumber,Integer pageSize);

}
