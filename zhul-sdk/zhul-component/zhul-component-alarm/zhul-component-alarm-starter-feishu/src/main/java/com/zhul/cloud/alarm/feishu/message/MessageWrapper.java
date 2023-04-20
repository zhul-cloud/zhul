package com.zhul.cloud.alarm.feishu.message;

import com.google.common.collect.Lists;
import com.zhul.cloud.alarm.feishu.model.FeishuBotAlertParams;
import com.zhul.cloud.alarm.feishu.model.FeishuBotAlertParams.Action;
import com.zhul.cloud.alarm.feishu.model.FeishuBotAlertParams.Action.Button;
import com.zhul.cloud.alarm.feishu.model.FeishuBotAlertParams.Card;
import com.zhul.cloud.alarm.feishu.model.FeishuBotAlertParams.Config;
import com.zhul.cloud.alarm.feishu.model.FeishuBotAlertParams.Element;
import com.zhul.cloud.alarm.feishu.model.FeishuBotAlertParams.Element.Field;
import com.zhul.cloud.alarm.feishu.model.FeishuBotAlertParams.Element.Text;
import java.util.List;

/**
 * @Auther: yanglikai
 * @Date: 2020/9/22 09:10
 * @Description:
 */
public class MessageWrapper {

  public static FeishuBotAlertParams wrap(BotMessage message) {
    Config config = new Config();
    config.setWide_screen_mode(false);
    config.setEnable_forward(true);

    List<Element> elements = Lists.newArrayList();

    Element element_1 = new Element();
    element_1.setTag("div");
    element_1.setText(new Text("lark_md",
        String.format("**%s | %s**", message.getType().getTitle(), message.getAppName())));

    Element element_2 = new Element();
    element_2.setTag("div");
    element_2.setText(new Text("plain_text", message.getContent()));

    Element element_3 = new Element();
    element_3.setTag("div");
    element_3.setText(new Text("lark_md", ""));

    List<Field> fields = Lists.newArrayList();
    Field field_1 = new Field();
    field_1.setIs_short(true);
    field_1.setText(
        new Text("lark_md", String.format("**告警级别:**\n %s", message.getLevel().getLevel())));
    fields.add(field_1);

    Field field_2 = new Field();
    field_2.setIs_short(true);
    field_2.setText(new Text("lark_md", String.format("**记录编号:**\n %s", message.getId())));
    fields.add(field_2);

    element_3.setFields(fields);

    Element element_4 = new Element();
    element_4.setTag("div");
    element_4.setText(new Text("lark_md", ""));

    List<Field> fields_1 = Lists.newArrayList();
    Field field_1_1 = new Field();
    field_1_1.setIs_short(true);
    field_1_1.setText(new Text("lark_md", String.format("**告警指标:**\n %s", message.getTarget())));
    fields_1.add(field_1_1);

    Field field_2_1 = new Field();
    field_2_1.setIs_short(true);
    field_2_1.setText(new Text("lark_md", String.format("**影响范围:**\n %s", message.getEnv())));
    fields_1.add(field_2_1);

    element_4.setFields(fields_1);

    Element element_5 = new Element();
    element_5.setTag("div");
    element_5.setText(new Text("lark_md", ""));

    List<Field> fields_2 = Lists.newArrayList();
    Field field_1_1_1 = new Field();
    field_1_1_1.setIs_short(true);
    field_1_1_1
        .setText(new Text("lark_md", String.format("**告警时间:**\n %s", message.getDatetime())));
    fields_2.add(field_1_1_1);

    element_5.setFields(fields_2);

    List<Button> buttons = Lists.newArrayList();
    Button button = new Button();
    button.setTag("button");
    button.setText(new Text("lark_md", "查看日志"));
    button.setUrl(message.getUrl());
    button.setType("default");
    buttons.add(button);

    Action action = new Action();
    action.setActions(buttons);
    action.setTag("action");

    elements.add(element_1);
    elements.add(element_2);
    elements.add(element_3);
    elements.add(element_4);
    elements.add(element_5);
    elements.add(action);

    Card card = new Card();
    card.setConfig(config);
    card.setElements(elements);

    FeishuBotAlertParams params = new FeishuBotAlertParams();
    params.setMsg_type("interactive");
    params.setCard(card);

    return params;
  }
}
