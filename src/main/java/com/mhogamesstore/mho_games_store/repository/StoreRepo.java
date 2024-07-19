package com.mhogamesstore.mho_games_store.repository;

import java.util.ArrayList;
import java.util.List;

import com.mhogamesstore.mho_games_store.Games;

public class StoreRepo {
    
   private List<Games> games = new ArrayList<>();


    public Games getGame(int index){

        return games.get(index);

    }


    public void addGame(Games game){

        games.add(game);
        
    
    }

    public void updateGame(Games game, int index){

        games.set(index, game);

        
    }


    public List<Games> getGames(){

        return games;
    }



}
