package com.seungho.jtademo.repository;

import com.seungho.jtademo.annotation.RoutingKey;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CommonMapper {

  @Insert("insert into common_test(user_id) values(#{userId})")
  void insert(@Param("userId") String userId);

  @Select("select count(*) from common_test where user_Id=#{userId}")
  int getCount(@Param("userId") String userId);

  @Delete("delete from common_test")
  void deleteAll(@Param("userId") String userId);
}
