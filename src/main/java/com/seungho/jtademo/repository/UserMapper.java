package com.seungho.jtademo.repository;

import com.seungho.jtademo.annotation.RoutingKey;
import com.seungho.jtademo.annotation.RoutingMapper;
import org.apache.ibatis.annotations.*;

@RoutingMapper
@Mapper
public interface UserMapper {

  @Insert("insert into user_test(user_id) values(#{userId})")
  void insert(@RoutingKey @Param("userId") String userId);

  @Select("select count(*) from user_test where user_id=#{userId}")
  int getCount(@RoutingKey @Param("userId") String userId);

  @Delete("delete from user_test")
  void deleteAll(@RoutingKey @Param("userId") String userId);
}
