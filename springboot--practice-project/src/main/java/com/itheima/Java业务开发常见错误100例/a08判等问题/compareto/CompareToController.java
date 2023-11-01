package com.itheima.Java业务开发常见错误100例.a08判等问题.compareto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;

@RestController
@Slf4j
@RequestMapping("compareto")
public class CompareToController {

    @Data
    @AllArgsConstructor
    class Student implements Comparable<Student> {
        private int id;

        private String name;

        @Override
        public int compareTo(Student other) {
            int result = Integer.compare(other.id, id);
            if (result == 0)
                log.info("this {} == other {}", this, other);
            return result;
        }
    }

    @Data
    @AllArgsConstructor
    class StudentRight implements Comparable<StudentRight> {
        private int id;
        private String name;

        @Override
        public int compareTo(StudentRight other) {
            return Comparator.comparing(StudentRight::getName)
                .thenComparingInt(StudentRight::getId)
                .compare(this, other);
        }
    }
}
