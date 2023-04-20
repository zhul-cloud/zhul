package com.zhul.cloud.common.spec;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yanglikai on 2022/12/07.
 */
public class Notification {

  private List<String> reasons = new ArrayList();

  public static Notification create() {
    return new Notification();
  }

  private Notification() {
  }

  public boolean addReason(String line, String msg) {
    return this.addReason(String.format("%s#%s", line, msg));
  }

  public boolean addReason(String reason) {
    if (reason != null && !reason.trim().isEmpty()) {
      Iterator var2 = this.reasons.iterator();

      String r;
      do {
        if (!var2.hasNext()) {
          return this.reasons.add(reason);
        }

        r = (String) var2.next();
      } while (!r.equals(reason));

      return false;
    } else {
      return false;
    }
  }

  public boolean isEmpty() {
    return this.reasons.isEmpty();
  }

  public boolean isNotEmpty() {
    return !isEmpty();
  }

  public String tString() {
    if (isEmpty()) {
      return "";
    }

    StringBuffer sb = new StringBuffer();
    for (String reason : this.reasons) {
      sb.append(reason);
      sb.append(System.lineSeparator());
    }

    return sb.toString();
  }

  public int size() {
    return this.reasons.size();
  }

  public List<String> reasons() {
    return this.reasons;
  }

  public String firstReason() {
    return this.isEmpty() ? null : (String) this.reasons.get(0);
  }
}
