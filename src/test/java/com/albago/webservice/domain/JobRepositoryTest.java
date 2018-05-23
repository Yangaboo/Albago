package com.albago.webservice.domain;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JobRepositoryTest {

    @Autowired
    JobRepository jobRepository;

    @After
    public void cleanup() {
        jobRepository.deleteAll();
    }

    @Test
    public void save_load_content() {
        jobRepository.save(Job.builder()
        .title("test")
        .pay(7500)
        .work_date(5)
        .night(true)
        .weekend(true)
        .age(20)
        .build());

        List<Job> jobList = jobRepository.findAll();

        Job job = jobList.get(0);
        assertThat(job.getTitle(), is("test"));
        assertThat(job.getPay(), is(7500));
        assertThat(job.getWork_date(), is(5));
        assertThat(job.getAge(), is(20));
    }
}
