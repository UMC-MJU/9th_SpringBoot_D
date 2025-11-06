package com.example.umc9th.global.entity;
import lombok.Setter;
import org.springframework.data.domain.Sort.Direction;

@Setter
public class PageRequest {

    private int page = 1;
    private int size = 2;
    private Direction direction = Direction.DESC;

    public void setPage(int page) {
        this.page = page <= 0 ? 1 : page;
    }

    public void setSize(int size) {
        int DEFAULT_SIZE = 10;
        int MAX_SIZE = 50;
        this.size = size > MAX_SIZE ? DEFAULT_SIZE : size;
    }

    public org.springframework.data.domain.PageRequest of() {
        return org.springframework.data.domain.PageRequest.of(page - 1, size, direction, "create_date");
    }
}