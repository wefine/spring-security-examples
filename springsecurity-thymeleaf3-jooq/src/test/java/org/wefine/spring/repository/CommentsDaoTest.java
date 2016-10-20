package org.wefine.spring.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.wefine.spring.AbstractSpringTest;
import org.wefine.spring.jooq.tables.pojos.Comments;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class CommentsDaoTest extends AbstractSpringTest {

    @Resource
    private CommentsDao dao;

    @Test
    public void testFindAll() {
        List<Comments> list = dao.findAll();

        log.info("size=" + list.size());
    }


    @Test
    public void testSearchWithPageable() {
        PageRequest request = new PageRequest(0, 10);
        List<Comments> list = dao.search(request);
        log.info("first page, size=" + list.size());
        list.forEach(System.out::println);

        list = dao.search(request.next());
        log.info("second page, size=" + list.size());
        list.forEach(System.out::println);

        Sort sort = new Sort(Sort.Direction.ASC, "name");
        PageRequest sortedRequest = new PageRequest(0, 10, sort);
        List<Comments> sortedList = dao.search(sortedRequest);

        // first page--sort by name
        log.info("first page--sort by name, size=" + sortedList.size());
        sortedList.forEach(System.out::println);

        sortedList = dao.search(sortedRequest.next());
        log.info("second page--sort by name, size=" + sortedList.size());
        sortedList.forEach(System.out::println);
    }

    @Test
    public void testSearchWithPageableAndConditionMap() {
        PageRequest request = new PageRequest(0, 10);
        Map<String, Object> conditionMap = new HashMap<>();
        conditionMap.put("name", "2");

        log.info("===================================query by name, start");
        List<Comments> list = dao.search(request, conditionMap);
        log.info("size=" + list.size());
        list.forEach(System.out::println);
        log.info("===================================end");

        log.info("===================================query by id, start");
        conditionMap = new HashMap<>();
        conditionMap.put("id", "2");
        list = dao.search(request, conditionMap);
        log.info("size=" + list.size());
        list.forEach(System.out::println);
        log.info("===================================end");

        log.info("===================================query by post_id, start");
        conditionMap = new HashMap<>();
        conditionMap.put("post_id", "3");

        list = dao.search(request, conditionMap);
        log.info("size=" + list.size());
        list.forEach(System.out::println);
        log.info("===================================query by post_id, end");

        log.info("===================================query by created_on, start");
        Timestamp timestamp = Timestamp.valueOf("2016-09-30 08:58:20");

        conditionMap = new HashMap<>();
        conditionMap.put("created_on", timestamp);

        list = dao.search(request, conditionMap);
        log.info("size=" + list.size());
        list.forEach(System.out::println);
        log.info("===================================query by created_on,end");

        log.info("===================================query by post_id and sort by name, start");
        conditionMap = new HashMap<>();
        conditionMap.put("post_id", "3");

        Sort sort = new Sort(Sort.Direction.DESC, "name");
        PageRequest sortedRequest = new PageRequest(1, 10, sort);
        List<Comments> sortedList = dao.search(sortedRequest, conditionMap);

        log.info("size=" + sortedList.size());
        sortedList.forEach(System.out::println);
        log.info("===================================query by post_id and sort by name, end");
    }
}
