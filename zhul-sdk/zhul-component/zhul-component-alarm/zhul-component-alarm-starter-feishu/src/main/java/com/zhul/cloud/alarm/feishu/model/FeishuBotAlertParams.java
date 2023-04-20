package com.zhul.cloud.alarm.feishu.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

/**
 * @Auther: yanglikai
 * @Date: 2020/9/21 18:14
 * @Description:
 */
@Data
public class FeishuBotAlertParams {

  @JsonProperty(value = "msg_type")
  private String msg_type;

  @JsonProperty(value = "card")
  private Card card;


  @Data
  public static class Card {

    @JsonProperty(value = "config")
    private Config config;

    @JsonProperty(value = "elements")
    private List<Element> elements;
  }

  @Data
  public static class Config {

    @JsonProperty(value = "wide_screen_mode")
    private Boolean wide_screen_mode;

    @JsonProperty(value = "enable_forward")
    private Boolean enable_forward;
  }

  @Data
  public static class Element {

    @JsonProperty(value = "tag")
    private String tag;

    @JsonProperty(value = "text")
    private Text text;

    @JsonProperty(value = "fields")
    private List<Field> fields;

    @Data
    public static class Text {

      @JsonProperty(value = "tag")
      private String tag;

      @JsonProperty(value = "content")
      private String content;

      public Text(String tag, String content) {
        this.tag = tag;
        this.content = content;
      }
    }

    @Data
    public static class Field {

      @JsonProperty(value = "is_short")
      private Boolean is_short;

      @JsonProperty(value = "text")
      private Text text;
    }
  }

  @Data
  public static class Action extends Element {

    @JsonProperty(value = "actions")
    private List<Button> actions;

    @JsonProperty(value = "tag")
    private String tag;

    @Data
    public static class Button {

      @JsonProperty(value = "tag")
      private String tag;

      @JsonProperty(value = "text")
      private Text text;

      @JsonProperty(value = "url")
      private String url;

      @JsonProperty(value = "type")
      private String type;
    }
  }
}
