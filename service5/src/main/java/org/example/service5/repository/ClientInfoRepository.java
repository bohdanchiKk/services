package org.example.service5.repository;

import org.example.service5.entity.ClientInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientInfoRepository extends JpaRepository<ClientInfo, Long> {
}
