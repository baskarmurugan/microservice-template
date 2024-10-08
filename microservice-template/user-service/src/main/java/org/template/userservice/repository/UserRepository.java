package org.template.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.template.userservice.model.UserDetails;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Long> {
}
