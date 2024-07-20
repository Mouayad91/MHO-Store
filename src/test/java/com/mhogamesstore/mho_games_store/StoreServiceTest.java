package com.mhogamesstore.mho_games_store;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

import com.mhogamesstore.mho_games_store.repository.StoreRepo;
import com.mhogamesstore.mho_games_store.service.StoreService;

@RunWith(MockitoJUnitRunner.class)
public class StoreServiceTest {

    @Mock
    private StoreRepo storeRepo;

    @InjectMocks
    private StoreService storeService;

    @Test
    public void getGamesFromRepoTest() {

        Games game1 = new Games("Action", "Game One", 59.99, 5, LocalDate.of(2020, 1, 1));
   

        Games game2 = new Games("Adventure","Game Two", 49.99, 5.0, LocalDate.of(2019, 6, 15));
   

        when(storeRepo.getGames()).thenReturn(Arrays.asList(game1, game2));

        List<Games> result = storeService.getGames();

        assertEquals("Action", result.get(0).getTitle());
        assertEquals("Adventure", result.get(1).getCategory());
    }

    @Test
    public void gameIndexTest() {

        Games game1 = new Games("Action", "Game One", 59.99, 10.0, LocalDate.of(2020, 1, 1));
 

        Games game2 = new Games("Adventure", "Game Two", 888, 0123, LocalDate.of(2019, 6, 15));
     
        
        when(storeRepo.getGames()).thenReturn(Arrays.asList(game1, game2));
        when(storeRepo.getGame(0)).thenReturn(game1);
        when(storeRepo.getGame(1)).thenReturn(game2);
    
        int valid = storeService.getIndexId(game1.getId());
        int notValid = storeService.getIndexId("5555");

        assertEquals(0, valid);
        assertEquals(Constants.NOT_FOUND, notValid);
    }

    @Test
    public void returnGameByIdTest(){

        Games game1 = new Games("Action", "Game One", 555.7, 100, LocalDate.of(2020, 1, 1));
     
    

        when(storeRepo.getGames()).thenReturn(Arrays.asList(game1));
        when(storeRepo.getGame(0)).thenReturn(game1);
       

        String id = game1.getId();

        Games result = storeService.getGameFromId(id);

        assertEquals(game1, result);
    }

    @Test
    public void submitGameTest(){

        Games game1 = new Games("Action", "Game One", 44.4, 3.0, LocalDate.of(2020, 1, 1));
      

        when(storeRepo.getGames()).thenReturn(Arrays.asList(game1));
        when(storeRepo.getGame(0)).thenReturn(game1);
       
        Games newGame = new Games("Action","Game Three",66.6,10.5,LocalDate.of(2021,5,6));

        storeService.handelSubmit(newGame);
        
        verify(storeRepo,times(1)).addGame(newGame);

    }

 
}




