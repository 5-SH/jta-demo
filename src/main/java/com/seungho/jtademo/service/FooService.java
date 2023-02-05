package com.seungho.jtademo.service;

import com.seungho.jtademo.repository.CommonMapper;
import com.seungho.jtademo.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FooService {

  @Autowired private UserMapper userMapper;

  @Autowired private CommonMapper commonMapper;

  @Transactional
  public void bar(String userId) {
    userMapper.insert(userId);
    commonMapper.insert(userId);
  }

  @Transactional
  public void barWithException(String userId) {
    userMapper.insert(userId);
    commonMapper.insert(userId);

    if ("test5".equals(userId)) {
      throw new IllegalArgumentException("Intended exception");
    }
  }

  @Transactional
  public void deleteAll() {
    userMapper.deleteAll("*");
    commonMapper.deleteAll("*");
  }

  public int getUserCount(String userId) { return userMapper.getCount(userId); }

  public int getCommonCount(String userId) { return commonMapper.getCount(userId); }
}
