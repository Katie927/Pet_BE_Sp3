package com.BEJ.Bej.identity.repository;

import com.BEJ.Bej.identity.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidatedTokenRepositoy extends JpaRepository<InvalidatedToken, String> {

}
