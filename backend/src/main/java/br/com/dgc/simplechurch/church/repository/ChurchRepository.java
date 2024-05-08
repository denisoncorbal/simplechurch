package br.com.dgc.simplechurch.church.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.dgc.simplechurch.church.model.Church;

public interface ChurchRepository extends JpaRepository<Church, UUID> {

}
