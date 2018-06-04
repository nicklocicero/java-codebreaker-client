package edu.cnm.deepdive.codebreaker.models;


import com.google.gson.annotations.Expose;
import java.net.URI;
import java.util.Date;
import java.util.UUID;

public class Guess {

  @Expose(deserialize = true, serialize = false)
  private UUID id;
  @Expose(deserialize = true, serialize = false)
  private URI href;
  @Expose(deserialize = true, serialize = false)
  private Date created;
  @Expose
  private String guess;
  @Expose(deserialize = true, serialize = false)
  private int inPlace;
  @Expose(deserialize = true, serialize = false)
  private int outOfPlace;

  public void setId(UUID id) {
    this.id = id;
  }

  public void setHref(URI href) {
    this.href = href;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public void setGuess(String guess) {
    this.guess = guess;
  }

  public String getGuess() {
    return guess;
  }

  public int getInPlace() {
    return inPlace;
  }

  public int getOutOfPlace() {
    return outOfPlace;
  }
}