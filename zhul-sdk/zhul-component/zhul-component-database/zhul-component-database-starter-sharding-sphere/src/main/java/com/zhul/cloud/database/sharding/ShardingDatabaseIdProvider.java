package com.zhul.cloud.database.sharding;

import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.apache.shardingsphere.infra.hint.HintManager;

/**
 * Created by yanglikai on 2022/11/29.
 */
public class ShardingDatabaseIdProvider implements DatabaseIdProvider {

  private VendorDatabaseIdProvider databaseIdProvider;

  public ShardingDatabaseIdProvider(VendorDatabaseIdProvider databaseIdProvider) {
    this.databaseIdProvider = databaseIdProvider;
  }

  @Override
  public String getDatabaseId(DataSource dataSource) throws SQLException {

    Statement statement = dataSource.getConnection().createStatement();

    /* 主数据源 */
    HintManager.getInstance().setWriteRouteOnly();
    statement.executeQuery("SELECT 'x'");
    HintManager.clear();

    /* 从数据源 */
    statement.executeQuery("SELECT 'x'");
    statement.executeQuery("SELECT 'x'");


    return this.databaseIdProvider.getDatabaseId(dataSource);
  }
}
