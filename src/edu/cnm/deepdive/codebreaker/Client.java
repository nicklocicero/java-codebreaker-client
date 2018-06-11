package edu.cnm.deepdive.codebreaker;

import edu.cnm.deepdive.codebreaker.models.Game;
import edu.cnm.deepdive.codebreaker.models.Guess;
import edu.cnm.deepdive.codebreaker.services.GameService;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {

  public static void main(String[] args) {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://java-bootcamp.cnm.edu/rest/codebreaker/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    GameService gameService = retrofit.create(GameService.class);
    Scanner scanner = new Scanner(System.in);

    Game game;

    try {
      Game gameSpecification = new Game();
      System.out.println("Enter the characters to use in this game:");
      String characters = scanner.nextLine();
      game = gameService.createGame(gameSpecification).execute().body();
    } catch (IOException e) {
      throw new RuntimeException("Error creating game", e);
    }

    while (true) {
      System.out.printf("Enter a guess of %d characters:", game.getLength());
      String guessString = scanner.nextLine().trim();
      Guess guess = new Guess();
      guess.setGuess(guessString);
      try {
         Response<Guess> guessResponse = gameService.createGuess(game.getId(), guess)
             .execute();
         Guess guessFromServer = guessResponse.body();
         System.out.printf("In place: %d Out of place %d", guessFromServer.getInPlace(),
             guessFromServer.getOutOfPlace());
      } catch (IOException e) {
        throw new RuntimeException("Error sending guess", e);
      }
    }

    /*gameService.findById(UUID.fromString("8614d224-92fb-4895-8c54-9c21f1486eb5")).enqueue(
        new Callback<Game>() {
      @Override
      public void onResponse(Call<Game> call, Response<Game> response) {
        Game game = response.body();
        System.out.println("Guesses " + game.getGuesses().size() + "\n" + "Characters "
            + game.getCharacters());
      }

      @Override
      public void onFailure(Call<Game> call, Throwable throwable) {
        throw new RuntimeException("Request Failed", throwable);
      }
    });*/
  }
}
