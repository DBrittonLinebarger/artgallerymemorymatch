//Copyright 2019 Denelle Britton Linebarger
//    All Rights Reserved.

package edu.cnm.deepdive.gallery_match.service;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cnm.deepdive.gallery_match.model.pojo.Score;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;


/**
 * Adds sorted scores to shared preferences.
 */

public class HighScoreService {

  public static final String HIGH_SCORES_KEY = "high_scores";
  private static int maxScores = 10;
  private static Context context;
  private NavigableSet<Score> scores;
  private Gson gson;

  private HighScoreService() {
    scores = new TreeSet<>();
    gson = new GsonBuilder().create();
    Collections.addAll(scores, read());
  }

  /**
   * Allows initialization-on demand for this service.
   * @return instance of this service
   */

  public static HighScoreService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  /**
   * Sets maximum number of scores that can be stored in shared preferences.
   * @param maxScores the maximum number of scores that can be stored
   */

  public static void setMaxScores(int maxScores) {
    HighScoreService.maxScores = maxScores;
  }

  /**
   * Provides context for this service.
   * @param context context for service
   */

  public static void setContext(Context context) {
    HighScoreService.context = context;
  }

  /**
   * Checks to see if limit reached for max number of stored scores.
   * If max number is false, adds latest score.
   * If max number is true, deleletes highest score before adding latest score.
   * @param score score from latest game
   * @return score added from game
   */

  public boolean add(Score score) {
    boolean added = scores.add(score);
    while (scores.size() > maxScores) {
      scores.pollLast();
    }
    added = added && scores.contains(score);
    return added;
  }

  /**
   * Returns list of scores from games played.
   * @return list of scores
   */

  public List<Score> highScores() {
    return new LinkedList<>(scores);
  }

  private Score[] read() {
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    return gson.fromJson(prefs.getString(HIGH_SCORES_KEY, "[]"), Score[].class);
  }

  private void update() {
    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    SharedPreferences.Editor editor = prefs.edit();
    editor.putString(HIGH_SCORES_KEY, gson.toJson(scores).toString());
    editor.apply();

  }

  private static class InstanceHolder {

    private static HighScoreService INSTANCE;

    static {
      INSTANCE = new HighScoreService();


    }
  }

}
