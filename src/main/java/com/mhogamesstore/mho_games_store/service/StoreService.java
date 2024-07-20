package com.mhogamesstore.mho_games_store.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhogamesstore.mho_games_store.Constants;
import com.mhogamesstore.mho_games_store.POJO.Games;
import com.mhogamesstore.mho_games_store.repository.StoreRepo;


@Service
public class StoreService {

    @Autowired
    private StoreRepo storeRepo;

    public Games getGame(int index) {
        return storeRepo.getGame(index);
    }

    public void addGame(Games game) {
        storeRepo.addGame(game);
    }

    public void updateGame(Games game, int index) {
        storeRepo.updateGame(game, index);
    }

    public List<Games> getGames() {
        return storeRepo.getGames();
    }

    public int getIndexId(String id) {
        for (int i = 0; i < getGames().size(); i++) {
            if (getGame(i).getId().equals(id)) {
                System.out.println("Found game with ID " + id + " at index " + i);
                return i;
            }
        }
        System.out.println("Game with ID " + id + " not found");
        return Constants.NOT_FOUND;
    }

    public boolean within10Days(LocalDate newDate, LocalDate oldDate) {
        if (newDate == null || oldDate == null) {
            System.out.println("One of the dates is null: newDate=" + newDate + ", oldDate=" + oldDate);
            return false;
        }
        long diff = Math.abs(java.time.temporal.ChronoUnit.DAYS.between(newDate, oldDate));
        System.out.println("Difference between dates is " + diff + " days");
        return diff <= 10;
    }

    public Games getGameFromId(String id) {
        int index = getIndexId(id);
        return index == Constants.NOT_FOUND ? null : getGame(index);
    }

    public String handelSubmit(Games game) {
        int index = getIndexId(game.getId());
        String status = Constants.Success;
        if (index == Constants.NOT_FOUND) {
            System.out.println("Game not found, adding game");
            addGame(game);
        } else {
            Games existingGame = getGame(index);
            if (existingGame != null && game.getDate() != null && within10Days(game.getDate(), existingGame.getDate())) {
                System.out.println("Updating game within 10 days");
                updateGame(game, index);
            } else {
                System.out.println("Game update failed: Not within 10 days or existing game is null");
                status = Constants.failed;
            }
        }
        return status;
    }
}


