package com.seungho.jtademo;

import com.seungho.jtademo.service.FooService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class JtademoApplicationTests {

	@Autowired private FooService fooService;

	@Test
	public void addAll() {
		log.debug("addAll test: {}", "=======================================================");
		String userId = "test5";
		fooService.deleteAll(userId);
		fooService.bar(userId);

		Assertions.assertThat(fooService.getUserCount(userId)).isEqualTo(1);
		Assertions.assertThat(fooService.getCommonCount(userId)).isEqualTo(1);
	}

	@Test
	public void addAllWithException() {

			String userId = "test5";
		try {
			log.debug("addAllWithException test: {}", "=======================================================");
			fooService.deleteAll(userId);
			fooService.barWithException(userId);

			Assertions.assertThat(fooService.getUserCount(userId)).isEqualTo(0);
			Assertions.assertThat(fooService.getCommonCount(userId)).isEqualTo(0);
		} catch (IllegalArgumentException e) {
			Assertions.assertThat(fooService.getUserCount(userId)).isEqualTo(0);
			Assertions.assertThat(fooService.getCommonCount(userId)).isEqualTo(0);
		}
	}

}
