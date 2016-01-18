package com.udacity.gradle.builditbigger.joker.java;

import java.util.Random;

public class JokeMaker {

    private static String jokes[] = {"Why did the chicken cross the road?\n\nTo get to the other side",
            "The only time incorrectly isn’t spelled incorrectly is when it’s spelled incorrectly",
            "I used to think the brain was the most important organ. Then I thought, look what’s telling me that",
            "Today a man knocked on my door and asked for a small donation towards the local swimming pool. I gave him a glass of water"};

    public static String getAJoke() {
        return jokes[new Random().nextInt(jokes.length)];
    }
}
