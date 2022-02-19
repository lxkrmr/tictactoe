package com.example.tictactoe.adapter.outgoing;

import com.example.tictactoe.domain.game.GameName;
import com.example.tictactoe.domain.game.GameRepository;
import com.example.tictactoe.domain.game.GameView;
import com.example.tictactoe.domain.game.GameViewRepository;
import org.springframework.stereotype.Repository;

import static com.example.tictactoe.domain.game.GameView.gameView;

@Repository
public class SimpleGameViewRepository implements GameViewRepository {


    private GameRepository gameRepository;

    public SimpleGameViewRepository(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public GameView load(GameName gameName) {
        return gameView(gameRepository.load(gameName));
    }
}
