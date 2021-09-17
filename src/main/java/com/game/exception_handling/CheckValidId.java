package com.game.exception_handling;

public class CheckValidId {
    public static boolean checkId(String id) {
        long aLong = 0;
        try {
            aLong = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return false;
        }
        if(aLong > 0) {
            return true;
        }
        return false;
    }
}
