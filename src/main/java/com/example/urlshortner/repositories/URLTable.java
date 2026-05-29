package com.example.urlshortner.repositories;

import com.example.urlshortner.entities.URLEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface URLTable extends JpaRepository<URLEntity,Long> {

}
