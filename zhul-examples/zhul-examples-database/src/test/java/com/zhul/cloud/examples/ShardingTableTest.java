package com.zhul.cloud.examples;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by yanglikai on 2022/10/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class ShardingTableTest {

  @Autowired
  private WebApplicationContext context;

  private MockMvc mockMvc;

  @Before
  public void setupMockMvc() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  /**
   * 数据分片(取模)
   * <p>
   * 验证点： 1.插入数据是否正确分片到指定表中 2.插入是否是主库插入
   * </p>
   *
   * @throws Exception
   */
  @Test
  public void created_01() throws Exception {
    mockMvc.perform(
        post("/v1/orders")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andDo(print());
  }

  /**
   * 加载单次数据
   * <p>
   * 验证点： 1.返回数据是否是当前分表数据 2.查询是否是从库查询
   * </p>
   *
   * @throws Exception
   */
  @Test
  public void loadOrder_0() throws Exception {
    Integer orderCode = 2020121014;

    mockMvc.perform(
        get("/v1/orders/{orderCode}", orderCode)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andDo(print());
  }

  /**
   * 加载单次数据
   * <p>
   * 验证点： 1.返回数据是否是当前分表数据 2.查询是否是从库查询
   * </p>
   *
   * @throws Exception
   */
  @Test
  public void loadOrder_1() throws Exception {
    Integer orderCode = 2020121013;

    mockMvc.perform(
        get("/v1/orders/{orderCode}", orderCode)
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isOk()).andDo(print());
  }

  /**
   * 加载多次数据
   * <p>
   * 验证点： 1.查询是否是从库查询，算法是否是轮询算法
   * </p>
   *
   * @throws Exception
   */
  @Test
  public void loadOrder_merge() throws Exception {
    this.loadOrder_0();

    this.loadOrder_1();
  }
}
