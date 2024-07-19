package com.mhogamesstore.mho_games_store.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mhogamesstore.mho_games_store.Constants;
import com.mhogamesstore.mho_games_store.Games;
import com.mhogamesstore.mho_games_store.repository.StoreRepo;


@Service
public class StoreService {
    
    @Autowired
    StoreRepo storeRepo;

    public Games getGame(int index){

        return storeRepo.getGame(index);

    }


    public void addGame(Games game){

        storeRepo.addGame(game);
        
    
    }

    public void updateGame(Games game, int index){

        storeRepo.updateGame(game, index);

        
    }


    public List<Games> getGames(){

        return storeRepo.getGames();
    }


    public int getIndexId(String id){

        for(int i=0; i<getGames().size();i++){

            if(getGame(i).getId().equals(id))
            return i;

}
            return Constants.NOT_FOUND;

    }

        public boolean within10Days(LocalDate newDate, LocalDate oldDate) {
        long diff = Math.abs(java.time.temporal.ChronoUnit.DAYS.between(newDate, oldDate));
        return diff <= 10;
    }
   

    public Games getGameFromId(String id){


        int index=getIndexId(id);
        return  index == Constants.NOT_FOUND? new Games(): getGame(index) ;
    }


    public String handelSubmit(Games game){

        int index = getIndexId(game.getId());
        String status = Constants.Success;
        if(index == Constants.NOT_FOUND){

            addGame(game);

        }else if (within10Days(game.getDate(), getGame(index).getDate())){
           updateGame(game, index);


        }else{

            status = Constants.failed;
        }

        return status;
    }



}
