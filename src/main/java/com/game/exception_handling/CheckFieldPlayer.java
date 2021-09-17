package com.game.exception_handling;

import com.game.entity.Player;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CheckFieldPlayer {

    public static boolean canCreate(Player player) {
        if(player.getName() == null || player.getTitle() == null || player.getRace() == null ||
                player.getProfession() == null || player.getBirthday() == null || player.getExperience() == null) {
            return false;
        }
        return true;
    }

    public static boolean checkCorrentPlayer(Player player) {
        if(!checkName(player.getName())) {
            return false;
        }
        if(!checkTitle(player.getTitle())) {
            return false;
        }
        if(!checkExp(player.getExperience())){
            return false;
        }
        if(!checkBirtDay(player.getBirthday())) {
            return false;
        }

        return true;
    }

    public static boolean checkName(String name) {
        if(name.length() > 12 || name.matches("^\\s*$") ) {
            return false;
        }
        return true;
    }

    public static boolean checkTitle(String name) {
        if(name.length() > 30 ) {
            return false;
        }
        return true;
    }

    public static boolean checkExp(Integer exp) {
        if(exp == null) {
            return  true;
        }
        if(exp < 0 || exp > 10_000_000 ) {
            return false;
        }
        return true;
    }

    public static boolean checkBirtDay(Date date) {
        if(date == null) {
            return true;
        }

        Long birtDay = date.getTime();
        Long begin = new GregorianCalendar(2000,0,0).getTimeInMillis();
        Long end = new GregorianCalendar(3000,0,0).getTimeInMillis();

        if(birtDay < 0 || (birtDay < begin || birtDay > end)) {
            return false;
        }


        return true;
    }
}
