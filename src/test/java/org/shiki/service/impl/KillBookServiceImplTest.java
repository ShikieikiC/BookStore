package org.shiki.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KillBookServiceImplTest {

    @Test
    void add() {
        long between = DateUtil.between(DateUtil.parse("2024-01-16 23:00:00"),
                DateUtil.date(), DateUnit.MINUTE);
        System.out.println(between);
    }
}