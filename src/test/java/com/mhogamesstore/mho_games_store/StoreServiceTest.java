package com.mhogamesstore.mho_games_store;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

import com.mhogamesstore.mho_games_store.repository.StoreRepo;
import com.mhogamesstore.mho_games_store.service.StoreService;

@RunWith(MockitoJUnitRunner.class)
public class StoreServiceTest { // Make the class public

    @Mock
    private StoreRepo storeRepo;

    @InjectMocks
    private StoreService storeService;

    @Test
    public void getGamesFromRepoTest() {

        Games game1 = new Games();
        game1.setCategory("Action");
        game1.setTitle("Game One");
        game1.setPrice(59.99);
        game1.setDiscount(10.0);
        game1.setDate(LocalDate.of(2020, 1, 1)); // Ensure this is a past date

        Games game2 = new Games();
        game2.setCategory("Adventure");
        game2.setTitle("Game Two");
        game2.setPrice(49.99);
        game2.setDiscount(5.0);
        game2.setDate(LocalDate.of(2019, 6, 15)); // Ensure this is a past date

        when(storeRepo.getGames()).thenReturn(Arrays.asList(game1, game2));

        List<Games> result = storeService.getGames();

        assertEquals("Game One", result.get(0).getTitle());
        assertEquals("Adventure", result.get(1).getCategory());
        System.out.println(result);
    }
}