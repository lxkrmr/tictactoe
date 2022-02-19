package com.example.tictactoe.adapter.incoming;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NeedALittleHelpController implements ErrorController {

    @RequestMapping(value = "/error", produces = "text/plain")
    public String upsLetMeHelpYou() {
        return String.join("\n",
                           "Oh, hello!",
                           "What happened? Is everything ok?",
                           "Let me give you a helping hand.",
                           "This web application is all about playing Tic Tac Toe.",
                           "So if you want to play a game with a friend or nemesis,",
                           "then the easiest way to start is to click in the url bar and add this to the end:",
                           "",
                           "/tictactoe",
                           "",
                           "For example for me the url looks then like this: http://localhost:8080/tictactoe",
                           "",
                           "From there you will get a new hint to get started.",
                           "Feel free to copy paste it!",
                           "And no worries if you mess something up, then you will come back to this page.",
                           "",
                           "Give it a try and have fun.",
                           "Always yours, your friendly web application ;)");
    }

}
