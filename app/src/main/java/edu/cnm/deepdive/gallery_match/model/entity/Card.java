package edu.cnm.deepdive.gallery_match.model.entity;

import android.media.Image;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import java.net.URL;

@Entity(foreignKeys = {
    @ForeignKey(entity = Theme.class, parentColumns = "id", childColumns = "theme_id",
        onDelete = ForeignKey.CASCADE)
})
public class Card {

  @PrimaryKey (autoGenerate = true)
  private long id;

  @ColumnInfo(name = "object_id", index = true)
  private String objectId;

  @ColumnInfo(name = "theme_id", index = true)
  private long  themeId;

  private String title;

  @ColumnInfo(name = "object_date", index = true)
  private  String objectDate;

  private String url; // TODO INVESTIGATE CONVERTERS

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getObjectId() {
    return objectId;
  }

  public void setObjectId(String objectId) {
    this.objectId = objectId;
  }

  public long getThemeId() {
    return themeId;
  }

  public void setThemeId(long themeId) {
    this.themeId = themeId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getObjectDate() {
    return objectDate;
  }

  public void setObjectDate(String objectDate) {
    this.objectDate = objectDate;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}