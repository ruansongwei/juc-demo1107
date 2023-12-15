package design.structural.adapter.clazz;

import design.structural.adapter.MoviePlayer;

public class MainTest {

    public static void main(String[] args) {

//        MoviePlayer player = new MoviePlayer();
//        player.play();
        JPMoviePlayerAdapter adapter = new JPMoviePlayerAdapter(new MoviePlayer());
//
//
        adapter.play();


    }
}
