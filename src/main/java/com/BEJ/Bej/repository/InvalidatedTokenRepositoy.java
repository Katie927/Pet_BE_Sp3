package com.BEJ.Bej.repository;

import com.BEJ.Bej.entity.identity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidatedTokenRepositoy extends JpaRepository<InvalidatedToken, String> {

}
