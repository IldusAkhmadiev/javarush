package com.game.service;

import com.game.controller.PlayerOrder;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.repository.PlayerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class PlayerServiceImpl implements  PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    PlatformTransactionManager platformTransactionManager;

    @Autowired
    EntityManagerFactory entityManagerFactory;

    //            query.select(from.get("name"));
//            query.where(cb.like(from.get("name"), name));
//        List<Player> resultList = manager.createQuery(query).getResultList();

    @Override
    public List<Player> getAll(String name,String title,Race race,Profession profession,Long before,Long after,
                               Boolean banned,Integer minExperience,Integer maxExperience,
                               Integer minLevel,Integer maxLevel) {

        EntityManager manager =  entityManagerFactory.createEntityManager();
        manager.getTransaction().begin();
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Player> query = cb.createQuery(Player.class);
        Root<Player> from = query.from(Player.class);

        List<Predicate> predicateList = new ArrayList<>();
        Predicate[] predicates = new Predicate[1];
        if(name != null) {
            predicates[0] = cb.like(from.get("name"),"%"+ name + "%");
            predicateList.add(predicates[0]);
        }
        if(title != null) {
            predicates[0] = cb.like(from.get("title"), "%"+ title + "%");
            predicateList.add(predicates[0]);
        }
        if(race != null) {
            predicates[0] = cb.equal(from.get("race"), race);
            predicateList.add(predicates[0]);
        }
        if(profession != null) {
            predicates[0] = cb.equal(from.get("profession"), profession);
            predicateList.add(predicates[0]);
        }
        if(before != null) {
            predicates[0] = cb.greaterThanOrEqualTo(from.get("birthday"), new Date(before));
            predicateList.add(predicates[0]);
        }
        if(after != null) {
            predicates[0] = cb.lessThanOrEqualTo(from.get("birthday"), new Date(after));
            predicateList.add(predicates[0]);
        }
        if(banned != null) {
            predicates[0] = cb.equal(from.get("banned"), banned);
            predicateList.add(predicates[0]);
        }
        if(minExperience != null) {
            predicates[0] = cb.greaterThanOrEqualTo(from.get("experience"), minExperience);
            predicateList.add(predicates[0]);
        }
        if(maxExperience != null) {
            predicates[0] = cb.lessThanOrEqualTo(from.get("experience"), maxExperience);
            predicateList.add(predicates[0]);
        }
        if(minLevel != null) {
            predicates[0] = cb.greaterThanOrEqualTo(from.get("level"), minLevel);
            predicateList.add(predicates[0]);
        }
        if(maxLevel != null) {
            predicates[0] = cb.lessThanOrEqualTo(from.get("level"), maxLevel);
            predicateList.add(predicates[0]);
        }
        Predicate[] objects =  predicateList.toArray(new Predicate[0]);
        CriteriaQuery<Player> where = query.select(from).where(objects);
        List<Player> resultList = manager.createQuery(where).getResultList();

        return resultList;
    }

    public List<Player> getAllAll(String name, String title, Race race, Profession profession, Long before, Long after,
                                  Boolean banned, Integer minExperience, Integer maxExperience,
                                  Integer minLevel, Integer maxLevel, PlayerOrder order, Integer pageNumber, Integer pageSize) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        manager.getTransaction().begin();
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Player> query = cb.createQuery(Player.class);
        Root<Player> from = query.from(Player.class);

        List<Predicate> predicateList = new ArrayList<>();
        Predicate[] predicates = new Predicate[1];
        if(name != null) {
            predicates[0] = cb.like(from.get("name"),"%"+ name + "%");
            predicateList.add(predicates[0]);
        }
        if(title != null) {
            predicates[0] = cb.like(from.get("title"), "%"+ title + "%");
            predicateList.add(predicates[0]);
        }
        if(race != null) {
            predicates[0] = cb.equal(from.get("race"), race);
            predicateList.add(predicates[0]);
        }
        if(profession != null) {
            predicates[0] = cb.equal(from.get("profession"), profession);
            predicateList.add(predicates[0]);
        }
        if(before != null) {
            predicates[0] = cb.greaterThanOrEqualTo(from.get("birthday"), new Date(before));
            predicateList.add(predicates[0]);
        }
        if(after != null) {
            predicates[0] = cb.lessThanOrEqualTo(from.get("birthday"), new Date(after));
            predicateList.add(predicates[0]);
        }
        if(banned != null) {
            predicates[0] = cb.equal(from.get("banned"), banned);
            predicateList.add(predicates[0]);
        }
        if(minExperience != null) {
            predicates[0] = cb.greaterThanOrEqualTo(from.get("experience"), minExperience);
            predicateList.add(predicates[0]);
        }
        if(maxExperience != null) {
            predicates[0] = cb.lessThanOrEqualTo(from.get("experience"), maxExperience);
            predicateList.add(predicates[0]);
        }
        if(minLevel != null) {
            predicates[0] = cb.greaterThanOrEqualTo(from.get("level"), minLevel);
            predicateList.add(predicates[0]);
        }
        if(maxLevel != null) {
            predicates[0] = cb.lessThanOrEqualTo(from.get("level"), maxLevel);
            predicateList.add(predicates[0]);
        }
        Predicate[] objects =  predicateList.toArray(new Predicate[0]);
        CriteriaQuery<Player> where = query.select(from).where(objects);
        TypedQuery<Player> query1 = manager.createQuery(where);
        if(order == null) {
            order = PlayerOrder.ID;
        }
        if(pageNumber == null) {
            pageNumber = 0;
        }
        if(pageSize == null) {
            pageSize = 3;
        }
        if(pageNumber == 0) {
            query1.setMaxResults(pageSize);
        }

        if(pageNumber != 0) {
            if(pageNumber != 1) {
                query1.setMaxResults(pageNumber * pageSize).setFirstResult((pageNumber - 1) * pageSize);
            }
            query1.setMaxResults(pageNumber * pageSize).setFirstResult((pageNumber) * pageSize);
        }
        List<Player> resultList = query1.getResultList();

        return resultList;
    }

    @Override
    public void add(Player player) {
        player.setLevel(player.getCurrentLevel());
        player.setUntilNextLevel(player.nextLevel());
        playerRepository.save(player);
    }

    @Override
    public void update(Player player) {
        player.setLevel(player.getCurrentLevel());
        player.setUntilNextLevel(player.nextLevel());
        playerRepository.save(player);
    }

    @Override
    public void delete(Long id) {
        playerRepository.deleteById(id );
    }

    @Override
    public Player getById(Long id) {
        Optional<Player> byId = playerRepository.findById(id);
        Player player = null;
        try {
           player = byId.get();
        } catch (Exception exception) {

        }

        if(player == null) {
            return null;
        }
        return  player;
    }

    @Override
    public List<Player> getList(HashMap<String, Object> Optional) {
        return null;
    }

    @Override
    public Integer getCount(HashMap<String, Object> Optional) {

        playerRepository.count();
        return 0;
    }


    public  Specification<Player> nameSelect(Player player) {
        return new Specification<Player>() {
            @Override
            public Predicate toPredicate(Root<Player> root, CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                query.from(Player.class);
                return null;
            }
        };
    }




    @Override
    public Player saveAndUpdate(Player player) {
        player.setLevel(player.getCurrentLevel());
        player.setUntilNextLevel(player.nextLevel());
        playerRepository.save(player);
        return player;
    }

}